package com.el.dao;

import com.el.entity.StopEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopEventDao extends CrudRepository<StopEvent, Integer> {

}
