package com.el;

import com.el.common.source.ResTO;
import com.el.common.to.RouteStopTO;
import org.springframework.data.domain.Pageable;

/**
 * @description: 站点相关Service
 * @author: MaoYe
 * @create: 2019/09/11
 */
public interface RouteStopService {
    ResTO addRouteStop(RouteStopTO to);

    ResTO updateRouteStop(RouteStopTO to);

    ResTO delRouteStop(String placeId);

    ResTO loadRouteStops(String name, Pageable pageable);

    ResTO getAllRouteStops(boolean deleted, boolean activation);
}