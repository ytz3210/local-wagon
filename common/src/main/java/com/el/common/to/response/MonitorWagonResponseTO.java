package com.el.common.to.response;

import com.el.entity.WagonOnMission;
import com.el.entity.WarningEvent;

import java.io.Serializable;
import java.util.List;

/**
 * @author ZhangJun
 * @Description: 班线监控
 * @create 2019-09-25 11:06
 */
public class MonitorWagonResponseTO implements Serializable {

    /**
     * 所有车辆
     */
    List<WagonOnMission> allMonitorWagon;

    /**
     * 车辆监控在线
     */
    List<WagonOnMission> wagonOnline;

    /**
     * 车辆监控离线
     */
    List<WagonOnMission> wagonOffline;

    /**
     * 车辆监控警告
     */
    List<WarningEvent> warningEvents;

    public List<WagonOnMission> getAllMonitorWagon() {
        return allMonitorWagon;
    }

    public void setAllMonitorWagon(List<WagonOnMission> allMonitorWagon) {
        this.allMonitorWagon = allMonitorWagon;
    }

    public List<WagonOnMission> getWagonOnline() {
        return wagonOnline;
    }

    public void setWagonOnline(List<WagonOnMission> wagonOnline) {
        this.wagonOnline = wagonOnline;
    }

    public List<WagonOnMission> getWagonOffline() {
        return wagonOffline;
    }

    public void setWagonOffline(List<WagonOnMission> wagonOffline) {
        this.wagonOffline = wagonOffline;
    }

    public List<WarningEvent> getWarningEvents() {
        return warningEvents;
    }

    public void setWarningEvents(List<WarningEvent> warningEvents) {
        this.warningEvents = warningEvents;
    }
}
