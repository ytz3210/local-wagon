package com.el;

import com.el.common.source.ResTO;
import com.el.common.to.SiteInfoTO;
import org.springframework.data.domain.Pageable;

/**
 * @description: 围栏相关Service
 * @author: MaoYe
 * @create: 2019/09/11
 */
public interface SiteService {
    ResTO addSite(SiteInfoTO to) throws Exception;

    ResTO updateSite(SiteInfoTO to) throws Exception;

    ResTO delSite(String placeId);

    ResTO loadSites(String name, Pageable pageable);

    ResTO getAllSites(boolean deleted, boolean activation);
}