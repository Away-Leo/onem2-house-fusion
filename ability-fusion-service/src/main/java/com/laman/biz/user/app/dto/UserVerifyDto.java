package com.laman.biz.user.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

/**
* @Title: UserVerify
* @Description:  用户审核
* @Author: Away
* @Date: 2018/6/5 14:47
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Getter
@Setter
public class UserVerifyDto extends BaseDto {

    /**用户ID**/
    private Long userId;

    /**审核意见**/
    private String content;

    /**审核结果**/
    private Integer result;

}
