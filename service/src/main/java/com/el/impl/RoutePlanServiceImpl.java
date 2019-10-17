package com.el.impl;

import com.el.RoutePlanService;
import com.el.SinoiovlApiService;
import com.el.TokenService;
import com.el.common.enums.ResponseStatusCode;
import com.el.common.source.REnum;
import com.el.common.source.ResTO;
import com.el.common.to.AppointWagonTeamTO;
import com.el.common.to.RoutePlanTO;
import com.el.common.to.RouteStopMouldTO;
import com.el.common.to.response.SinoiovlTO;
import com.el.common.utils.BDMapApiUtils;
import com.el.common.utils.JsonUtils;
import com.el.common.utils.RUtil;
import com.el.dao.*;
import com.el.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @description: 路线相关业务
 * @author: MaoYe
 * @create: 2019/09/11
 */
@Service
public class RoutePlanServiceImpl implements RoutePlanService {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private SinoiovlApiService sinoiovlApiService;
    @Autowired
    RoutePlanDao routePlanDao;
    @Autowired
    RouteStopDao routeStopDao;
    @Autowired
    RouteStopBillDao routeStopBillDao;
    @Autowired
    StopDao stopDao;
    @Autowired
    SiteDao siteDao;
    @Autowired
    WagonDao wagonDao;
    @Autowired
    WayBillDao wayBillDao;
    @Autowired
    WagonTeamDao wagonTeamDao;
    @Autowired
    DriverDao driverDao;
    @Autowired
    WagonOnMissionDao wagonOnMissionDao;

    @Value("${bdmap-ak}")
    private List<String> aks;

    @Override
    public ResTO addRoutePlan(RouteStopMouldTO to) {
        RoutePlan routePlan = new RoutePlan();
        routePlan.setWayBillId(to.getId());
        routePlan.setStatus("0");
        routePlanDao.save(routePlan);

        List<RouteStop> routeStopList = new ArrayList<>();
        routeStopList.addAll(this.saveRouteStop(routePlan.getId(), to));
        if(routeStopList != null) {
            routeStopDao.saveAll(routeStopList);
        }
        return RUtil.success();
    }

    @Override
    public ResTO addRoutePlanByBill(RouteStopMouldTO to) {
        this.saveRouteMould(to);

        // 保存车队模板
        WayBill wayBill = wayBillDao.findByIdAndDeleted(to.getId(), false);
        wayBill.setWagonTeamJson(to.getWagonTeamJson());
        List<Map> wagonTeamList = JsonUtils.fromJson(to.getWagonTeamJson(), List.class);

        // 根据运单查找路线
        List<RoutePlan> routePlanList = routePlanDao.findAllByWayBillIdAndDeleted(to.getId(), false);
        List<RouteStop> routeStopList = new ArrayList<>();
        for (RoutePlan routePlan : routePlanList) {
            routeStopList.addAll(this.saveRouteStop(routePlan.getId(), to));

            for (Map wagonTeam: wagonTeamList) {
                if(routePlan.getWagonTeamId() == null){
                    if("0".equals(wagonTeam.get("quality")) || "".equals(wagonTeam.get("wagonTeamId") )) {
                        continue;
                    }
                    routePlan.setWagonTeamId(""+wagonTeam.get("wagonTeamId"));
                    wagonTeam.put("quality", ""+(Integer.valueOf(""+wagonTeam.get("quality"))-1));
                }
                break;
            }

        }
        if(routeStopList != null) {
            routeStopDao.saveAll(routeStopList);
        }
        return RUtil.success();
    }

    /**
     * 封装路线信息
     */
    private List<RouteStop> saveRouteStop(String routeId, RouteStopMouldTO to) {
        List<RouteStop> routeStopList = new ArrayList<>();
        // 保存起点围栏快照
        if (to.getStartStop() != null) {
            routeStopList.add(saveRouteStop(to.getStartStop(), routeId, to.getStartStopBegin(), to.getStartStopEnd(), "1"));
        }

        List<Map> transitList = JsonUtils.fromJson(to.getTransitInfo(), List.class);
        int s = 2;
        // 遍历中转站 保存快照
        for (Map transit : transitList) {
            if (transit.get("transit") == null) {
                continue;
            }
            routeStopList.add(saveRouteStop(transit.get("transit").toString(), routeId,
                    transit.get("transitBegin")==null?0:Long.parseLong(""+transit.get("transitBegin")),
                    transit.get("transitEnd")==null?0:Long.parseLong(""+transit.get("transitEnd")), ""+(s++)));
        }

        // 保存终点围栏快照
        if (to.getEndStop() != null) {
            routeStopList.add(saveRouteStop(to.getEndStop(), routeId, to.getEndStopBegin(), to.getEndStopEnd(), "90"));
        }

        // 计算下一站里程 若只设置了1个站点则跳过
        if (routeStopList.size() > 1) {
            for (int i = 0; i < routeStopList.size(); i++) {
                RouteStop routeStop = routeStopList.get(i);
                if("90".equals(routeStop.getSort())) {
                    routeStop.setNextStopDistance("0");
                } else {
                    RouteStop nextRouteStop = routeStopList.get(i+1);
                    Stop stop = stopDao.findByIdAndDeleted(routeStop.getStopId(), false);
                    Stop nextStop = stopDao.findByIdAndDeleted(nextRouteStop.getStopId(), false);
                    ResTO resTO = BDMapApiUtils.calculatedDistance(stop.getLat()+","+stop.getLng(),
                            nextStop.getLat()+","+nextStop.getLng(), aks);
                    routeStop.setNextStopDistance(resTO.getData().toString());
                }
            }
        }
        return routeStopList;
    }

    /**
     * 保存模板
     */
    private void saveRouteMould(RouteStopMouldTO to){
        List<RouteStopBill> routeStopBillList = new ArrayList<>();
        // 保存模板起点围栏快照
        if (to.getStartStop() != null) {
            routeStopBillList.add(saveBillByStop(to.getStartStop(), to.getId(), to.getStartStopBegin(), to.getStartStopEnd(), "1"));
        }

        // 保存模板终点围栏快照
        if (to.getEndStop() != null) {
            routeStopBillList.add(saveBillByStop(to.getEndStop(), to.getId(), to.getEndStopBegin(), to.getEndStopEnd(), "90"));
        }

        List<Map> transitList = JsonUtils.fromJson(to.getTransitInfo(), List.class);
        int s = 2;
        // 遍历中转站 保存模板快照
        for (Map transit : transitList) {
            if (transit.get("transit") == null) {
                continue;
            }
            routeStopBillList.add(saveBillByStop(transit.get("transit").toString(), to.getId(),
                    transit.get("transitBegin")==null?0:Long.parseLong(""+transit.get("transitBegin")),
                    transit.get("transitEnd")==null?0:Long.parseLong(""+transit.get("transitEnd")), ""+(s++)));
        }

        if(routeStopBillList != null) {
            routeStopBillDao.saveAll(routeStopBillList);
        }
    }

    /**
     * 保存路线模板信息
     */
    private RouteStopBill saveBillByStop(String stopId, String wayBillId, long startTime, long endTime, String sort) {
        Stop stop = new Stop();
        Site site = siteDao.findByIdAndDeleted(stopId, false);
        BeanUtils.copyProperties(site, stop);
        stop.setSiteId(site.getId());
        Stop s = stopDao.save(stop);

        RouteStopBill routeStopBill = new RouteStopBill();
        routeStopBill.setWayBillId(wayBillId);
        routeStopBill.setPta(startTime);
        routeStopBill.setPtd(endTime);
        routeStopBill.setStopId(s.getId());
        routeStopBill.setSort(sort);
        return routeStopBill;
    }

    /**
     * 保存路线信息
     */
    private RouteStop saveRouteStop(String stopId, String planId, long startTime, long endTime, String sort) {
        Stop stop = new Stop();
        Site site = siteDao.findByIdAndDeleted(stopId, false);
        BeanUtils.copyProperties(site, stop);
        stop.setSiteId(site.getId());
        Stop s = stopDao.save(stop);

        RouteStop routeStop = new RouteStop();
        routeStop.setRoutePlanId(planId);
        routeStop.setStopId(s.getId());

        if("1".equals(sort)) {
            routeStop.setCurrent(true);
        } else {
            routeStop.setCurrent(false);
        }
        routeStop.setPta(startTime);
        routeStop.setPtd(endTime);
        routeStop.setSort(sort);
        routeStop.setStatus("0");
        return routeStop;
    }

    @Override
    public ResTO editRoutePlan(RouteStopMouldTO to) {
        RoutePlan routePlan = routePlanDao.findByIdAndDeleted(to.getRoutePlanId(), false);
        if (routePlan == null) {
            return RUtil.error(REnum.ROUTE_PLAN_NOT_EXISTS);
        }
        stopDao.deleteAllByRoutePlan(to.getRoutePlanId());
        routeStopDao.deleteAllByRoutePlan(to.getRoutePlanId());
        List<RouteStop> routeStopList = new ArrayList<>();
        routeStopList.addAll(this.saveRouteStop(to.getRoutePlanId(), to));
        if(routeStopList != null) {
            routeStopDao.saveAll(routeStopList);
        }
        return RUtil.success();
    }

    @Override
    public ResTO editContainer(RoutePlanTO to) {
        RoutePlan routePlan = routePlanDao.findByIdAndDeleted(to.getId(), false);
        if (routePlan == null) {
            return RUtil.error(REnum.ROUTE_PLAN_NOT_EXISTS);
        }
        routePlan.setContainerNo(to.getContainerNo());
        routePlan.setContainerOwner(to.getContainerOwner());
        routePlan.setContainerType(to.getContainerType());
        routePlan.setSealNumber(to.getSealNumber());
        routePlan.setGrossWeight(to.getGrossWeight());
        routePlan.setTareWeight(to.getTareWeight());
        routePlan.setNetWeight(to.getNetWeight());
        routePlan.setCubeCapacity(to.getCubeCapacity());
        return RUtil.success();
    }

    @Override
    public ResTO delRoutePlan(String routePlanId) {
        if (!routePlanDao.existsById(routePlanId)) {
            return RUtil.error(REnum.ROUTE_PLAN_NOT_EXISTS);
        }

        if (routePlanDao.deleteRoutePlan(routePlanId) > 0) {
            return RUtil.success();
        }
        return RUtil.error(REnum.DELETE_FAILED);
    }

    @Override
    public ResTO loadRoutePlans(String wayBillId, Pageable pageable) {
        return RUtil.successByPage(routePlanDao.find(wayBillId, pageable));
    }

    @Override
    public ResTO getAllSitesByWayBill(String wayBillId) {
        List<Map> routePlanList = routePlanDao.find(wayBillId);

        List<Map> resList = new ArrayList<>();
        for (Map routePlan : routePlanList) {
            List<Map> stopList = routeStopDao.findAllByRoutePlanIdAndDeletedAndSortBetween(routePlan.get("id").toString());
            String jsonStr = JsonUtils.toJson(stopList);
            Map map = new HashMap();
            map.putAll(routePlan);
            map.put("transitInfo", jsonStr);
            if (stopList.size() > 0){
                map.put("shipmentEndDate", stopList.get(0).get("transitEnd"));
            }
            resList.add(map);
        }
        return RUtil.success(resList);
    }

    @Override
    public ResTO bandWagon(RoutePlanTO to) {
        RoutePlan routePlan = routePlanDao.findByIdAndDeleted(to.getId(), false);
        if(routePlan == null) {
            return RUtil.error(REnum.ROUTE_PLAN_NOT_EXISTS);
        }

        Wagon wagon = wagonDao.findByIdAndDeleted(to.getWagonId(), false);
        routePlan.setPlateNo(wagon.getPlateNo());
        routePlan.setWagonId(wagon.getId());
        routePlan.setWagonTeamId(wagon.getWagonTeamId());
        routePlan.setStatus("1");
        return RUtil.success();
    }

    /**
     * 指派车队
     * 如果车队与车辆都已经指派则将路线状态改为未完成
     *
     * @return
     */
    @Override
    public ResTO appointWagonTeam(AppointWagonTeamTO to) {
        List<RoutePlan> routePlanList =
                routePlanDao.findAllByIdIn(JsonUtils.fromJson(to.getIds(), List.class));
        for (RoutePlan routePlan : routePlanList) {
            routePlan.setWagonTeamId(to.getWagonTeamId());
            if(routePlan.getWagonId() != null) {
                routePlan.setStatus("1");
            }
        }
        return RUtil.success();
    }

    /**
     * 任务开始
     * 将运单状态改为未完成
     * 设置RoutePlan的开始站点与结束站点
     * 生成onMission数据
     * 调用订阅时间接口
     * @return
     */
    @Override
    public ResTO missionStart(String id) throws Exception {
        String token = tokenService.getToken();
        RoutePlan routePlan = routePlanDao.findByIdAndDeleted(id, false);
        if (routePlan == null) {
            return RUtil.error(REnum.ROUTE_PLAN_NOT_EXISTS);
        }
        if (!"1".equals(routePlan.getStatus())) {
            return RUtil.error(REnum.ROUTE_PLAN_STATUS_ERR);
        }

        WayBill wayBill = wayBillDao.findByIdAndDeleted(routePlan.getWayBillId(), false);
        if (wayBill == null) {
            return RUtil.error(REnum.WAYBILL_NOT_EXISTS);
        }

        if (routePlan.getPlateNo() == null) {
            return RUtil.error(REnum.NOT_BAND_WAGON);
        }

        WagonTeam wagonTeam = null;
        if (routePlan.getWagonTeamId() != null) {
            wagonTeam = wagonTeamDao.findByIdAndDeleted(routePlan.getWagonTeamId(), false);
        }

        WagonOnMission wagonOnMission = new WagonOnMission();
        wagonOnMission.setBookingNo(wayBill.getBookingNo());
        wagonOnMission.setRoutePlanId(routePlan.getId());
        wagonOnMission.setPlateNo(routePlan.getPlateNo());
        Map driverMap = driverDao.findDriverByplateNo(routePlan.getPlateNo());
        wagonOnMission.setDriverName(""+driverMap.get("name"));
        wagonOnMission.setDriverPhone(""+driverMap.get("phoneNo"));
        wagonOnMission.setTeamName(wagonTeam == null? "" : wagonTeam.getName());
        wagonOnMission.setSealNumber(routePlan.getSealNumber());

        // 设置路线的开始站点与结束站点
        String inAreaId = "";
        String outAreaId = "";

        BigDecimal distance = new BigDecimal("0");
        List<RouteStop> routeStopList = routeStopDao.findByRoutePlanId(routePlan.getId());

        // 至少需要开始站点、结束站点与1个中转站
        int cnt = 0;
        for (RouteStop stop: routeStopList) {
            if("1".equals(stop.getSort()) || "2".equals(stop.getSort()) || "90".equals(stop.getSort())){
                cnt++;
            }
        }
        if (cnt !=3) {
            return RUtil.error(REnum.ROUTE_PLAN_SET_ERR);
        }

        for (RouteStop stop: routeStopList) {
            distance = distance.add(new BigDecimal(""+stop.getNextStopDistance()));
            Stop s = stopDao.findByIdAndDeleted(stop.getStopId(), false);
            if ("1".equals(stop.getSort())) {
                routePlan.setActivation(true);
                routePlan.setStopStartId(stop.getId());
            }

            if ("2".equals(stop.getSort())) {
                wagonOnMission.setNextStopPTA(stop.getPta());
                wagonOnMission.setNextStopName(s.getName());
            }

            if ("90".equals(stop.getSort())) {
                routePlan.setStopEndId(stop.getId());
                wagonOnMission.setPta(stop.getPta());
            }

            // 围栏绑定车辆
            // 订阅车辆进围栏
            SinoiovlTO inSinoiovlTo = sinoiovlApiService.inVehicleSubscription(
                    token, wagonOnMission.getPlateNo() + "_2", s.getInAreaId()
            );

            if (inSinoiovlTo == null || !ResponseStatusCode.SUCCESS.getCode().equals(inSinoiovlTo.getStatus())) {
                return RUtil.errorByCode(REnum.OPERATION_FAILED.getCode());
            }

            // 订阅车辆出围栏
            SinoiovlTO outSinoiovlTo = sinoiovlApiService.outVehicleSubscription(
                    token, wagonOnMission.getPlateNo() + "_2", s.getOutAreaId()
            );
            if (outSinoiovlTo == null || !ResponseStatusCode.SUCCESS.getCode().equals(outSinoiovlTo.getStatus())) {
                return RUtil.errorByCode(REnum.OPERATION_FAILED.getCode());
            }
        }
        routePlan.setDistance(""+distance);
        routePlan.setStatus("2");
        wagonOnMission.setDistance(""+distance);
        wagonOnMission.setLastStopDistance(""+distance);
        wagonOnMissionDao.save(wagonOnMission);
        return RUtil.success();
    }
}