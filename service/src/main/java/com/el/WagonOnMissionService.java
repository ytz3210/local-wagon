package com.el;

import com.el.common.source.ResTO;

public interface WagonOnMissionService {

    /**
     * @param bookingNo 提单号
     * @return com.el.common.source.ResTO
     * @Description 获取任务中的车辆信息
     **/
    ResTO getWagonOnMissions(String bookingNo);

    /**
     * @param warningEventId 告警事件id
     * @return com.el.common.source.ResTO
     * @Description 忽略处理告警事件
     **/
    ResTO ignoreWarningEvent(String warningEventId);

    ResTO getMissionLSD(Integer hour);
}
