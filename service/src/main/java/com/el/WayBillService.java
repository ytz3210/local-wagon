package com.el;

import com.el.common.source.ResTO;
import com.el.common.to.WayBillInfoTO;
import org.springframework.data.domain.Pageable;

/**
 * @description: 运单相关Service
 * @author: MaoYe
 * @create: 2019/09/11
 */
public interface WayBillService {
    ResTO addWayBill(WayBillInfoTO to);

    ResTO updateWayBill(WayBillInfoTO to);

    ResTO delWayBill(String wayBillId);

    ResTO loadWayBills(String bookingNo, String site, String status, Pageable pageable);

    ResTO getContainerByBill(String wayBillId);
}