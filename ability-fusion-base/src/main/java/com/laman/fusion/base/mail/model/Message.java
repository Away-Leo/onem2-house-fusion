package com.laman.fusion.base.mail.model;

/**
* @Title: Message
* @Description: 数据模型类
* @Author: Away
* @Date: 2018/5/28 18:21
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public class Message {

    private String messageCode;

    private String messageStatus;

    private String cause;

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
