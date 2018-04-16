package com.onem2.biz.user.domain.entity;

import com.onem2.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
* @Title: User.java
* @Description: 用户
* @author Away
* @date 2018/2/7 14:04
* @copyright 重庆壹平方米网络科技有限公司
* @version V1.0
*/
@Entity
@Table(name="fusion_sys_user")
@org.hibernate.annotations.Table(appliesTo = "fusion_sys_user",comment = "用户账户表，用于存储用户帐号信息")
@Getter
@Setter
public class User extends BaseEntity {

    @Column(name="username",columnDefinition="varchar(100) not null comment '用户帐号'")
    private String userName;

    @Column(name="display_name",columnDefinition="varchar(100)  comment '用户显示名称'")
    private String displayName;

    @Column(name="password",columnDefinition="varchar(256)   comment '密码'")
    private String password;

    @Column(name="salt",columnDefinition="varchar(100)  comment '加密盐'")
    private String salt;

    @Column(name="role_ids",columnDefinition="varchar(100) comment '角色ID'")
    private String roleIds;

    @Column(name="verify_code",columnDefinition="varchar(100) comment '验证码'")
    private String verifyCode;

    @Column(name="locked",columnDefinition="tinyint(1)  comment '是否锁定'")
    private Boolean locked=false;

    @Column(name="type",columnDefinition="varchar(100)  comment '用户类型'")
    private String type;

    @Column(name="rid",columnDefinition="int(11)  comment ''")
    private long rid;

    @Column(name="phone",columnDefinition="varchar(30)  comment ''")
    private String phone;

    @Column(name="register_date",columnDefinition="datetime  comment '注册时间'")
    private Date registerDate;


    public String getCredentialsSalt() {
        return userName + salt;
    }

}