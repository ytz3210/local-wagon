package com.el.task;

import com.el.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ZhangJun
 * @Description: 每日刷新token定时任务
 * @create 2019-09-18 11:26
 */
@Component
public class TokenTask {

    @Autowired
    private TokenService tokenService;

    /**
     * @return void
     * @Description 每天凌晨1点更新redis缓存中的token, token的有效期为3天, 每天接口调用上限为10次
     **/
    @Scheduled(cron = "0 0 1 * * ?")
    public void refreshCache() throws Exception {

        tokenService.refresh();
    }


}
