package com.laman.fusion.base.enums;

/**
 * @Title: ENUM_USER_TYPE.java
 * @Description: 用户类型
 * @Author: Away
 * @Date: 2018/4/12 12:01
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
public enum ENUM_USER_TYPE {

    USER("user","普通用户"),
    ADMIN("admin","超级管理员"),
    MANAGER("manager","一般管理员");

    public final String name;
    public final String code;

    private ENUM_USER_TYPE(String code, String name){
        this.name=name;
        this.code=code;
    }


}
