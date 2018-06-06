package com.laman.biz.user.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
* @Title: UserRewardSituation
* @Description:  奖励情况
* @Author: Away
* @Date: 2018/5/30 19:33
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Getter
@Setter
public class UserRewardSituationDto extends BaseDto {

    /**用户ID**/
    private Long userId;

    /**奖励时间**/
    private Date rewardTime;

    /**奖励名称**/
    private String rewardName;

    /**组织单位**/
    private String org;

    /**参与身份**/
    private String participationIdentity;

}
