package com.el.dao;

import com.el.entity.RouteStopBill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

public interface RouteStopBillDao extends CrudRepository<RouteStopBill, Integer> {
    @Query(value = " select (SELECT name from t_stop tp where tp.id = stop_id) transit, pta transitBegin, ptd transitEnd" +
                   "   from t_route_stop_bill where is_deleted = false" +
                   "    and if(?1 !='', way_bill_id = ?1, 1=1) " +
                   "    and sort > '1' and sort <'90'" +
                   "  order by sort", nativeQuery = true)
    List<Map> find (String wayBillId);
}