package com.el.dao;

import com.el.entity.RouteStop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public interface RouteStopDao extends CrudRepository<RouteStop, Integer> {

    RouteStop findByIdAndDeleted(String routeStopId, boolean deleted);

    @Query(value = "select * from t_route_stop where is_deleted = false " +
            " and if(?1 !='', name like %?1%, 1=1) " +
            " order by create_time desc", nativeQuery = true)
    Page<RouteStop> find(String name, Pageable pageable);

    boolean existsById(String routeStopId);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update RouteStop set deleted = true where Id=?1")
    Integer deleteRouteStop(String routeStopId);

    List<RouteStop> findAllByDeleted(boolean deleted);

    @Query(value = "select * from t_route_stop " +
            " where is_deleted = false and route_plan_id = ?1 order by sort desc", nativeQuery = true)
    List<RouteStop> findByRoutePlanId(String routePlanId);

    @Query(value = "select * from t_route_stop " +
            " where is_deleted = false and status = '0' and route_plan_id = ?1 order by sort desc", nativeQuery = true)
    List<RouteStop> findMissedArrivalByRoutePlanId(String routePlanId);

    @Transactional
    @Modifying
    @Query(value = "update t_route_stop " +
            " set eta = :#{#routeStop.eta}," +
            " update_time = :#{#routeStop.updateTime} " +
            " where id = :#{#routeStop.id}", nativeQuery = true)
    int updateRouteEta(@Param("routeStop") RouteStop routeStop);

    RouteStop findByStopIdAndDeleted(String stopId, boolean deleted);

    @Transactional
    @Modifying
    @Query(value = "update t_route_stop set " +
            " update_time = ?4 ," +
            " status = ?3, " +
            " if(?1 != '', eta =?1, 1=1) ," +
            " if(?2 != '', atd =?2, 1=1) " +
            " where id = ?4", nativeQuery = true)
    int updateRouteStopInfo(Long ata, Long atd, String status, Long updateTime, String id);


    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update RouteStop set deleted = true where routePlanId=?1")
    int deleteAllByRoutePlan(String routePlanId);

    @Query(value = " select b.site_id transit, b.name transitName, pta transitBegin, ptd transitEnd" +
                   "   from t_route_stop a, t_stop b where a.is_deleted = false and b.is_deleted = false " +
                   "    and if(?1 !='', a.route_plan_id = ?1, 1=1) " +
                   "    and a.sort > '1' and a.sort <'90' and a.stop_id = b.id" +
                   "  order by a.sort", nativeQuery = true)
    List<Map> findAllByRoutePlanIdAndDeletedAndSortBetween (String routePlanId);
}