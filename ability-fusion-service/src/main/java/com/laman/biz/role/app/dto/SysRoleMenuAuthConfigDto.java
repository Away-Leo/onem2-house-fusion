package com.laman.biz.role.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;


/**
* @Title: SysRoleConfig
* @Description:  角色菜单权限配置表
* @Author: Away
* @Date: 2018/6/1 15:45
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Getter
@Setter
public class SysRoleMenuAuthConfigDto extends BaseDto{

    /**角色编号**/
    private String roleCode;

    /**菜单名称**/
    private String menuName;

    /**菜单编码**/
    private String menuCode;

    /**上级菜单编号**/
    private String menuParentCode;

    /**上级菜单名称**/
    private String menuParentName;

    /**菜单链接地址**/
    private String menuUrl;

    /**菜单顺序**/
    private Integer ordby;

    /**菜单状态**/
    private Integer state;

}
