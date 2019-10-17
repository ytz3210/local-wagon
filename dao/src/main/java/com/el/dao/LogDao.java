package com.el.dao;

import com.el.entity.Log;
import org.springframework.data.repository.CrudRepository;

public interface LogDao extends CrudRepository<Log, Integer> {
}
