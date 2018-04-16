package com.onem2.fusion.base.enums;

/**
 * @Title: ENUM_EXCEPTION.java
 * @Description: 异常信息定义枚举类
 * @Author: Away
 * @Date: 2018/4/12 12:01
 * @Copyright: 重庆壹平方米网络科技有限公司
 * @Version: V1.0
 */
public enum ENUM_EXCEPTION {

    E10001("E10001","参数为空"),
    E10002("E10002","根据参数未找到相应数据"),
    E10003("E10003","传入参数有误"),
    E10004("E10004","复制出错"),
    E10005("E10005","获取当前登录用户失败"),
    E10006("E10006","未找到相应配置"),
    E10007("E10007","逻辑删除相应数据出错"),
    E10008("E10008","保存数据出错"),
    E10009("E10009","更新数据出错"),
    E10010("E10010","已经存在相同账号用户"),
    E10011("E10011","不存在此用户"),
    E10012("E10012","Token已经过期"),
    E10013("E10013","Token格式错误"),
    E10014("E10014","Token没有被正确构造"),
    E10015("E10015","签名失败"),
    E10016("E10016","非法参数异常");

    public final String msg;
    public final String code;

    private ENUM_EXCEPTION(String code,String msg){
        this.msg=msg;
        this.code=code;
    }


}
