package com.el.task;

import com.el.SinoiovlApiService;
import com.el.TokenService;
import com.el.common.enums.ResponseStatusCode;
import com.el.common.source.Constant;
import com.el.common.source.Gps;
import com.el.common.to.response.SinoiovlTO;
import com.el.common.utils.BaiDuMapUtils;
import com.el.dao.*;
import com.el.entity.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author ZhangJun
 * @Description: 车辆监控任务
 * @create 2019-09-27 16:07
 */
@Component
@SuppressWarnings("DuplicatedCode")
public class MonitorWagonTask {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SinoiovlApiService sinoiovlApiService;

    @Autowired
    private WagonOnMissionDao wagonOnMissionDao;

    @Autowired
    private WagonPositionDao wagonPositionDao;

    @Autowired
    private RouteStopDao routeStopDao;

    @Autowired
    private StopDao stopDao;

    @Autowired
    private WagonEventNoticeDao wagonEventNoticeDao;

    @Autowired
    private RoutePlanDao routePlanDao;

    @Autowired
    private WayBillDao wayBillDao;

    @Autowired
    private StopEventDao stopEventDao;

    @Autowired
    private WarningEventDao warningEventDao;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * @return void
     * @Description 获取车辆最新信息
     * 1: 通过t_wagon_on_mission表 获取所有的车辆
     * 2: 通过车牌调第三方接口
     * 3: 对经纬度,速度,方向,定位时间进行hash,比较数据库,查看是否一致,如一致则无需更新,反之则保存最新的信息至t_wagon_position 并更新 t_wagon_on_miss
     * 4: 离线状态(当hash一致时且时间大于配置时间(分钟))
     **/
    @Scheduled(cron = "*/15 * * * * ?")
    public void getLastedWagonInfo() throws Exception {

        List<WagonOnMission> wagonOnMissionList = wagonOnMissionDao.getWagonOnMissions("", null);

        String token = tokenService.getToken();

        for (WagonOnMission wagonOnMission : wagonOnMissionList) {
            // 获取一个小时内车子的最新位置信息
            SinoiovlTO sinoiovlTO = sinoiovlApiService.latestPos(token, wagonOnMission.getPlateNo(), Constant.HOUR);
            if (sinoiovlTO != null && ResponseStatusCode.SUCCESS.getCode().equals(sinoiovlTO.getStatus())) {
                JSONObject position = JSONObject.fromObject(sinoiovlTO.getResult());

                int hash = wagonOnMission.getPlateNo().hashCode() +
                        position.get("lat").hashCode() +
                        position.get("lon").hashCode() +
                        position.get("utc").hashCode();

                // 当前的位置信息 数据库不存在
                if (wagonPositionDao.countByHash(hash) == 0) {

                    WagonPosition wagonPosition = new WagonPosition();
                    wagonPosition.setLat(String.valueOf(position.get("lat")));
                    wagonPosition.setLon(String.valueOf(position.get("lon")));

                    Gps gps = BaiDuMapUtils.gps84_To_bd09(
                            Double.parseDouble(wagonPosition.getLat()) / 600000,
                            Double.parseDouble(wagonPosition.getLon()) / 600000
                    );
                    wagonPosition.setLatBD(String.valueOf(gps.getLat()));
                    wagonPosition.setLonBD(String.valueOf(gps.getLon()));
                    wagonPosition.setPlateNo(wagonOnMission.getPlateNo());
                    wagonPosition.setAdr(String.valueOf(position.get("adr")));
                    wagonPosition.setUtc(String.valueOf(position.get("utc")));
                    wagonPosition.setSpd(String.valueOf(position.get("spd")));
                    wagonPosition.setDrc(String.valueOf(position.get("drc")));
                    wagonPosition.setProvince(String.valueOf(position.get("province")));
                    wagonPosition.setCity(String.valueOf(position.get("city")));
                    wagonPosition.setCountry(String.valueOf(position.get("country")));
                    wagonPosition.setHash(hash);

                    // 不存在车子的定位信息
                    if (wagonPositionDao.countByPlateNo(wagonPosition.getPlateNo()) <= 0) {
                        wagonPositionDao.save(wagonPosition);
                    } else {
                        wagonPosition.setUpdateTime(System.currentTimeMillis());
                        wagonPositionDao.updateByPlateNo(wagonPosition);
                    }

                    wagonOnMission.setVelocity(wagonPosition.getSpd());
                    wagonOnMission.setDirection(wagonPosition.getDrc() == null ? 0 : Integer.valueOf(wagonPosition.getDrc()));
                    wagonOnMission.setLng(wagonPosition.getLonBD());
                    wagonOnMission.setLat(wagonPosition.getLatBD());
                    wagonOnMission.setAdr(wagonPosition.getAdr());
                    wagonOnMission.setPosTimeUpdated(Long.parseLong(wagonPosition.getUtc()));
                    wagonOnMission.setOnline(true);

                } else {
                    // 离线状态 车子不懂超过默认时间 (暂定10个小时)
                    if ((System.currentTimeMillis() - wagonOnMission.getPosTimeUpdated()) > Constant.ALLOW_MAX_OFFLINE_TIME) {
                        wagonOnMission.setOnline(false);
                    }
                }
                wagonOnMission.setUpdateTime(System.currentTimeMillis());
                wagonOnMissionDao.updatePosition(wagonOnMission);
            } else {
                // 没有获取到最新的位置信息,则判断是否超过最大离线时间
                if ((System.currentTimeMillis() - wagonOnMission.getPosTimeUpdated()) > Constant.ALLOW_MAX_OFFLINE_TIME) {
                    wagonOnMission.setOnline(false);
                    wagonOnMissionDao.updateOnline(wagonOnMission);
                }
            }
        }
    }


    /**
     * @return void
     * @Description 更新下一站的距离
     * 1: 更新下一站预计到达时间,下一站距离,下一站名称和终点到达时间 t_wagon_on_mission 和 t_route_stop
     * 2: 下一站与终点站可能一样则调一次接口
     * 3: 不一样则需调两次分别更新下一站与最后一站
     * 4: 如果预计时间晚于计划时间,则产生告警数据(先判断表里是否有数据)
     **/
    @Scheduled(cron = "*/15 * * * * ?")
    public void updateNextStopInfo() throws Exception {
        List<WagonOnMission> wagonOnMissionList = wagonOnMissionDao.getWagonOnMissions("", null);
        if (wagonOnMissionList.size() > 0) {
            String token = tokenService.getToken();
            for (WagonOnMission wagonOnMission : wagonOnMissionList) {
                // 获取班线中 站点状态为未到站的所有站点
                List<RouteStop> routeStopList = routeStopDao.findMissedArrivalByRoutePlanId(wagonOnMission.getRoutePlanId());
                RoutePlan routePlan = routePlanDao.findByIdAndDeleted(wagonOnMission.getRoutePlanId(), false);
                if (routePlan == null) {
                    continue;
                }
                if (routeStopList.size() <= 0) {
                    continue;
                }
                // 下一站预计到达时间
                long eta;
                // 下一站点id
                String nextStopId = "";
                for (int i = 0; i < routeStopList.size(); i++) {
                    // >1 说明不是最后一站 需要调两次接口
                    if (routeStopList.size() > 1) {

                        // 此为下一站
                        Stop stop = stopDao.findByIdAndDeleted(routeStopList.get(i).getStopId(), false);
                        if (stop == null) {
                            continue;
                        }
                        if (i == 0) {
                            // 车辆在途运抵预判
                            SinoiovlTO sinoiovlTO = sinoiovlApiService.vehicleInPrejudge(
                                    token,
                                    wagonOnMission.getPlateNo(),
                                    stop.getLng(),
                                    stop.getLat()
                            );
                            if (sinoiovlTO != null && ResponseStatusCode.SUCCESS.getCode().equals(sinoiovlTO.getStatus())) {
                                JSONObject jsonObject = JSONObject.fromObject(sinoiovlTO.getResult());
                                RouteStop routeStop = routeStopList.get(i);
                                nextStopId = routeStop.getStopId();
                                eta = sdf.parse((String) jsonObject.get("preTime")).getTime();
                                routeStop.setEta(eta);
                                routeStop.setUpdateTime(System.currentTimeMillis());
                                // 更新下一站预计到达时间
                                routeStopDao.updateRouteEta(routeStop);

                                wagonOnMission.setNextStopETA(eta);
                                String nextStopDistance = (String) jsonObject.get("residueDis");
                                wagonOnMission.setNextStopDistance(
                                        StringUtils.hasText(nextStopDistance) ?
                                                nextStopDistance.substring(0, nextStopDistance.length() - 2) :
                                                "0");
                                wagonOnMission.setNextStopName(stop.getName());

                                // 如果预计时间晚于计划时间 则产生告警
                                if (eta - routeStop.getEta() > 0) {

                                    wagonOnMission.setStatus(0);
                                    // 没有该告警数据
                                    if (warningEventDao.countByRoutePlanIdAndPlateNoAndStatusAndDeleted(
                                            routePlan.getId(),
                                            wagonOnMission.getPlateNo(),
                                            0,
                                            false
                                    ) == 0) {
                                        WarningEvent warningEvent = new WarningEvent();
                                        warningEvent.setRoutePlanId(routePlan.getId());
                                        warningEvent.setPlateNo(wagonOnMission.getPlateNo());
                                        warningEvent.setRoutePlanStopId(routePlan.getStopEndId());
                                        warningEvent.setStopId(stop.getId());
                                        warningEvent.setStopName(stop.getName());
                                        warningEvent.setType(1);
                                        warningEvent.setStatus(0);
                                        warningEvent.setData(String.valueOf(eta));
                                        warningEventDao.save(warningEvent);
                                    } else {
                                        WarningEvent warningEvent = warningEventDao.findByRoutePlanIdAndPlateNo
                                                (
                                                        routePlan.getId(),
                                                        wagonOnMission.getPlateNo()
                                                );

                                        warningEvent.setStopId(stop.getId());
                                        warningEvent.setStopName(stop.getName());
                                        warningEvent.setType(1);
                                        warningEvent.setStatus(0);
                                        warningEvent.setData("晚计到达");
                                        warningEvent.setUpdateTime(System.currentTimeMillis());
                                        warningEventDao.updateWarningEvent(warningEvent);
                                    }

                                }
                            }
                        } else if (i == routeStopList.size() - 1) {
                            // 下一站与终点站一样,只需调用一次车辆在途运抵接口,反之还需调用获取终点站的预计到达时间
                            if (!nextStopId.equals(routeStopList.get(i).getStopId())) {
                                Stop lastStop = stopDao.findByIdAndDeleted(routeStopList.get(i).getStopId(), false);
                                if (lastStop != null) {
                                    // 车辆在途运抵预判
                                    SinoiovlTO sinoiovlTO = sinoiovlApiService.vehicleInPrejudge(
                                            token,
                                            wagonOnMission.getPlateNo(),
                                            lastStop.getLng(),
                                            lastStop.getLat()
                                    );
                                    if (sinoiovlTO != null && ResponseStatusCode.SUCCESS.getCode().equals(sinoiovlTO.getStatus())) {
                                        JSONObject jsonObject = JSONObject.fromObject(sinoiovlTO.getResult());

                                        long lastEta = sdf.parse((String) jsonObject.get("preTime")).getTime();
                                        wagonOnMission.setLastStopETA(lastEta);
                                        String lastStopDistance = (String) jsonObject.get("residueDis");
                                        wagonOnMission.setLastStopDistance(
                                                StringUtils.hasText(lastStopDistance) ?
                                                        lastStopDistance.substring(0, lastStopDistance.length() - 2) :
                                                        "0");
                                        RouteStop routeStop = routeStopList.get(i);
                                        // 如果预计时间晚于计划时间 则产生告警
                                        if (lastEta - routeStop.getEta() > 0) {
                                            wagonOnMission.setStatus(0);
                                            // 没有该告警数据
                                            if (warningEventDao.countByRoutePlanIdAndPlateNoAndStatusAndDeleted(
                                                    routePlan.getId(),
                                                    wagonOnMission.getPlateNo(),
                                                    0,
                                                    false
                                            ) == 0) {
                                                WarningEvent warningEvent = new WarningEvent();
                                                warningEvent.setRoutePlanId(routePlan.getId());
                                                warningEvent.setPlateNo(wagonOnMission.getPlateNo());
                                                warningEvent.setRoutePlanStopId(routePlan.getStopEndId());
                                                warningEvent.setStopId(stop.getId());
                                                warningEvent.setStopName(stop.getName());
                                                warningEvent.setType(1);
                                                warningEvent.setStatus(0);
                                                warningEvent.setData(String.valueOf(lastEta));
                                                warningEventDao.save(warningEvent);
                                            } else {
                                                WarningEvent warningEvent = warningEventDao.findByRoutePlanIdAndPlateNo
                                                        (
                                                                routePlan.getId(),
                                                                wagonOnMission.getPlateNo()
                                                        );

                                                warningEvent.setStopId(stop.getId());
                                                warningEvent.setStopName(stop.getName());
                                                warningEvent.setType(1);
                                                warningEvent.setStatus(0);
                                                warningEvent.setData("晚预计到达");
                                                warningEvent.setUpdateTime(System.currentTimeMillis());
                                                warningEventDao.updateWarningEvent(warningEvent);
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    } else {
                        // 此为下一站也是最后一站
                        Stop stop = stopDao.findByIdAndDeleted(routeStopList.get(i).getStopId(), false);
                        if (stop == null) {
                            continue;
                        }
                        // 车辆在途运抵预判
                        SinoiovlTO sinoiovlTO = sinoiovlApiService.vehicleInPrejudge(
                                token,
                                wagonOnMission.getPlateNo(),
                                stop.getLng(),
                                stop.getLat()
                        );
                        if (sinoiovlTO != null && ResponseStatusCode.SUCCESS.getCode().equals(sinoiovlTO.getStatus())) {
                            JSONObject jsonObject = JSONObject.fromObject(sinoiovlTO.getResult());
                            RouteStop routeStop = routeStopList.get(i);
                            eta = sdf.parse((String) jsonObject.get("preTime")).getTime();
                            routeStop.setEta(eta);
                            routeStop.setUpdateTime(System.currentTimeMillis());
                            // 更新下一站预计到达时间
                            routeStopDao.updateRouteEta(routeStop);

                            wagonOnMission.setNextStopETA(eta);
                            String nextStopDistance = (String) jsonObject.get("residueDis");
                            wagonOnMission.setNextStopDistance(
                                    StringUtils.hasText(nextStopDistance) ?
                                            nextStopDistance.substring(0, nextStopDistance.length() - 2) :
                                            "0");
                            wagonOnMission.setNextStopName(stop.getName());
                            wagonOnMission.setLastStopETA(eta);
                            String lastStopDistance = (String) jsonObject.get("residueDis");
                            wagonOnMission.setLastStopDistance(
                                    StringUtils.hasText(lastStopDistance) ?
                                            lastStopDistance.substring(0, lastStopDistance.length() - 2) :
                                            "0");
                            // 更新任务车辆下一站及终点站信息
                            wagonOnMissionDao.updateStopInfo(wagonOnMission);
                            // 如果预计时间晚于计划时间 则产生告警
                            if (eta - routeStop.getEta() > 0) {
                                wagonOnMission.setStatus(0);
                                // 没有该告警数据
                                if (warningEventDao.countByRoutePlanIdAndPlateNoAndStatusAndDeleted(
                                        routePlan.getId(),
                                        wagonOnMission.getPlateNo(),
                                        0,
                                        false
                                ) == 0) {
                                    WarningEvent warningEvent = new WarningEvent();
                                    warningEvent.setRoutePlanId(routePlan.getId());
                                    warningEvent.setPlateNo(wagonOnMission.getPlateNo());
                                    warningEvent.setRoutePlanStopId(routePlan.getStopEndId());
                                    warningEvent.setStopId(stop.getId());
                                    warningEvent.setStopName(stop.getName());
                                    warningEvent.setType(1);
                                    warningEvent.setStatus(0);
                                    warningEvent.setData(String.valueOf(eta));
                                    warningEventDao.save(warningEvent);
                                } else {
                                    WarningEvent warningEvent = warningEventDao.findByRoutePlanIdAndPlateNo
                                            (
                                                    routePlan.getId(),
                                                    wagonOnMission.getPlateNo()
                                            );

                                    warningEvent.setStopId(stop.getId());
                                    warningEvent.setStopName(stop.getName());
                                    warningEvent.setType(1);
                                    warningEvent.setStatus(0);
                                    warningEvent.setData("晚计划到达");
                                    warningEvent.setUpdateTime(System.currentTimeMillis());
                                    warningEventDao.updateWarningEvent(warningEvent);
                                }
                            }
                        }
                    }
                }
                // 更新任务车辆下一站及终点站信息
                wagonOnMission.setUpdateTime(System.currentTimeMillis());
                wagonOnMissionDao.updateStopInfo(wagonOnMission);
            }
        }
    }


    /**
     * @return void
     * @Description 更新任务车辆进出围栏事件
     * 1: 获取所有回调的结果
     * 2: 通过areaId获取stopId,再获取RouteStop
     * 3: 更新routeStop状态信息
     * 4: a.进区域判断是否是最后一站(如果是则更新完成运单,移除任务车辆,再判断若晚于计划事件则添加告警事件,同时更新站点状态)
     * b.区域判断若晚于计划事件则添加告警事件,同时更新站点状态
     **/
    @Scheduled(cron = "*/15 * * * * ?")
    public void updateWagonEvent() throws Exception {
        // 车辆进出围栏时间回调
        List<WagonEventNotice> wagonEventNoticeList = wagonEventNoticeDao.findByDeleted(false);
        if (wagonEventNoticeList.size() > 0) {
            for (WagonEventNotice wagonEventNotice : wagonEventNoticeList) {

                // 1:进区域 2:出区域
                if ("1".equals(wagonEventNotice.getType())) {
                    // 获取站点详细信息
                    Stop currentStop = stopDao.findByInAreaId(wagonEventNotice.getAreaId());
                    if (currentStop == null) {
                        continue;
                    }
                    RouteStop currentRouteStop = routeStopDao.findByStopIdAndDeleted(currentStop.getId(), false);
                    if (currentRouteStop == null) {
                        continue;
                    }
                    RoutePlan currentRoutePlan = routePlanDao.findByIdAndDeleted(currentRouteStop.getRoutePlanId(), false);
                    if (currentRoutePlan == null) {
                        continue;
                    }

                    WagonOnMission wagonOnMission = wagonOnMissionDao.findByRoutePlanIdAndPlateNo(
                            currentRoutePlan.getId(),
                            wagonEventNotice.getVno()
                    );
                    if (wagonOnMission == null) {
                        continue;
                    }

                    // 添加站点事件
                    StopEvent stopEvent = new StopEvent();
                    stopEvent.setRouteStopId(currentRouteStop.getId());
                    stopEvent.setEventTime(Long.parseLong(wagonEventNotice.getUtc()));
                    stopEvent.setEventType(Integer.parseInt(wagonEventNotice.getType()));
                    stopEvent.setPlateNo(wagonEventNotice.getVno());
                    stopEventDao.save(stopEvent);

                    // 更新进站信息 (只有状态是未到站,才会更新数据,重入站不处理)
                    if ("0".equals(currentRouteStop.getStatus())) {

                        // 晚于计划到达时间
                        if (Long.parseLong(wagonEventNotice.getUtc()) - currentRouteStop.getPta() > 0) {
                            WarningEvent warningEvent = new WarningEvent();
                            warningEvent.setRoutePlanId(currentRoutePlan.getId());
                            warningEvent.setPlateNo(wagonEventNotice.getVno());
                            warningEvent.setRoutePlanStopId(currentRoutePlan.getStopEndId());
                            warningEvent.setStopId(currentStop.getId());
                            warningEvent.setStopName(currentStop.getName());
                            warningEvent.setType(1);
                            warningEvent.setStatus(0);
                            warningEvent.setData("晚计划到达");
                            warningEventDao.save(warningEvent);

                            // 更新状态为告警状态
                            if (wagonOnMission.getStatus() == 1) {
                                wagonOnMission.setStatus(0);
                                wagonOnMission.setUpdateTime(System.currentTimeMillis());
                                wagonOnMissionDao.updateWagonOnMissionStatus(wagonOnMission);
                            }

                        }

                        // 更新站点信息
                        routeStopDao.updateRouteStopInfo
                                (
                                        Long.parseLong(wagonEventNotice.getUtc()),
                                        null,
                                        "1",
                                        System.currentTimeMillis(),
                                        currentRouteStop.getId()
                                );

                        // 进入终点站
                        if (currentStop.getId().equals(currentRoutePlan.getStopEndId())) {

                            // 更新班线状态为已完成
                            currentRoutePlan.setStatus("2");
                            currentRoutePlan.setUpdateTime(System.currentTimeMillis());
                            routePlanDao.finishedRoutePlan(currentRoutePlan);

                            // 删除任务车辆
                            wagonOnMissionDao.deleteWagonOnMission(System.currentTimeMillis(), wagonOnMission.getId());

                            // 说明所有班线都已经结束了
                            if (routePlanDao.countUnfinished(currentRoutePlan.getWayBillId()) <= 0) {
                                // 更新运单完成
                                WayBill wayBill = wayBillDao.findByBookingNo(wagonOnMission.getBookingNo());
                                if (wayBill == null) {
                                    continue;
                                }
                                // 运单完成结束
                                wayBillDao.completeWayBill(System.currentTimeMillis(), wayBill.getId());

                                // 获取运单下的所有班线
                                List<RoutePlan> routePlanList = routePlanDao.findByWayBillId(wayBill.getId());
                                if (routePlanList.size() <= 0) {
                                    continue;
                                }

                                String token = tokenService.getToken();
                                for (RoutePlan routePlan : routePlanList) {
                                    List<RouteStop> stopList = routeStopDao.findByRoutePlanId(routePlan.getId());
                                    if (stopList.size() <= 0) {
                                        continue;
                                    }
                                    for (RouteStop routeStop : stopList) {
                                        Stop stop = stopDao.findById(routeStop.getStopId());
                                        if (stop == null) {
                                            continue;
                                        }

                                        // 当运单进入终点站时 删除围栏的进出事件
                                        if (StringUtils.hasText(stop.getInAreaId())) {
                                            SinoiovlTO sinoiovlTO = sinoiovlApiService.inDelVehicleSubscription(
                                                    token,
                                                    wagonOnMission.getPlateNo(),
                                                    stop.getInAreaId()
                                            );
                                        }
                                        if (StringUtils.hasText(stop.getOutAreaId())) {
                                            SinoiovlTO sinoiovlTO = sinoiovlApiService.outDelVehicleSubscription(
                                                    token,
                                                    wagonOnMission.getPlateNo(),
                                                    stop.getOutAreaId()
                                            );
                                        }
                                    }
                                }
                            }
                        }
                    }

                } else {

                    // 获取站点详细信息
                    Stop stop = stopDao.findByOutAreaId(wagonEventNotice.getAreaId());
                    if (stop == null) {
                        continue;
                    }
                    RouteStop routeStop = routeStopDao.findByStopIdAndDeleted(stop.getId(), false);
                    if (routeStop == null) {
                        continue;
                    }
                    RoutePlan routePlan = routePlanDao.findByIdAndDeleted(routeStop.getRoutePlanId(), false);
                    if (routePlan == null) {
                        continue;
                    }

                    WagonOnMission wagonOnMission = wagonOnMissionDao.findByRoutePlanIdAndPlateNo(
                            routePlan.getId(),
                            wagonEventNotice.getVno()
                    );
                    if (wagonOnMission == null) {
                        continue;
                    }

                    // 添加站点事件
                    StopEvent stopEvent = new StopEvent();
                    stopEvent.setRouteStopId(routeStop.getId());
                    stopEvent.setEventTime(Long.parseLong(wagonEventNotice.getUtc()));
                    stopEvent.setEventType(Integer.parseInt(wagonEventNotice.getType()));
                    stopEvent.setPlateNo(wagonEventNotice.getVno());
                    stopEventDao.save(stopEvent);

                    // 更新出站信息 (只有原状态是已到站,才会更新数据,重出站不处理)
                    if ("1".equals(routeStop.getStatus())) {

                        // 晚于计划离开时间
                        if (Long.parseLong(wagonEventNotice.getUtc()) - routeStop.getPtd() > 0) {
                            WarningEvent warningEvent = new WarningEvent();
                            warningEvent.setRoutePlanId(routePlan.getId());
                            warningEvent.setPlateNo(wagonEventNotice.getVno());
                            warningEvent.setRoutePlanStopId(routePlan.getStopEndId());
                            warningEvent.setStopId(stop.getId());
                            warningEvent.setStopName(stop.getName());
                            warningEvent.setType(2);
                            warningEvent.setStatus(0);
                            warningEvent.setData("晚计划出发");
                            warningEventDao.save(warningEvent);

                            // 更新状态为告警状态
                            if (wagonOnMission.getStatus() == 1) {
                                wagonOnMission.setStatus(0);
                                wagonOnMission.setUpdateTime(System.currentTimeMillis());
                                wagonOnMissionDao.updateWagonOnMissionStatus(wagonOnMission);
                            }
                        }

                        routeStopDao.updateRouteStopInfo
                                (
                                        null,
                                        Long.parseLong(wagonEventNotice.getUtc()),
                                        "2",
                                        System.currentTimeMillis(),
                                        routeStop.getId()
                                );

                    }

                }

            }

        }

    }

}
