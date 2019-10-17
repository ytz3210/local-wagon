package com.el.impl;

import com.el.RedisService;
import com.el.SinoiovlApiService;
import com.el.TokenService;
import com.el.common.enums.ResponseStatusCode;
import com.el.common.source.Constant;
import com.el.common.to.response.SinoiovlTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author ZhangJun
 * @Description: 获取token
 * @create 2019-09-18 14:58
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private SinoiovlApiService sinoiovlApiService;

    private String token = "";

    @Override
    public String refresh() throws Exception {

        SinoiovlTO responseTO = sinoiovlApiService.getToken();
        if (responseTO != null && ResponseStatusCode.SUCCESS.getCode().equals(responseTO.getStatus())) {
            token = responseTO.getResult().toString();
            redisService.set(Constant.WAGON_TRACKER_TOKEN, token);
        }
        return token;
    }

    @Override
    public String getToken() throws Exception {

        token = redisService.get(Constant.WAGON_TRACKER_TOKEN);
        if (StringUtils.isEmpty(token)) {
            SinoiovlTO responseTO = sinoiovlApiService.getToken();
            if (responseTO != null && ResponseStatusCode.SUCCESS.getCode().equals(responseTO.getStatus())) {
                token = responseTO.getResult().toString();
                redisService.set(Constant.WAGON_TRACKER_TOKEN, token);
            }
        }
        return token;
    }
}
