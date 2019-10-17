package com.el.controller;

import com.el.WagonOnMissionService;
import com.el.common.source.ResTO;
import com.el.task.CustomWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhangJun
 * @Description: 车辆监控
 * @create 2019-09-25 14:59
 */
@RestController
@RequestMapping("api/monitor")
public class MonitorWagonController {
    @Autowired
    private CustomWebSocket customWebSocket;
    @Autowired
    private WagonOnMissionService wagonOnMissionService;


    /**
     * @param bookingNo 提单号
     * @return com.el.common.source.ResTO
     * @Description 通过提单号查询任务车辆及告警事件
     **/
    @GetMapping("wagon")
    public ResTO wagonOnMission(String bookingNo) {
        return wagonOnMissionService.getWagonOnMissions(bookingNo);
    }

    /**
     * @param warningEventId 告警事件id
     * @return com.el.common.source.ResTO
     * @Description 忽略告警事件
     **/
    @DeleteMapping(value = "ignore")
    public ResTO warningEvent(String warningEventId) {
        return wagonOnMissionService.ignoreWarningEvent(warningEventId);
    }

    @GetMapping("/mission_lsd/{hour}")
    public ResTO missionControl(@PathVariable("hour")Integer hour) {
//        customWebSocket.sendMessage(wagonOnMissionService.getMissionLSD().toString());
        return wagonOnMissionService.getMissionLSD(hour);
    }

}
