package com.el.dao;

import com.el.entity.RoutePlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface RoutePlanDao extends CrudRepository<RoutePlan, Integer> {

    RoutePlan findByIdAndDeleted(String routePlanId, boolean deleted);

    @Query(value = "select * from t_route_plan where is_deleted = false" +
            " and if(?1 !='', way_bill_id like %?1%, 1=1) " +
            " order by create_time desc", nativeQuery = true)
    Page<RoutePlan> find(String wayBillId, Pageable pageable);

    boolean existsById(String routePlanId);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update RoutePlan set deleted = true where Id=?1")
    Integer deleteRoutePlan(String routePlanId);

    @Query(value = " select a.id, a.plate_no, b.pta startStopBegin, b.ptd startStopEnd, c.pta endStopBegin, c.ptd endStopEnd, " +
                   "  a.seal_number sealNumber, a.container_type containerType, a.container_no containerNo, " +
                   "  a.container_owner containerOwner, a.gross_weight grossWeight, a.tare_weight tareWeight, " +
                   "  a.net_weight netWeight, cube_capacity cubeCapacity, b.site_id startStop, b.name startStopName, " +
                   "  c.site_id endStop, c.name endStopName, " +
                   "  (select name from t_wagon_team where id = a.wagon_team_id) wagonTeamName, " +
                   "  (select name from t_driver where id = d.driver_id) driverName, " +
                   "  (select phone_no from t_driver where id = d.driver_id) phoneNo, a.status" +
                   "  from t_route_plan a " +
                   "  left join (select trs.*, ts.site_id, ts.name from t_route_stop trs, t_stop ts " +
                   "    where trs.is_deleted = false and ts.is_deleted = false and trs.sort='1' and trs.stop_id = ts.id " +
                   "  ) b on a.id = b.route_plan_id " +
                   "  left join (select trs.*, ts.site_id, ts.name from t_route_stop trs, t_stop ts " +
                   "    where trs.is_deleted = false and ts.is_deleted = false and trs.sort='90' and trs.stop_id = ts.id " +
                   "  ) c on a.id = c.route_plan_id " +
                   "  left join t_wagon d on a.wagon_id = d.id and d.is_deleted = false" +
                   " where a.is_deleted = false and a.way_bill_id = ?1" +
                   " order by a.create_time", nativeQuery = true)
    List<Map> find(String wayBillId);

    List<RoutePlan> findAllByWayBillIdAndDeleted(String wayBillId, boolean deleted);

    List<RoutePlan> findAllByIdIn(List<Map> ids);

    List<RoutePlan> findByWayBillId(String wayBillId);

    @Query(value = "select count(way_bill_id) from t_route_plan where status != '9' and  way_bill_id = ?1 ",nativeQuery = true)
    int countUnfinished(String wayBillId);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update t_route_plan set status='9'," +
            " update_time = :#{#routePlan.updateTime} " +
            " where id = :#{#routePlan.id} ",nativeQuery = true)
    int finishedRoutePlan(RoutePlan routePlan);
}