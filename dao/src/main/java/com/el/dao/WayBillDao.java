package com.el.dao;

import com.el.entity.WayBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Repository
public interface WayBillDao extends CrudRepository<WayBill, Integer> {

    WayBill findByIdAndDeleted(String wayBillId, boolean deleted);

    @Query(value = " select a.id, a.name, a.booking_no bookingNo, a.ship_company shipCompany, a.barge_ship_name bargeShipName, " +
                   "  a.barge_line bargeLine, a.ship_name shipName, a.line, a.barge_from bargeFrom, a.pol, a.pod, " +
                   "  a.delivery_place deliveryPlace, a.port_cut_off portCutOff, a.eta, a.etd, a.barge_etd bargeEtd, " +
                   "  a.barge_eta bargeEta, a.order_cut_off orderCutOff, a.cy_cut_off cyCutOff, a.port_code portCode, " +
                   "  a.container_info containerInfo, a.status, a.lcl_booking_no lclBookingNo, a.invoice_no invoiceNo, " +
                   "  a.customer_no customerNo, a.wagon_team_json wagonTeamJson, " +
                   "  (SELECT name from t_stop tp where tp.id = b.stop_id) startStop,  b.pta startStopBegin, b.ptd startStopEnd, " +
                   "  (SELECT name from t_stop tp where tp.id = c.stop_id) endStop, c.pta endStopBegin, c.ptd endStopEnd, " +
                   "  (SELECT name from t_route_plan trp, t_wagon_team twt where trp.wagon_team_id = twt.id and trp.way_bill_id = a.id limit 1) wagonTeamName " +
                   "  from t_way_bill a " +
                   "  left join t_route_stop_bill b ON a.id = b.way_bill_id AND b.is_deleted = false AND b.sort = '1' " +
                   "  left join t_route_stop_bill c ON a.id = c.way_bill_id AND c.is_deleted = false AND c.sort = '90' " +
                   " where a.is_deleted = false " +
                   "   and if(?1 !='', a.booking_no like %?1%, 1=1) " +
                   "   and if(?2 !='', exists (select 1 from t_route_plan trp, t_route_stop trs " +
                   "     where trp.id = trs.route_plan_id and trp.is_deleted = false and trs.is_deleted = false " +
                   "       and trs.sort > '1' and trs.sort < '90' and trp.way_bill_id = a.id " +
                   "       and exists (select 1 from t_stop where is_deleted = false and site_id = ?2 and id = trs.stop_id)) , 1=1) " +
                   "   and if(?3 !='', a.status = ?3, 1=1) " +
                   " order by a.create_time desc ",
            nativeQuery = true,
            countQuery = " select count(1) " +
                         "   from t_way_bill a " +
                         "   left join t_route_stop_bill b ON a.id = b.way_bill_id AND b.is_deleted = false AND b.sort = '1' " +
                         "   left join t_route_stop_bill c ON a.id = c.way_bill_id AND c.is_deleted = false AND c.sort = '90' " +
                         " where a.is_deleted = false " +
                         "   and if(?1 !='', a.booking_no like %?1%, 1=1) " +
                         "   and if(?2 !='', exists (select 1 from t_route_plan trp, t_route_stop trs " +
                         "     where trp.id = trs.route_plan_id and trp.is_deleted = false and trs.is_deleted = false " +
                         "       and trs.sort > '1' and trs.sort < '90' and trp.way_bill_id = a.id " +
                         "       and exists (select 1 from t_stop where is_deleted = false and site_id = ?2 and id = trs.stop_id)) , 1=1) " +
                         "    and if(?3 !='', a.status = ?3, 1=1) ")
    Page<Map> find(String bookingNo, String site, String status, Pageable pageable);

    boolean existsById(String wayBillId);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update WayBill set deleted = true where Id=?1")
    Integer deleteWayBill(String wayBillId);

    WayBill findByBookingNo(String bookingNo);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update t_way_bill set status = 2," +
            " update_time = ?1 " +
            " where id =?2",nativeQuery = true)
    int completeWayBill(Long updateTime, String wayBillId);
}