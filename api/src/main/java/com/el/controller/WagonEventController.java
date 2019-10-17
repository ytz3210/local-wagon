package com.el.controller;

import com.el.TokenService;
import com.el.common.enums.ResponseStatusCode;
import com.el.common.to.response.SinoiovlTO;
import com.el.dao.WagonEventNoticeDao;
import com.el.entity.WagonEventNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangJun
 * @Description: 车辆进出围栏事件回调
 * @create 2019-09-29 15:27
 */
@RestController
@RequestMapping("wagon_event")
public class WagonEventController {

    @Value("${wagon_tracker.client_id}")
    private String clientId;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private WagonEventNoticeDao wagonEventNoticeDao;

    @PostMapping("area_notify")
    public SinoiovlTO areaModify(WagonEventNotice wagonEventNotice) {

        SinoiovlTO sinoiovlTO = new SinoiovlTO();
        sinoiovlTO.setResult(System.currentTimeMillis());
        String token = "";
        try {
            token = tokenService.getToken();
        } catch (Exception e) {
            sinoiovlTO.setStatus(ResponseStatusCode.SYSTEM_ERROR.getCode());
            sinoiovlTO.setMessage(null);
            return sinoiovlTO;
        }

        if (StringUtils.isEmpty(wagonEventNotice.getToken()) || !token.equals(wagonEventNotice.getToken())) {
            sinoiovlTO.setStatus(ResponseStatusCode.TOKEN_INVALID_EXPIRED.getCode());
            sinoiovlTO.setMessage(null);
            return sinoiovlTO;
        }

        if (!clientId.equals(wagonEventNotice.getCid())) {
            sinoiovlTO.setStatus(ResponseStatusCode.CLIENT_ID_ERROR.getCode());
            sinoiovlTO.setMessage(null);
            return sinoiovlTO;
        }

        wagonEventNoticeDao.save(wagonEventNotice);
        sinoiovlTO.setStatus(ResponseStatusCode.SUCCESS.getCode());
        sinoiovlTO.setMessage(null);
        return sinoiovlTO;
    }


}
