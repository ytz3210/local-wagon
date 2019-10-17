package com.el.impl;

import com.el.RequestService;
import com.el.TokenService;
import com.el.common.to.response.SinoiovlTO;
import com.el.common.utils.JsonUtils;
import com.el.dao.SinoiovInterfaceLogDao;
import com.el.entity.SinoiovInterfaceLog;
import com.openapi.sdk.service.DataExchangeService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 发送https请求service
 */
@Service
public class RequestServiceImpl implements RequestService {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    SinoiovInterfaceLogDao sinoiovInterfaceLogDao;
    @Autowired
    TokenService tokenService;

    @Override
    public SinoiovlTO doPostHttps(Object object, String url) {
        String res = "";
        SinoiovlTO to;
        try {
            Map<String, String> map = JSONObject.fromObject(object);
            // 设置 http 读写超时
            DataExchangeService des = new DataExchangeService(5000, 8000);
            // 通过 https 方式调用，此方法内部会使用私钥生成签名参数 sign,私钥不会发送
//            System.out.println(JSONObject.fromObject(map).toString());
            res = des.postHttps(url, map);
            to = JsonUtils.fromJson(res, SinoiovlTO.class);
            // token过期 或者 失效 则重新获取
            if ("1013".equals(to.getStatus()) || "1016".equals(to.getStatus())) {
                tokenService.refresh();
            }

//            this.saveLog(url, JSONObject.fromObject(map).toString(), res);
            return to;
        } catch (Exception e) {
            LOG.error("{}接口调用异常：{}", url, e.getMessage());
            this.saveLog(url, res, e.getMessage());
        }
        return null;
    }

    /**
     * 保存日志
     */
    private void saveLog(String url, String requestStr, String responseStr) {
        SinoiovInterfaceLog log = new SinoiovInterfaceLog();
        log.setRequestUrl(url);
        log.setRequestContent(requestStr);
        log.setResponseContent(responseStr);
        sinoiovInterfaceLogDao.save(log);
    }
}