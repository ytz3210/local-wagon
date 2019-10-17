package com.el.impl;

import com.el.RouteStopService;
import com.el.common.source.REnum;
import com.el.common.source.ResTO;
import com.el.common.to.RouteStopTO;
import com.el.common.utils.RUtil;
import com.el.dao.RouteStopDao;
import com.el.entity.RouteStop;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @description: 站点相关业务
 * @author: MaoYe
 * @create: 2019/09/11
 */
@Service
public class RouteStopServiceImpl implements RouteStopService {

    @Autowired
    RouteStopDao routeStopDao;

    @Override
    public ResTO addRouteStop(RouteStopTO to) {
        RouteStop routeStop = new RouteStop();
        BeanUtils.copyProperties(to, routeStop);
        routeStopDao.save(routeStop);
        return RUtil.success();
    }

    @Override
    public ResTO updateRouteStop(RouteStopTO to) {
        RouteStop routeStop = routeStopDao.findByIdAndDeleted(to.getId(), false);
        if (routeStop == null) {
            return RUtil.error(REnum.PLACE_NOT_EXISTS);
        }

        BeanUtils.copyProperties(to, routeStop);
        return RUtil.success();
    }

    @Override
    public ResTO delRouteStop(String routeStopId) {
        if (!routeStopDao.existsById(routeStopId)) {
            return RUtil.error(REnum.PLACE_NOT_EXISTS);
        }

        if (routeStopDao.deleteRouteStop(routeStopId) > 0) {
            return RUtil.success();
        }
        return RUtil.error(REnum.DELETE_FAILED);
    }

    @Override
    public ResTO loadRouteStops(String name, Pageable pageable) {
        return RUtil.successByPage(routeStopDao.find(name, pageable));
    }

    @Override
    public ResTO getAllRouteStops(boolean deleted, boolean activation) {
        return RUtil.success(routeStopDao.findAllByDeleted(deleted));
    }
}