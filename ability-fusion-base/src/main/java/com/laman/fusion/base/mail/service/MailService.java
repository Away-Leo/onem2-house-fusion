package com.laman.fusion.base.mail.service;

/**
* @Title: MailService
* @Description:  邮件发送服务
* @Author: Away
* @Date: 2018/5/28 18:19
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public interface MailService {

    void sendMessageMail(Object params,String sendTo,String title, String templateName);
}
