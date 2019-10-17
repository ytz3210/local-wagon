package com.el.aspect;

import com.el.annotation.SysLog;
import com.el.common.utils.HttpContextUtils;
import com.el.common.utils.IPUtils;
import com.el.common.utils.JWTUtil;
import com.el.dao.LogDao;
import com.el.entity.Log;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;


/**
 * @author ZhangJun
 * @ClassName: SysLogAspect
 * @Description: 系统日志切面
 * @date 2019年9月6日 下午3:34:10
 */
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private LogDao logDao;

    // 定义Pointcut，Pointcut的名称 就是simplePointcut，此方法不能有返回值，该方法只是一个标示
    // @annotation 指定自定义注解
    @Pointcut("@annotation(com.el.annotation.SysLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //保存日志
        saveSysLog(point, time);
        return result;
    }


    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Log sysLog = new Log();
        SysLog log = method.getAnnotation(SysLog.class);
        if (log != null) {
            //注解上的描述
            sysLog.setOperation(log.value());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = new Gson().toJson(args[0]);
            sysLog.setParams(params);
        } catch (Exception e) {

        }

        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));
//        sysLog.setAddress(IPUtils.getAddresses(IPUtils.getIpAddr(request)));
        sysLog.setTime(time);
        sysLog.setCreateTime(new Date());
        //用户名
//        String token = (String) SecurityUtils.getSubject().getPrincipal();
//        String username = JWTUtil.getUsername(token);
//        if (StringUtils.hasText(username)) {
//            sysLog.setUsername(username);
            //保存系统日志
            logDao.save(sysLog);
//        }
    }
}
