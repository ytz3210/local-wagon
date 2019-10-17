package com.el.impl;

import com.el.WagonOnMissionService;
import com.el.common.source.REnum;
import com.el.common.source.ResTO;
import com.el.common.to.MissionViewTO;
import com.el.common.to.response.MonitorWagonResponseTO;
import com.el.common.utils.CommonUtils;
import com.el.common.utils.RUtil;
import com.el.dao.WagonOnMissionDao;
import com.el.dao.WarningEventDao;
import com.el.entity.WagonOnMission;
import com.el.entity.WarningEvent;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ZhangJun
 * @Description: 描述这个类的作用
 * @create 2019-09-25 17:19
 */
@Service
public class WagonOnMissionServiceImpl implements WagonOnMissionService {

    @Autowired
    private WagonOnMissionDao wagonOnMissionDao;

    @Autowired
    private WarningEventDao warningEventDao;

    @Override
    public ResTO getWagonOnMissions(String bookingNo) {

        List<WagonOnMission> wagonOnMissionList = wagonOnMissionDao.getWagonOnMissions(bookingNo, null);
        MonitorWagonResponseTO monitorWagonResponseTO = new MonitorWagonResponseTO();
        List<WagonOnMission> wagonOnlineList = new ArrayList<WagonOnMission>();
        List<WagonOnMission> wagonOfflineList = new ArrayList<WagonOnMission>();
        List<WarningEvent> warningEventList = new ArrayList<WarningEvent>();
        for (WagonOnMission wagonOnMission : wagonOnMissionList) {
            if (wagonOnMission.getOnline() == true) {
                wagonOnlineList.add(wagonOnMission);
            } else {
                wagonOfflineList.add(wagonOnMission);
            }
            warningEventList.addAll(warningEventDao.findAllByRoutePlanIdAndPlateNoAndDeleted(wagonOnMission.getRoutePlanId(), wagonOnMission.getPlateNo()));
        }
        monitorWagonResponseTO.setAllMonitorWagon(wagonOnMissionList);
        monitorWagonResponseTO.setWagonOnline(wagonOnlineList);
        monitorWagonResponseTO.setWagonOffline(wagonOfflineList);
        monitorWagonResponseTO.setWarningEvents(warningEventList);
        return RUtil.success(monitorWagonResponseTO);
    }

    @Override
    public ResTO ignoreWarningEvent(String warningEventId) {

        if (StringUtils.isEmpty(warningEventId)) {
            return RUtil.errorByCode(REnum.NO_WARNING_EVENT_ID.getCode());
        }

        if (warningEventDao.ignoreWarningEvent(warningEventId) > 0) {
            return RUtil.success();
        } else {
            return RUtil.errorByCode(REnum.OPERATION_FAILED.getCode());
        }
    }

    @Override
    public ResTO getMissionLSD(Integer hour) {
        List<MissionViewTO> missionViewTOList = new LinkedList<>();
        Long id = 1L;
        for (WagonOnMission wagonOnMission : wagonOnMissionDao.findMission()) {
            MissionViewTO missionViewTO = new MissionViewTO();
            BeanUtils.copyProperties(wagonOnMission, missionViewTO);

            /**
             * 里程进度
             */
            if (wagonOnMission.getDistance() == null || wagonOnMission.getLastStopDistance() == null) {
                missionViewTO.setMileageProgress(null);
            } else {
                Double distance = Double.parseDouble(wagonOnMission.getDistance());
                Double lastDistance = Double.parseDouble(wagonOnMission.getLastStopDistance());
                Double mileagePlan = ((distance - lastDistance) / distance) * 100;
                if (mileagePlan < 0) {
                    missionViewTO.setMileageProgress(null);
                } else {
                    missionViewTO.setMileageProgress(String.valueOf(Math.round(mileagePlan)));
                }
            }
            /**
             * 预计到达时间延误
             */
            Long pta = Long.valueOf(wagonOnMission.getPta());
            Long currentTime = wagonOnMission.getLastStopETA() - pta;
            Long switchTime = System.currentTimeMillis() - wagonOnMission.getLastStopETA();
            String etaFormat = DateFormatUtils.format(wagonOnMission.getLastStopETA(), "yyyy-MM-dd HH:mm:ss");
            missionViewTO.setLastStopETA(etaFormat);
            String time = null;
            if (currentTime < 0) {
                time = "提前"+CommonUtils.transformDate(0-currentTime);
                missionViewTO.setEad(time);
            } else if(currentTime == 0){
                missionViewTO.setEad("正点");
            }else {
                time = CommonUtils.transformDate(currentTime);
                missionViewTO.setEad(time);
            }
            missionViewTO.setId(id++);
            switch (hour) {
                case 0:
                    missionViewTOList.add(missionViewTO);
                    break;
                case 1:
                    if (switchTime <= 1000 * 60 * 60) {
                        missionViewTOList.add(missionViewTO);
                    }
                    break;
                case 2:
                    if (switchTime <= 1000 * 60 * 60 * 2) {
                        missionViewTOList.add(missionViewTO);
                    }
                    break;
                case 3:
                    if (switchTime <= 1000 * 60 * 60 * 3) {
                        missionViewTOList.add(missionViewTO);
                    }
                    break;
                case 4:
                    if (switchTime <= 1000 * 60 * 60 * 24) {
                        missionViewTOList.add(missionViewTO);
                    }
                    break;
            }
        }
        return RUtil.success(missionViewTOList);
    }
}
