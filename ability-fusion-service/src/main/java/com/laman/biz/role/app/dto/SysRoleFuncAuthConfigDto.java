package com.laman.biz.role.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

/**
* @Title: SysRoleFuncAuthConfig
* @Description:  角色功能权限配置
* @Author: Away
* @Date: 2018/6/1 16:55
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Getter
@Setter
public class SysRoleFuncAuthConfigDto extends BaseDto{

    /**角色编号**/
    private String roleCode;

    /**功能地址**/
    private String funcUrl;

    /**功能名称**/
    private String funcName;

}
