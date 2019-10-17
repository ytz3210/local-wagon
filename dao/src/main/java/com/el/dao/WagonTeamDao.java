package com.el.dao;

import com.el.entity.WagonTeam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WagonTeamDao extends CrudRepository<WagonTeam, Integer> {

    WagonTeam findByIdAndDeleted(String MotorcadeId, boolean deleted);

    @Query(value = "select * from t_wagon_team where is_deleted = false " +
            " and if(?1 !='', name like %?1%, 1=1) " +
            " and if(?2 !='', contact_phone like %?2%, 1=1) " +
            " order by create_time desc", nativeQuery = true)
    Page<WagonTeam> find(String name, String contactPhone, Pageable pageable);

    boolean existsById(String MotorcadeId);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update WagonTeam set deleted = true where Id = ?1")
    Integer deleteMotorcade(String MotorcadeId);

    /**
     * @param deleted
     * @return java.util.List<com.el.entity.WagonTeam>
     * @Description 获取所有可用的司机
     **/
    List<WagonTeam> findAllByDeleted(boolean deleted);

    int countByName(String name);

    WagonTeam findByName(String name);
}