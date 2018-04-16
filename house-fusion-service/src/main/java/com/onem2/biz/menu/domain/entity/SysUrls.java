package com.onem2.biz.menu.domain.entity;

import com.onem2.fusion.base.entity.AggEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
* @Title: Price.java
* @Description:  连接表
* @author Away
* @date 2018/2/7 18:22
* @copyright 重庆壹平方米网络科技有限公司
* @version V1.0
*/
@Entity
@Table(name="fusion_sys_urls")
@Getter
@Setter
public class SysUrls extends AggEntity{

    @Column(name="url",columnDefinition="varchar(100) comment '链接地址'")
    private String url;

    @Column(name="url_name",columnDefinition="varchar(100)  comment '连接描述'")
    private String urlName;

}
