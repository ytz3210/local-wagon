package com.el.dao;

import com.el.entity.Stop;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface StopDao extends CrudRepository<Stop, Integer> {

    Stop findByIdAndDeleted(String id, boolean deleted);

    Stop findByInAreaId(String inAreaId);

    Stop findByOutAreaId(String outAreaId);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update t_stop set is_deleted = true " +
            " where id in (select stop_id from t_route_stop where route_plan_id = ?1)", nativeQuery = true)
    int deleteAllByRoutePlan(String routePlanId);

    Stop findById(String id);
}