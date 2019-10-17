package com.el.controller;

import com.el.RoutePlanService;
import com.el.annotation.SysLog;
import com.el.common.source.ResTO;
import com.el.common.to.AppointWagonTeamTO;
import com.el.common.to.RoutePlanTO;
import com.el.common.to.RouteStopMouldTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 路线相关业务
 * @author: MaoYe
 * @create: 2019/09/11
 */
@RestController
@RequestMapping(value = "/api")
public class RoutePlanController extends BaseApiController {

    @Autowired
    RoutePlanService routePlanService;

    /**
     * 增加路线
     *
     * @return
     */
    @SysLog("新增路线")
    @PutMapping("/route_plan")
    public ResTO addRoutePlan(@RequestBody RouteStopMouldTO to) {
        return routePlanService.addRoutePlan(to);
    }

    /**
     * 增加路线
     *
     * @return
     */
    @SysLog("运单管理中新增路线")
    @PutMapping("/route_plan_bill")
    public ResTO addRoutePlanByBill(@RequestBody RouteStopMouldTO to) {
        return routePlanService.addRoutePlanByBill(to);
    }

    /**
     * 修改路线
     *
     * @return
     */
    @SysLog("修改路线")
    @PostMapping("/route_plan")
    public ResTO editRoutePlan(@RequestBody RouteStopMouldTO to) {
        return routePlanService.editRoutePlan(to);
    }

    /**
     * 修改箱子信息
     *
     * @return
     */
    @SysLog("修改路线")
    @PostMapping("/edit_container")
    public ResTO editContainer(@RequestBody RoutePlanTO to) {
        return routePlanService.editContainer(to);
    }

    /**
     * 删除路线
     *
     * @return
     */
    @SysLog("删除路线")
    @DeleteMapping("/route_plan")
    public ResTO delRoutePlan(@RequestParam String id) {
        return routePlanService.delRoutePlan(id);
    }

    /**
     * 加载所有路线
     *
     * @return
     */
    @GetMapping("/route_plans")
    public ResTO loadRoutePlans(@RequestParam(required = false) String route_planId,
                             @RequestParam Integer pageNo,
                             @RequestParam Integer pageSize) {
        return routePlanService.loadRoutePlans(route_planId, PageRequest.of(pageNo - 1, pageSize));
    }

    /**
     * 不分页根据运单号加载路线
     * @return
     **/
    @GetMapping("/all_route_plans")
    public ResTO getAllSitesByWayBill(@RequestParam String wayBillId) {
        return routePlanService.getAllSitesByWayBill(wayBillId);
    }

    /**
     * 路线绑定车辆
     *
     * @return
     */
    @SysLog("路线绑定车辆")
    @PostMapping("/band_wagon")
    public ResTO bandWagon(@RequestBody RoutePlanTO to) {
        return routePlanService.bandWagon(to);
    }

    /**
     * 指派车队
     *
     * @return
     */
    @SysLog("指派车队")
    @PostMapping("/appoint_wagonteam")
    public ResTO appointWagonteam(@RequestBody AppointWagonTeamTO to) {
        return routePlanService.appointWagonTeam(to);
    }

    /**
     * 任务开始
     *
     * @return
     */
    @SysLog("任务开始")
    @PostMapping("/mission_start")
    public ResTO missionStart(@RequestBody RoutePlanTO to) throws Exception {
        return routePlanService.missionStart(to.getId());
    }
}