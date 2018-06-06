package com.laman.biz.user.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
* @Title: UserInspectionWork
* @Description:  重要工作或检查工作
* @Author: Away
* @Date: 2018/5/30 19:33
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Getter
@Setter
public class UserInspectionWorkDto extends BaseDto {

    /**用户ID**/
    private Long userId;

    /**起始时间**/
    private Date startTime;

    /**终止时间**/
    private Date endTime;

    /**工作名称**/
    private String workName;

    /**组织单位**/
    private String org;

    /**参与身份**/
    private String participationIdentity;

}
