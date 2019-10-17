package com.el.controller;


import com.el.common.source.REnum;
import com.el.common.source.ResTO;
import com.el.common.utils.RUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BaseApiController {

    protected Logger LOG = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResTO handleException(Throwable e) {
        LOG.info("Exception: ", e);
        return RUtil.error(REnum.OTHER_EXCEPTION);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResTO handleException(HttpMessageNotReadableException t) {
        LOG.info("errorLog{}",t);
        return RUtil.error(REnum.DATA_FORMAT_ERR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResTO handleException(DataIntegrityViolationException t) {
        LOG.info("errorLog{}",t);
        return RUtil.error(REnum.DATABASE_ERR);
    }
}