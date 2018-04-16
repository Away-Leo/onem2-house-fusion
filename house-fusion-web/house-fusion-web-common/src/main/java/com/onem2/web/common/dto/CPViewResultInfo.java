package com.onem2.web.common.dto;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by dujy on 2017-05-21.
 */
public class CPViewResultInfo implements Serializable{

    private Boolean success =Boolean.TRUE;
    private String code = "000000";
    private String message;
    private int draw;
    private Object data;

    public void newSuccess(Object data){
        this.data=data;
        this.success=true;
        this.message="成功";
    }

    public void newFalse(Exception e){
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
