package com.el.dao;

import com.el.entity.WarningEvent;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZhangJun
 * @Description: 警告事件
 * @create 2019-09-26 15:06
 */
@Repository
public interface WarningEventDao extends CrudRepository<WarningEvent, String> {

    /**
     * @param routePlanId 班线id
     * @param palteNo     车牌号码
     * @return java.util.List<com.el.entity.WarningEvent>
     * @Description 根据班线和车牌获取告警事件
     **/
    @Query("from WarningEvent where deleted = false and status = 0 and routePlanId = ?1 and plateNo = ?2")
    List<WarningEvent> findAllByRoutePlanIdAndPlateNoAndDeleted(String routePlanId, String palteNo);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("update WarningEvent set status = 1 where id = ?1")
    int ignoreWarningEvent(String warningEventId);

    /**
     * @param routePlanId 班线id
     * @param plateNo     车牌号码
     * @param status      状态
     * @param deleted     删除标识
     * @return int
     * @Description 根据参数查询是否含有告警数据
     **/
    int countByRoutePlanIdAndPlateNoAndStatusAndDeleted(String routePlanId, String plateNo, Integer status, Boolean deleted);

    /**
     * @param routePlanId 班线id
     * @param plateNo     车牌号
     * @return com.el.entity.WarningEvent
     * @Description 获取告警内容
     **/
    WarningEvent findByRoutePlanIdAndPlateNo(String routePlanId, String plateNo);

    /**
     * @param warningEvent
     * @return int
     * @Description 更新告警事件
     **/
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update t_warning_event set " +
            " stop_id = :#{#warningEvent.stopId}, " +
            " stop_name = :#{#warningEvent.stopName}, " +
            " type = :#{#warningEvent.type}, " +
            " data = :#{#warningEvent.data}, " +
            " update_time = :#{#warningEvent.updateTime} " +
            " where id = :#{#warningEvent.id} ", nativeQuery = true)
    int updateWarningEvent(WarningEvent warningEvent);
}
