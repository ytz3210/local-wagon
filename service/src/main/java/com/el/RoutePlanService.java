package com.el;

import com.el.common.source.ResTO;
import com.el.common.to.AppointWagonTeamTO;
import com.el.common.to.RoutePlanTO;
import com.el.common.to.RouteStopMouldTO;
import org.springframework.data.domain.Pageable;

/**
 * @description: 路线相关Service
 * @author: MaoYe
 * @create: 2019/09/11
 */
public interface RoutePlanService {
    ResTO addRoutePlan(RouteStopMouldTO to);

    ResTO addRoutePlanByBill(RouteStopMouldTO to);

    ResTO editRoutePlan(RouteStopMouldTO to);

    ResTO editContainer(RoutePlanTO to);

    ResTO delRoutePlan(String routePlanId);

    ResTO loadRoutePlans(String wayBillId, Pageable pageable);

    ResTO getAllSitesByWayBill(String wayBillId);

    ResTO bandWagon(RoutePlanTO to);

    ResTO appointWagonTeam(AppointWagonTeamTO to);

    ResTO missionStart(String id) throws Exception;
}