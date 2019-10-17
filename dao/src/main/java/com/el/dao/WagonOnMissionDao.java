package com.el.dao;

import com.el.entity.WagonOnMission;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WagonOnMissionDao extends CrudRepository<WagonOnMission, String> {

    @Query(value = "SELECT * FROM t_wagon_on_mission WHERE is_deleted = 0 " +
            " AND if(?1 !='', booking_no = ?1, 1=1)" +
            " AND if(?2 !='', online =?2, 1=1)" +
            " ORDER BY (last_stop_distance + 0) ASC,`status` DESC ", nativeQuery = true)
    List<WagonOnMission> getWagonOnMissions(String bookingNo, String status);

    @Transactional
    @Modifying
    @Query(value = "update t_wagon_on_mission set " +
            " online = :#{#wagonOnMission.online}, " +
            " velocity = :#{#wagonOnMission.velocity}, " +
            " direction = :#{#wagonOnMission.direction}, " +
            " lng = :#{#wagonOnMission.lng}, " +
            " lat = :#{#wagonOnMission.lat}, " +
            " adr = :#{#wagonOnMission.adr}, " +
            " pos_time_updated = :#{#wagonOnMission.posTimeUpdated}, " +
            " update_time = :#{#wagonOnMission.updateTime} " +
            " where id = :#{#wagonOnMission.id} ", nativeQuery = true)
    int updatePosition(@Param("wagonOnMission") WagonOnMission wagonOnMission);

    @Transactional
    @Modifying
    @Query(value = "update t_wagon_on_mission set " +
            " online = :#{#wagonOnMission.online}, " +
            " update_time = :#{#wagonOnMission.updateTime} " +
            " where id = :#{#wagonOnMission.id} ", nativeQuery = true)
    int updateOnline(@Param("wagonOnMission") WagonOnMission wagonOnMission);

    @Transactional
    @Modifying
    @Query(value = "update t_wagon_on_mission set " +
            " next_stop_eta = :#{#wagonOnMission.nextStopETA}, " +
            " next_stop_distance = :#{#wagonOnMission.nextStopDistance}, " +
            " next_stop_name = :#{#wagonOnMission.nextStopName}, " +
            " last_stop_eta = :#{#wagonOnMission.lastStopETA}, " +
            " last_stop_distance = :#{#wagonOnMission.lastStopDistance}, " +
            " status = :#{#wagonOnMission.status}, " +
            " update_time = :#{#wagonOnMission.updateTime} " +
            " where id = :#{#wagonOnMission.id} ", nativeQuery = true)
    int updateStopInfo(@Param("wagonOnMission") WagonOnMission wagonOnMission);

    WagonOnMission findByRoutePlanIdAndPlateNo(String routePlanId, String plateNo);

    @Transactional
    @Modifying
    @Query(value = "update t_wagon_on_mission set " +
            " is_deleted = 1" +
            " update_time = ?1 " +
            " where id = ?2 ", nativeQuery = true)
    int deleteWagonOnMission(Long updateTime, String wagonOnMissionId);

    @Transactional
    @Modifying
    @Query(value = "update t_wagon_on_mission set " +
            " status = :#{#wagonOnMission.status}, " +
            " update_time = :#{#wagonOnMission.updateTime} " +
            " where id = :#{#wagonOnMission.id} ", nativeQuery = true)
    int updateWagonOnMissionStatus(@Param("wagonOnMission") WagonOnMission wagonOnMission);

    @Query(value = "select * from t_wagon_on_mission order by last_stop_eta asc", nativeQuery = true)
    List<WagonOnMission> findMission();

}
