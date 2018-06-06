package com.laman.biz.user.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Title: UserInfoDto.java
 * @Description: 用户基本信息数据传输体
 * @Author: Away
 * @Date: 2018/4/12 14:31
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@Getter
@Setter
public class UserInfoDto extends BaseDto {

    /**用户ID**/
    private Long userId;

    /**姓名**/
    private String name;

    /**角色 比如  user,manager,admin  多个角色用逗号隔开**/
    private String roles;

    /**用户头像**/
    private String headImg;

    /**其他**/
    private Integer sex;

    /**出生年月**/
    private Date birthday;

    /**鉴别编号 1待选,2注销，3专家**/
    private Integer identifyCode;

    /**民族名称**/
    private String nationName;

    /**民族编号**/
    private String nationCode;

    /**学历编号**/
    private String educationCode;

    /**学历名称**/
    private String educationName;

    /**政治面貌编号**/
    private String politicalOutlookCode;

    /**政治面貌名称**/
    private String politicalOutlookName;

    /**技术职称**/
    private String technicalTitle;

    /**籍贯编号**/
    private String nativePlaceCode;

    /**籍贯名称**/
    private String nativePlaceName;

    /**行政职级编号**/
    private String administrativePostCode;

    /**行政职级名称**/
    private String administrativePostName;

    /**电子邮箱**/
    private String email;

    /**工作单位**/
    private String workUnit;

    /**工作电话**/
    private String workPhone;

    /**邮政编码**/
    private String postCode;

    /**工作时间**/
    private Date workTime;

    /**证件号码**/
    private String idCard;

    /**手机号码**/
    private String phone;

    /**用户邮箱**/
    private String emailAddress;

    /**人才称号**/
    private String personnelName;

    /**自荐领域**/
    private String recommendationArea;

    /**注册时间**/
    private Date registerDate;
}
