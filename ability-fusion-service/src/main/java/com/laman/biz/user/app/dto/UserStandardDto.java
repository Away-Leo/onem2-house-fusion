package com.laman.biz.user.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
* @Title: UserStandard
* @Description:  标准制定
* @Author: Away
* @Date: 2018/5/30 19:33
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Getter
@Setter
public class UserStandardDto extends BaseDto {

    /**用户ID**/
    private Long userId;

    /**起始时间**/
    private Date startTime;

    /**终止时间**/
    private Date endTime;

    /**标准名称**/
    private String standardName;

    /**标准编号**/
    private String standardCode;

    /**参与身份**/
    private String participationIdentity;

}
