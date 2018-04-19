package com.onem2.biz.user.app.dto;

//import BaseDto;

import com.onem2.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
* @Title: User.java
* @Description: 用户
* @author Away
* @date 2018/2/7 14:04
* @copyright 重庆壹平方米网络科技有限公司
* @version V1.0
*/
@Getter
@Setter
public class UserDto extends BaseDto {

    /**用户帐号**/
    private String userName;

    /**用户显示名称**/
    private String displayName;

    /**密码**/
    private String password;

    /**加密盐**/
    private String salt;

    /**角色ID**/
    private String roleIds;

    /**是否锁定**/
    private Boolean locked=false;

    /**用户类型**/
    private String type;

    /**/
    private long rid;

    /**/
    private String phone;

    /**/
    private Date registerDate;

    /**具有权限的接口**/
    private String authUrls;

    /**平台编码**/
    private String platformCode;

    /**私有密钥**/
    private String privateKey;

}