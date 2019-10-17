package com.el.impl;

import com.el.SinoiovlApiService;
import com.el.SiteService;
import com.el.TokenService;
import com.el.common.enums.ResponseStatusCode;
import com.el.common.source.REnum;
import com.el.common.source.ResTO;
import com.el.common.to.SiteInfoTO;
import com.el.common.to.response.SinoiovlTO;
import com.el.common.utils.CommonUtils;
import com.el.common.utils.JsonUtils;
import com.el.common.utils.RUtil;
import com.el.dao.SiteDao;
import com.el.entity.Site;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @description: 围栏相关业务
 * @author: MaoYe
 * @create: 2019/09/11
 */
@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    SiteDao siteDao;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private SinoiovlApiService sinoiovlApiService;

    @Override
    public ResTO addSite(SiteInfoTO to) throws Exception {
        Site site = new Site();
        BeanUtils.copyProperties(to, site);
        // 调用接口 获取进出围栏的areaid
        if (StringUtils.hasText(site.getMapData()) && !"[]".equals(site.getMapData())) {

            // 计算中心点
            List<Map> mapDataList = JsonUtils.fromJson(site.getMapData(), List.class);

            BigDecimal lat = new BigDecimal(0);
            BigDecimal lng = new BigDecimal(0);

            for (Map data : mapDataList) {
                lat = lat.add(new BigDecimal(""+data.get("lat")));
                lng = lng.add(new BigDecimal(""+data.get("lng")));
            }

            site.setLat(""+lat.divide(new BigDecimal(mapDataList.size()), 7, BigDecimal.ROUND_HALF_UP));
            site.setLng(""+lng.divide(new BigDecimal(mapDataList.size()), 7, BigDecimal.ROUND_HALF_UP));

            // 注册围栏事件
            String lnglats = CommonUtils.transformatLatLng(site.getMapData());
            String token = tokenService.getToken();
            SinoiovlTO inSsinoiovlTO = sinoiovlApiService.inPolygonalAreaRegist(token, site.getName(), lnglats);
            if (inSsinoiovlTO != null && ResponseStatusCode.SUCCESS.getCode().equals(inSsinoiovlTO.getStatus())) {
                JSONObject data = JSONObject.fromObject(JSONObject.fromObject(inSsinoiovlTO.getResult()).toString());
                site.setInAreaId((String) data.get("areaid"));
            }else {
                return RUtil.errorByCode(REnum.OPERATION_FAILED.getCode());
            }
            SinoiovlTO outSsinoiovlTO = sinoiovlApiService.outPolygonalAreaRegist(token, site.getName(), lnglats);
            if (outSsinoiovlTO != null && ResponseStatusCode.SUCCESS.getCode().equals(outSsinoiovlTO.getStatus())) {
                JSONObject data = JSONObject.fromObject(JSONObject.fromObject(outSsinoiovlTO.getResult()).toString());
                site.setOutAreaId((String) data.get("areaid"));
            }else {
                return RUtil.errorByCode(REnum.OPERATION_FAILED.getCode());
            }
        }
        siteDao.save(site);
        return RUtil.success();
    }

    @Override
    public ResTO updateSite(SiteInfoTO to) throws Exception {
        Site site = siteDao.findByIdAndDeleted(to.getId(), false);
        if (site == null) {
            return RUtil.error(REnum.PLACE_NOT_EXISTS);
        }

        BeanUtils.copyProperties(to, site);
        // 调用接口 获取进出围栏的areaid
        if (StringUtils.hasText(site.getMapData())) {
            String lnglats = CommonUtils.transformatLatLng(site.getMapData());
            String token = tokenService.getToken();
            SinoiovlTO inSsinoiovlTO = sinoiovlApiService.inPolygonalAreaRegist(token, site.getName(), lnglats);
            if (inSsinoiovlTO != null && ResponseStatusCode.SUCCESS.getCode().equals(inSsinoiovlTO.getStatus())) {
                JSONObject data = JSONObject.fromObject(JSONObject.fromObject(inSsinoiovlTO.getResult()).toString());
                site.setInAreaId((String) data.get("areaid"));
            } else {
                return RUtil.errorByCode(REnum.OPERATION_FAILED.getCode());
            }
            SinoiovlTO outSsinoiovlTO = sinoiovlApiService.outPolygonalAreaRegist(token, site.getName(), lnglats);
            if (outSsinoiovlTO != null && ResponseStatusCode.SUCCESS.getCode().equals(outSsinoiovlTO.getStatus())) {
                JSONObject data = JSONObject.fromObject(JSONObject.fromObject(outSsinoiovlTO.getResult()).toString());
                site.setOutAreaId((String) data.get("areaid"));
            } else {
                return RUtil.errorByCode(REnum.OPERATION_FAILED.getCode());
            }
        }
        return RUtil.success();
    }

    @Override
    public ResTO delSite(String siteId) {
        if (!siteDao.existsById(siteId)) {
            return RUtil.error(REnum.PLACE_NOT_EXISTS);
        }

        if (siteDao.deleteSite(siteId) > 0) {
            return RUtil.success();
        }
        return RUtil.error(REnum.DELETE_FAILED);
    }

    @Override
    public ResTO loadSites(String name, Pageable pageable) {
        return RUtil.successByPage(siteDao.find(name, pageable));
    }

    @Override
    public ResTO getAllSites(boolean deleted, boolean activation) {
        return RUtil.success(siteDao.findAllByDeletedAndActivation(deleted, activation));
    }

}