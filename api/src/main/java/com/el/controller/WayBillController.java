package com.el.controller;

import com.el.WayBillService;
import com.el.annotation.SysLog;
import com.el.common.source.ResTO;
import com.el.common.to.WayBillInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 运单相关业务
 * @author: MaoYe
 * @create: 2019/09/11
 */
@RestController
@RequestMapping(value = "/api")
public class WayBillController extends BaseApiController {

    @Autowired
    WayBillService wayBillService;

    /**
     * 增加运单
     *
     * @return
     */
    @SysLog("新增运单")
    @PutMapping("/way_bill")
    public ResTO addWayBill(@RequestBody WayBillInfoTO to) {
        return wayBillService.addWayBill(to);
    }

    /**
     * 修改运单
     *
     * @return
     */
    @SysLog("修改运单")
    @PostMapping("/way_bill")
    public ResTO editWayBill(@RequestBody WayBillInfoTO to) {
        return wayBillService.updateWayBill(to);
    }

    /**
     * 删除运单
     *
     * @return
     */
    @SysLog("删除运单")
    @DeleteMapping("/way_bill")
    public ResTO delWayBill(@RequestParam String id) {
        return wayBillService.delWayBill(id);
    }

    /**
     * 加载所有运单
     *
     * @return
     */
    @GetMapping("/way_bills")
    public ResTO loadWayBills(@RequestParam(required = false) String bookingNo,
                              @RequestParam(required = false) String site,
                              @RequestParam(required = false) String status,
                              @RequestParam Integer pageNo,
                              @RequestParam Integer pageSize) {
        return wayBillService.loadWayBills(bookingNo, site, status, PageRequest.of(pageNo - 1, pageSize));
    }

    /**
     * 根据运单获取箱型
     *
     * @return
     */
    @SysLog("根据运单获取箱型")
    @GetMapping("/get_container_by_bill")
    public ResTO getContainerByBill(@RequestParam String wayBillId) {
        return wayBillService.getContainerByBill(wayBillId);
    }
}