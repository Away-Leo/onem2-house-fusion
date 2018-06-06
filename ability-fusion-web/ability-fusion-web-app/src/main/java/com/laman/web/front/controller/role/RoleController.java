package com.laman.web.front.controller.role;

import com.laman.biz.role.app.dto.SysRoleConfigDto;
import com.laman.biz.role.app.dto.SysRoleFuncAuthConfigDto;
import com.laman.biz.role.app.dto.SysRoleMenuAuthConfigDto;
import com.laman.biz.role.app.dto.SysRoleMenuAuthConfigParaDto;
import com.laman.biz.role.app.service.SysRoleConfigAppService;
import com.laman.biz.role.app.service.SysRoleFuncAuthConfigAppService;
import com.laman.biz.role.app.service.SysRoleMenuAuthConfigAppService;
import com.laman.biz.role.domain.entity.SysRoleConfig;
import com.laman.biz.role.domain.entity.SysRoleFuncAuthConfig;
import com.laman.web.common.dto.CPViewResultInfo;
import com.laman.web.front.controller.AbstractFrontController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* @Title: RoleController
* @Description:  
* @Author: Away
* @Date: 2018/6/2 11:08
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Slf4j
@RestController
public class RoleController extends AbstractFrontController{

    /**角色功能权限配置**/
    private final SysRoleFuncAuthConfigAppService sysRoleFuncAuthConfigAppService;

    /**角色菜单权限配置**/
    private final SysRoleMenuAuthConfigAppService sysRoleMenuAuthConfigAppService;

    /**角色配置**/
    private final SysRoleConfigAppService sysRoleConfigAppService;

    @Autowired
    public RoleController(SysRoleConfigAppService sysRoleConfigAppService,SysRoleFuncAuthConfigAppService sysRoleFuncAuthConfigAppService,SysRoleMenuAuthConfigAppService sysRoleMenuAuthConfigAppService) {
        this.sysRoleConfigAppService = sysRoleConfigAppService;
        this.sysRoleFuncAuthConfigAppService=sysRoleFuncAuthConfigAppService;
        this.sysRoleMenuAuthConfigAppService=sysRoleMenuAuthConfigAppService;
    }

    /**
     * @Method:  saveOrUpdateRole
     * @Author: Away
     * @Version: v1.0
     * @See: 角色管理-新增或修改角色
     * @Param: info
     * @Param: sysRoleConfigDto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/2 14:03
     */
    @PostMapping(value = "/role/saveOrUpdateRole.json",name = "角色管理-新增或修改角色")
    public CPViewResultInfo saveOrUpdateRole(CPViewResultInfo info, @RequestBody SysRoleConfigDto sysRoleConfigDto){
        try{
            info.newSuccess(this.sysRoleConfigAppService.saveOrUpdateData(sysRoleConfigDto, SysRoleConfig.class));
        }catch (Exception e){
            info.newFalse(e);
            log.error("角色管理-新增或修改角色",e);
        }
        return info;
    }

    /**
     * @Method:  getRoleConfList
     * @Author: Away
     * @Version: v1.0
     * @See: 角色管理-获取角色列表
     * @Param: info
     * @Param: dto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/4 18:02
     */
    @GetMapping(value = "/role/getRoleConfList.json",name = "角色管理-获取角色列表")
    public CPViewResultInfo getRoleConfList(CPViewResultInfo info,SysRoleConfigDto  dto){
        try{
            info.newSuccess(this.sysRoleConfigAppService.findListByConditions(dto));
        }catch (Exception e){
            info.newFalse(e);
            log.error("角色管理-获取角色列表",e);
        }
        return info;
    }

    /**
     * @Method:  getRoleConfPage
     * @Author: Away
     * @Version: v1.0
     * @See: 角色管理-获取角色分页列表
     * @Param: info
     * @Param: pageRequest
     * @Param: dto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/4 18:04
     */
    @PostMapping(value = "/role/getRoleConfPage.json",name = "角色管理-获取角色分页列表")
    public CPViewResultInfo getRoleConfPage(CPViewResultInfo info, @RequestBody SysRoleConfigDto  dto){
        try{
            info.newSuccess(this.sysRoleConfigAppService.findPageByConditions(new PageRequest(dto.getPage(),dto.getSize()),dto));
        }catch (Exception e){
            info.newFalse(e);
            log.error("角色管理-获取角色分页列表",e);
        }
        return info;
    }

    /**
     * @Method:  deleteRole
     * @Author: Away
     * @Version: v1.0
     * @See: 物理删除角色
     * @Param: info
     * @Param: sysRoleConfigDto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/2 14:09
     */
    @PostMapping(value = "/role/deleteRole.json",name = "角色管理-删除角色")
    public CPViewResultInfo deleteRole(CPViewResultInfo info, @RequestBody SysRoleConfigDto sysRoleConfigDto){
        try{
            this.sysRoleConfigAppService.physicalDelete(sysRoleConfigDto.getId());
            info.newSuccess(null);
        }catch (Exception e){
            info.newFalse(e);
            log.error("角色管理-新增或修改角色",e);
        }
        return info;
    }

    /**
     * @Method:  getConfdMenu
     * @Author: Away
     * @Version: v1.0
     * @See: 角色管理-根据角色获取已经配置好的树形菜单
     * @Param: info
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/2 16:48
     */
    @GetMapping(value = "/role/getConfdMenu.json",name = "角色管理-根据角色获取已经配置好的树形菜单")
    public CPViewResultInfo getConfdMenu(CPViewResultInfo info){
        try{
            info.newSuccess(this.sysRoleMenuAuthConfigAppService.findRoleMenuConf(false));
        }catch (Exception e){
            info.newFalse(e);
            log.error("角色管理-根据角色获取已经配置好的树形菜单",e);
        }
        return info;
    }

    /**
     * @Method:  batchSaveMenuConf
     * @Author: Away
     * @Version: v1.0
     * @See: 角色管理-批量保存角色菜单权限配置
     * @Param: info
     * @Param: source
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/3 16:00
     */
    @PostMapping(value = "/role/batchSaveMenuConf.json",name = "角色管理-批量保存角色菜单权限配置")
    public CPViewResultInfo batchSaveMenuConf(CPViewResultInfo info, @RequestBody SysRoleMenuAuthConfigParaDto roleMenuAuthConfigParaDto){
        try{
            info.newSuccess(this.sysRoleMenuAuthConfigAppService.batchSaveMenuAuth(roleMenuAuthConfigParaDto.getRoleCode(),roleMenuAuthConfigParaDto.getSaveList()));
        }catch (Exception e){
            info.newFalse(e);
            log.error("角色管理-批量保存角色菜单权限配置",e);
        }
        return info;
    }

    /**
     * @Method:  batchDeleteMenuConf
     * @Author: Away
     * @Version: v1.0
     * @See: 角色管理-批量删除菜单配置
     * @Param: info
     * @Param: ids
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/2 16:48
     */
    @PostMapping(value = "/role/batchDeleteMenuConf.json",name = "角色管理-批量删除菜单配置")
    public CPViewResultInfo batchDeleteMenuConf(CPViewResultInfo info,@RequestBody SysRoleMenuAuthConfigDto dto){
        try{
            this.sysRoleMenuAuthConfigAppService.batchDelete(dto.getIds());
            info.newSuccess(null);
        }catch (Exception e){
            info.newFalse(e);
            log.error("",e);
        }
        return info;
    }

    /**
     * @Method:  batchSaveRoleFuncAuthConf
     * @Author: Away
     * @Version: v1.0
     * @See: 角色管理-批量保存角色功能权限
     * @Param: info
     * @Param: source
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/3 16:24
     */
    @PostMapping(value = "/role/batchSaveRoleFuncAuthConf.json",name = "角色管理-批量保存角色功能权限")
    public CPViewResultInfo batchSaveRoleFuncAuthConf(CPViewResultInfo info, @RequestBody List<SysRoleFuncAuthConfigDto> source){
        try{
            info.newSuccess(this.sysRoleFuncAuthConfigAppService.batchSave(source,SysRoleFuncAuthConfigDto.class, SysRoleFuncAuthConfig.class));
        }catch (Exception e){
            info.newFalse(e);
            log.error("角色管理-批量保存角色功能权限",e);
        }
        return info;
    }

    /**
     * @Method:  batchDeleteRoleFuncAuthConf
     * @Author: Away
     * @Version: v1.0
     * @See:  角色管理-批量删除角色功能权限
     * @Param: info
     * @Param: sysRoleFuncAuthConfigDto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/3 16:35
     */
    @PostMapping(value = "/role/batchDeleteRoleFuncAuthConf.json",name = "角色管理-批量删除角色功能权限")
    public CPViewResultInfo batchDeleteRoleFuncAuthConf(CPViewResultInfo info,@RequestBody SysRoleFuncAuthConfigDto sysRoleFuncAuthConfigDto){
        try{
            this.sysRoleFuncAuthConfigAppService.batchDelete(sysRoleFuncAuthConfigDto.getIds());
            info.newSuccess(null);
        }catch (Exception e){
            info.newFalse(e);
            log.error("角色管理-批量删除角色功能权限",e);
        }
        return info;
    }

}
