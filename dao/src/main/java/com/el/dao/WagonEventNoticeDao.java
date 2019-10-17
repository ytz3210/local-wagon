package com.el.dao;

import com.el.entity.WagonEventNotice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WagonEventNoticeDao extends CrudRepository<WagonEventNotice, String> {


    List<WagonEventNotice> findByDeleted(boolean deleted);

}
