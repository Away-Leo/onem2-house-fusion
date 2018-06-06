package com.laman.web.common.security.handles;

import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.web.common.dto.CPViewResultInfo;
import com.zds.common.lang.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Title: GlobalExceptionHandler.java
 * @Description:  全局controller抛出异常处理
 * @Author: Away
 * @Date: 2018/4/16 11:17
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @Author: Away
     * @Title: signException
     * @Description: 处理所有异常
     * @Param: ex
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/4/16 11:18
     * @Version: 2018/4/16 11:18
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public CPViewResultInfo signException(Exception ex) {
        CPViewResultInfo info=new CPViewResultInfo();
        try{
            BusinessException businessException=(BusinessException)ex;
            info.newFalse(ex,businessException.getCode());
        }catch (Exception e){
            info.newFalse(ex, ENUM_EXCEPTION.E10017.code);
        }
        log.error("全局异常处理器",ex);
        return info;
    }

}
