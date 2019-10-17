package com.el.impl;

import com.el.WayBillService;
import com.el.common.source.REnum;
import com.el.common.source.ResTO;
import com.el.common.to.PageTO;
import com.el.common.to.WayBillInfoTO;
import com.el.common.utils.JsonUtils;
import com.el.common.utils.RUtil;
import com.el.dao.RoutePlanDao;
import com.el.dao.RouteStopBillDao;
import com.el.dao.WayBillDao;
import com.el.entity.RoutePlan;
import com.el.entity.WayBill;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @description: 运单相关业务
 * @author: MaoYe
 * @create: 2019/09/11
 */
@Service
public class WayBillServiceImpl implements WayBillService {

    @Autowired
    private WayBillDao wayBillDao;
    @Autowired
    RoutePlanDao routePlanDao;
    @Autowired
    private RouteStopBillDao routeStopBillDao;

    @Override
    public ResTO addWayBill(WayBillInfoTO to) {
        WayBill wayBill = new WayBill();
        BeanUtils.copyProperties(to, wayBill);
        wayBill.setStatus("0");
        wayBill.setIconColor(new Random().nextInt(100)+1);
        wayBillDao.save(wayBill);

        List<Map<String, String>> boxList = JsonUtils.fromJson(to.getContainerInfo(), List.class);
        for (Map<String, String> m : boxList) {
            int quality = Integer.valueOf(m.get("quality"));
            for (int i = 0; i<quality; i++) {
                RoutePlan routePlan = new RoutePlan();
                routePlan.setWayBillId(wayBill.getId());
                routePlan.setActivation(false);
                routePlan.setStatus("0");
                routePlanDao.save(routePlan);
            }
        }

        return RUtil.success(wayBill.getId());
    }

    @Override
    public ResTO updateWayBill(WayBillInfoTO to) {
        WayBill wayBill = wayBillDao.findByIdAndDeleted(to.getId(), false);
        if (wayBill == null) {
            return RUtil.error(REnum.WAYBILL_NOT_EXISTS);
        }

        BeanUtils.copyProperties(to, wayBill);
        return RUtil.success();
    }

    @Override
    public ResTO delWayBill(String wayBillId) {
        if (!wayBillDao.existsById(wayBillId)) {
            return RUtil.error(REnum.WAYBILL_NOT_EXISTS);
        }

        if (wayBillDao.deleteWayBill(wayBillId) > 0) {
            return RUtil.success();
        }
        return RUtil.error(REnum.DELETE_FAILED);
    }

    @Override
    public ResTO loadWayBills(String bookingNo, String site, String status, Pageable pageable) {
        Page<Map> resultPage = wayBillDao.find(bookingNo, site, status, pageable);

        List<Map> resList = new ArrayList<>();
        if(resultPage.getTotalElements()>0) {
            List<Map> resultList =  resultPage.getContent();
            for (Map m : resultList) {
                List<Map> stopList = routeStopBillDao.find(m.get("id").toString());
                String jsonStr = JsonUtils.toJson(stopList);
                Map map = new HashMap();
                map.putAll(m);
                if (stopList.size() > 0){
                    map.put("shipmentPlace", stopList.get(0).get("transit"));
                }
                map.put("transitInfo", jsonStr);
                resList.add(map);
            }
        }

        PageTO pageTO = new PageTO();
        pageTO.setData(resList);
        pageTO.setPageNo(resultPage.getNumber());
        pageTO.setPageSize(resultPage.getSize());
        pageTO.setTotalCount(resultPage.getTotalElements());
        pageTO.setTotalPage(resultPage.getTotalPages());
        return RUtil.success(pageTO);
    }

    @Override
    public ResTO getContainerByBill(String wayBillId) {
        if (!wayBillDao.existsById(wayBillId)) {
            return RUtil.error(REnum.WAYBILL_NOT_EXISTS);
        }

        WayBill wayBill = wayBillDao.findByIdAndDeleted(wayBillId, false);

        List<Map<String, String>> boxList = JsonUtils.fromJson(wayBill.getContainerInfo(), List.class);
        HashSet resultSet = new HashSet();
        for (Map<String, String> m : boxList) {
            resultSet.add(m.get("type"));
        }
        return RUtil.success(resultSet);
    }
}