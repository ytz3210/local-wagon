package com.el.dao;

import com.el.entity.Site;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SiteDao extends CrudRepository<Site, Integer> {

    Site findByIdAndDeleted(String siteId, boolean deleted);

    @Query(value = "select * from t_site where is_deleted = false " +
            " and if(?1 !='', name like %?1%, 1=1) " +
            " order by create_time desc", nativeQuery = true)
    Page<Site> find(String name, Pageable pageable);

    boolean existsById(String siteId);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update Site set deleted = true where Id=?1")
    Integer deleteSite(String siteId);

    List<Site> findAllByDeletedAndActivation(boolean deleted, boolean activation);
}