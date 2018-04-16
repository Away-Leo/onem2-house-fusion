package com.onem2.biz.user.domain.entity;

import com.onem2.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name="fusion_user_info")
@Getter
@Setter
public class UserInfo extends BaseEntity {

    @Column(name="user_id",columnDefinition="varchar(100) not null comment '用户ID'")
    private Long userId;

    @Column(name="name",columnDefinition="varchar(100)  comment '姓名'")
    private String name;

    @Column(name="head_img",columnDefinition="varchar(50) comment '用户头像'")
    private String headImg;

    @Column(name="id_card",columnDefinition="varchar(20)  comment '证件号码'")
    private String idCard;

    @Column(name="phone",columnDefinition="varchar(32)  comment '手机号码'")
    private String phone;

    @Column(name="account_no",columnDefinition="varchar(50)  comment '银行卡号'")
    private String accountNo;

    @Column(name="area",columnDefinition="varchar(50)  comment '用户所在区域'")
    private String area;

    @Column(name="area_name",columnDefinition="varchar(50)  comment '用户所在区域描述'")
    private String areaName;

    @Column(name="link_address",columnDefinition="varchar(200)  comment '用户详细地址'")
    private String linkAddress;

    @Column(name="register_date",columnDefinition="datetime comment '注册时间'")
    private Date registerDate;

}
