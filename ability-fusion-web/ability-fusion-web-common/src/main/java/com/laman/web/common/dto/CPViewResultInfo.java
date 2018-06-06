package com.laman.web.common.dto;

import com.alibaba.fastjson.JSON;
import com.zds.common.lang.exception.BusinessException;

import java.io.Serializable;

/**
 * @Title: CPViewResultInfo.java
 * @Description: 统一返回结果
 * @Author: Away
 * @Date: 2018/4/16 14:21
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
public class CPViewResultInfo implements Serializable{

    private Boolean success =Boolean.TRUE;
    private String code = "";
    private String message;
    private int draw;
    private Object data;

    public void newSuccess(Object data){
        this.data=data;
        this.success=true;
        this.message="成功";
    }

    public void newFalse(Exception e,String falseCode){
        this.success=false;
        this.code=falseCode;
        this.message=e.getMessage();
    }
    public void newFalse(Exception e){
        if(e.getClass().getName().equalsIgnoreCase(BusinessException.class.getName()))this.code=((BusinessException) e).getCode();
        this.success=false;
        this.message=e.getMessage();
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String convert(Object object)
    {
        CPViewResultInfo message = new CPViewResultInfo();
        message.setData(object);
        message.setSuccess(Boolean.TRUE);
        message.setMessage("成功");
        return JSON.toJSONString(message);
    }
}
