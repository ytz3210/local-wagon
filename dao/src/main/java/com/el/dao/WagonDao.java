package com.el.dao;

import com.el.common.to.request.WagonRequestTO;
import com.el.entity.Wagon;
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
public interface WagonDao extends CrudRepository<Wagon, Integer> {

    Wagon findByIdAndDeleted(String wagonId, boolean deleted);

    int countByPlateNo(String palteNo);

    @Query(value = "select w.id,w.plate_no as plateNo,w.driver_id as driverId, w.is_temporary as temporary," +
            " d.name as driverName,w.wagon_team_id as wagonTeamId,wt.`name` as wagonTeamName " +
            " from t_wagon w " +
            " left join t_driver d on w.driver_id = d.id " +
            " left join t_wagon_team wt on w.wagon_team_id = wt.id " +
            " where w.is_deleted = false and d.is_deleted = false and (wt.is_deleted = false or isnull(wt.is_deleted)) " +
            " and if(:#{#wagon.plateNo} !='', w.plate_no like %:#{#wagon.plateNo}%, 1=1) " +
            " and if(:#{#wagon.driverName} !='', d.name like %:#{#wagon.driverName}%, 1=1) " +
            " and if(:#{#wagon.wagonTeamName} !='', wt.name like %:#{#wagon.wagonTeamName}%, 1=1) " +
            " order by w.update_time desc ",
            nativeQuery = true,
            countQuery = "select count(w.id) " +
                    " from t_wagon w " +
                    " left join t_driver d on w.driver_id = d.id " +
                    " left join t_wagon_team wt on w.wagon_team_id = wt.id " +
                    " where w.is_deleted = false and d.is_deleted = false and (wt.is_deleted = false or isnull(wt.is_deleted)) " +
                    " and if(:#{#wagon.plateNo} !='', w.plate_no like %:#{#wagon.plateNo}%, 1=1) " +
                    " and if(:#{#wagon.driverName} !='', d.name like %:#{#wagon.driverName}%, 1=1) " +
                    " and if(:#{#wagon.wagonTeamName} !='', wt.name like %:#{#wagon.wagonTeamName}%, 1=1) " +
                    " order by w.update_time desc ")
    Page<Map> find(@Param("wagon") WagonRequestTO wagonRequestTO, Pageable pageable);

    boolean existsById(String wagonId);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update Wagon set deleted = true where Id=?1")
    Integer deleteWagon(String wagonId);

    Wagon findByPlateNo(String plateNo);

    List<Wagon> findAllByDeleted(boolean deleted);
}