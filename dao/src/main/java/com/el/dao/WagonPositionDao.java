package com.el.dao;

import com.el.entity.WagonPosition;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface WagonPositionDao extends CrudRepository<WagonPosition, String> {

    int countByHash(int hash);

    int countByPlateNo(String plateNo);

    @Transactional
    @Modifying
    @Query(value = "update t_wagon_position set " +
            " lat = :#{#wagonPosition.lat}," +
            " lon = :#{#wagonPosition.lon}," +
            " lat_bd = :#{#wagonPosition.latBD}," +
            " lon_bd = :#{#wagonPosition.lonBD}," +
            " adr = :#{#wagonPosition.adr}," +
            " utc = :#{#wagonPosition.utc}," +
            " spd = :#{#wagonPosition.spd}," +
            " drc = :#{#wagonPosition.drc}," +
            " province = :#{#wagonPosition.province}," +
            " city = :#{#wagonPosition.city}," +
            " country = :#{#wagonPosition.country}," +
            " hash = :#{#wagonPosition.hash}," +
            " update_time = :#{#wagonPosition.updateTime}" +
            " where plate_no = :#{#wagonPosition.plateNo}", nativeQuery = true)
    int updateByPlateNo(@Param("wagonPosition") WagonPosition wagonPosition);
}
