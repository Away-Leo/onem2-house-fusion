package com.onem2.biz.user.app.dto;

import com.onem2.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Title: UserInfoDto.java
 * @Description: 用户基本信息数据传输体
 * @Author: Away
 * @Date: 2018/4/12 14:31
 * @Copyright: 重庆壹平方米网络科技有限公司
 * @Version: V1.0
 */
@Getter
@Setter
public class UserInfoDto extends BaseDto {

    /**用户ID**/
    private Long userId;

    /**姓名**/
    private String name;

    /**用户头像**/
    private String headImg;

    /**证件号码**/
    private String idCard;

    /**手机号码**/
    private String phone;

    /**银行卡号**/
    private String accountNo;

    /**用户所在区域**/
    private String area;

    /**用户所在区域描述**/
    private String areaName;

    /**用户详细地址**/
    private String linkAddress;

    /**注册时间**/
    private Date registerDate;
}
