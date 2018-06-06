package com.laman.biz.user.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
* @Title: UserScienceProject
* @Description:  科研项目
* @Author: Away
* @Date: 2018/5/30 19:33
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Getter
@Setter
public class UserScienceProjectDto extends BaseDto {

    /**用户ID**/
    private Long userId;

    /**起始时间**/
    private Date startTime;

    /**终止时间**/
    private Date endTime;

    /**项目名称**/
    private String projectName;

    /**项目组织单位**/
    private String projectOrg;

    /**个人贡献**/
    private String personalContribution;

}
