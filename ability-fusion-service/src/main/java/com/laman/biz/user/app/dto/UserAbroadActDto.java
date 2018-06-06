package com.laman.biz.user.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
* @Title: UserAbroadAct
* @Description:  海外活动
* @Author: Away
* @Date: 2018/5/30 19:33
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Getter
@Setter
public class UserAbroadActDto extends BaseDto {

    /**用户ID**/
    private Long userId;

    /**开始时间**/
    private Date startTime;

    /**结束时间**/
    private Date endTime;

    /**学习或活动内容**/
    private String content;

    /**工作职责**/
    private String workDuty;

}
