package com.el.dao;

import com.el.entity.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public interface DriverDao extends CrudRepository<Driver, Integer> {

    Driver findByIdAndDeleted(String driverId, boolean deleted);

    @Query(value = "select * from t_driver where is_deleted = false" +
            " and if(?1 !='', name like %?1%, 1=1) " +
            " and if(?2 !='', phone_no like %?2%, 1=1)" +
            " order by create_time desc", nativeQuery = true)
    Page<Driver> find(String name, String phoneNo, Pageable pageable);

    boolean existsById(String driverId);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update Driver set deleted = true where Id=?1")
    Integer deleteDriver(String driverId);

    /**
     * @param deleted
     * @return java.util.List<com.el.entity.Driver>
     * @Description 获取所有可用的司机
     **/
    List<Driver> findAllByDeleted(boolean deleted);

    @Query("from Driver where identityCard = ?1")
    Driver findByIdeAndIdentityCard(String identityCard);

    int countByIdentityCard(String identityCard);

    @Query(value = "select b.name, b.phone_no phoneNo from t_wagon a, t_driver b" +
                   " where a.driver_id = b.id AND a.plate_no = ?1" +
                   "   and a.is_deleted = false and b.is_deleted = false", nativeQuery = true)
    Map findDriverByplateNo(String phoneNo);
}