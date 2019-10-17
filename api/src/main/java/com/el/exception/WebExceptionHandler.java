package com.el.exception;

import com.el.common.source.REnum;
import com.el.common.source.ResTO;
import com.el.common.utils.RUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 异常处理器
 */
@ControllerAdvice
public class WebExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(WebExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResTO handleException(Exception e) {
        logger.error("", e);
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            return RUtil.error(REnum.ERROR_PATH);
        } else {
            return RUtil.error(REnum.OPERATION_FAILED);
        }
    }

    /**
     * @param e
     * @return com.el.common.source.ResTO
     * @Author ZhangJun
     * @Description 自定义异常处理
     * @Date 2019-08-06 20:07
     **/
    @ResponseBody
    @ExceptionHandler(ApiException.class)
    public ResTO apiException(ApiException e) {
        return RUtil.errorByCode(Integer.valueOf(e.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler(DuplicateKeyException.class)
    public ResTO handleDuplicateKeyException(DuplicateKeyException e) {
        return RUtil.error(REnum.DATA_ALREADY_EXISTS);

    }


    /**
     * @param e
     * @return com.el.common.source.ResTO
     * @Description 登录验证
     **/
    @ResponseBody
    @ExceptionHandler(AuthenticationException.class)
    public ResTO authenticationException(AuthenticationException e) {

        return RUtil.errorByCode(Integer.valueOf(e.getMessage()));
    }


    /**
     * @return com.el.common.source.ResTO
     * @Description 权限不足
     **/
    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public ResTO unauthorizedException() {

        return RUtil.error(REnum.INSUFFICIENT_AUTHORITY);
    }
}
