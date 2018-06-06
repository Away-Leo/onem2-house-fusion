package com.laman.biz.user.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;


/**
 * @Title: UserPrivateKey.java
 * @Description: 用户私有数据传输体
 * @Author: Away
 * @Date: 2018/4/18 15:05
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@Getter
@Setter
public class UserPrivateKeyDto extends BaseDto {

    /**用户ID**/
    private Long userId;

    /**平台编号**/
    private String platformCode;

    /**私有密钥**/
    private String privateKey;

}