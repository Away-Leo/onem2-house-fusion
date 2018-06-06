package com.laman.biz.menu.domain.entity;

import com.laman.fusion.base.entity.BaseEntity;
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
* @copyright 重庆拉曼科技有限公司
* @version V1.0
*/
@Entity
@Table(name="fusion_sys_urls")
@Getter
@Setter
public class SysUrls extends BaseEntity{

    @Column(name="url",columnDefinition="varchar(100) comment '链接地址'")
    private String url;

    @Column(name="url_method",columnDefinition="varchar(10) comment '链接请求类型'")
    private String urlMethod;

    @Column(name="url_name",columnDefinition="varchar(100)  comment '连接描述'")
    private String urlName;

    @Column(name="url_belong",columnDefinition="varchar(100)  comment '所属菜单'")
    private String urlBelong;

}
