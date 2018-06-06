package com.laman.biz.role.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


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
public class SysRoleMenuAuthConfigParaDto extends BaseDto{

    /**角色编号**/
    private String roleCode;

    private List<SysRoleMenuAuthConfigDto> saveList;
}
