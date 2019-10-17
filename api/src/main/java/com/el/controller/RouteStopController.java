package com.el.controller;

import com.el.RouteStopService;
import com.el.annotation.SysLog;
import com.el.common.source.ResTO;
import com.el.common.to.RouteStopTO;
import com.el.common.utils.BDMapApiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 站点相关业务
 * @author: MaoYe
 * @create: 2019/09/11
 */
@RestController
@RequestMapping(value = "/api")
public class RouteStopController extends BaseApiController {

    @Autowired
    RouteStopService routeStopService;

    @Value("${bdmap-ak}")
    private List<String> aks;

    /**
     * 增加站点
     * @return
     */
    @SysLog("新增站点")
    @PutMapping("/route_stop")
    public ResTO addRouteStop(@RequestBody RouteStopTO to) {
        return routeStopService.addRouteStop(to);
    }

    /**
     * 修改站点
     * @return
     */
    @SysLog("修改站点")
    @PostMapping("/route_stop")
    public ResTO editRouteStop(@RequestBody RouteStopTO to) {
        return routeStopService.updateRouteStop(to);
    }

    /**
     * 删除站点
     * @return
     */
    @SysLog("删除站点")
    @DeleteMapping("/route_stop")
    public ResTO delRouteStop(@RequestParam String id) {
        return routeStopService.delRouteStop(id);
    }

    /**
     * 加载所有站点
     * @return
     */
    @SysLog("加载所有站点")
    @GetMapping("/route_stops")
    public ResTO loadRouteStops(@RequestParam(required = false) String name,
                           @RequestParam Integer pageNo,
                           @RequestParam Integer pageSize) {
        return routeStopService.loadRouteStops(name, PageRequest.of(pageNo - 1, pageSize));
    }

    /**
     * 加载所有有效站点
     * @return
     **/
    @GetMapping("/all_route_stops")
    public ResTO getAllRouteStops() {
        return routeStopService.getAllRouteStops(false, true);
    }

    /**
     * 计算两个坐标之间的里程数（单位：公里）
     * @return
     **/
    @GetMapping("/getDistence")
    public ResTO getDistence(String start, String end) {
        return BDMapApiUtils.calculatedDistance(start, end, aks);
    }
}