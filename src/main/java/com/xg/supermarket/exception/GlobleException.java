package com.xg.supermarket.exception;

import com.xg.supermarket.utils.ReMap;
import com.xg.supermarket.utils.ReMapUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobleException {
    @ExceptionHandler(BizException.class)
    @ResponseBody
    public ReMap bizException(Exception e){
        return ReMapUtil.fail(e.getMessage());
    }

}
