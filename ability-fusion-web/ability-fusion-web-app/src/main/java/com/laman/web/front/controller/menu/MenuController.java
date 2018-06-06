package com.laman.web.front.controller.menu;

import com.laman.biz.menu.app.dto.SysMenuConfigDto;
import com.laman.biz.menu.app.service.SysMenuConfigAppService;
import com.laman.biz.menu.app.service.SysUrlsAppService;
import com.laman.biz.menu.app.service.UserFunctionAuthConfAppService;
import com.laman.biz.menu.app.service.UserMenuAuthConfAppService;
import com.laman.biz.role.app.service.SysRoleMenuAuthConfigAppService;
import com.laman.fusion.base.dtos.DataTablesPage;
import com.laman.fusion.base.page.DataTablesPageRequest;
import com.laman.web.common.dto.CPViewResultInfo;
import com.laman.web.front.controller.AbstractFrontController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**   
* @Title: MenuController.java
* @Description:  菜单控制器
* @Aauthor: Away
* @Date: 2018/2/8 16:48
* @Copyright: 重庆拉曼科技有限公司
* @version V1.0   
*/
@Slf4j
@RestController
public class MenuController extends AbstractFrontController {

    private final UserMenuAuthConfAppService userMenuAuthConfAppService;

    private final UserFunctionAuthConfAppService userFunctionAuthConfAppService;

    private final SysMenuConfigAppService sysMenuConfigAppService;

    private final SysUrlsAppService sysUrlsAppService;

    private final SysRoleMenuAuthConfigAppService sysRoleMenuAuthConfigAppService;


    @Autowired
    public MenuController(UserMenuAuthConfAppService userMenuAuthConfAppService, UserFunctionAuthConfAppService userFunctionAuthConfAppService, SysMenuConfigAppService sysMenuConfigAppService, SysUrlsAppService sysUrlsAppService, SysRoleMenuAuthConfigAppService sysRoleMenuAuthConfigAppService) {
        this.userMenuAuthConfAppService = userMenuAuthConfAppService;
        this.userFunctionAuthConfAppService = userFunctionAuthConfAppService;
        this.sysMenuConfigAppService = sysMenuConfigAppService;
        this.sysUrlsAppService = sysUrlsAppService;
        this.sysRoleMenuAuthConfigAppService = sysRoleMenuAuthConfigAppService;
    }

    /**
     * @Method:  getMenu
     * @Author: Away
     * @Version: v1.0
     * @See: 菜单-根据用户获得菜单
     * @Param: info
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/4 11:02
     */
    @GetMapping(value = "/menu/getMenu.json",name = "菜单-根据用户获得菜单树")
    public CPViewResultInfo getMenu(CPViewResultInfo info){
        try{
            info.newSuccess(sysRoleMenuAuthConfigAppService.findRoleMenuConf(true));
        }catch (Exception e){
            info.newFalse(e);
            log.error("菜单-根据用户获得菜单",e);
        }
        return info;
    }

    /**
     * @Method:  getAllMenuTreeWithoutCommon
     * @Author: Away
     * @Version: v1.0
     * @See: 查找所有非公共菜单
     * @Param: info
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/1 16:53
     */
    @GetMapping(value = "/menu/getAllMenuTreeWithoutCommon.json",name = "菜单-获取所有非公共菜单树")
    public CPViewResultInfo getAllMenuTreeWithoutCommon(CPViewResultInfo info){
        try{
            info.newSuccess(sysMenuConfigAppService.getAllMenuTreeWithoutCommon());
        }catch (Exception e){
            info.newFalse(e);
            log.error("菜单-获取所有非公共菜单",e);
        }
        return info;
    }

    /**
     * @Method:  getAllMenu
     * @Author: Away
     * @Version: v1.0
     * @See: 获取所有菜单
     * @Param: info
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/1 16:53
     */
    @GetMapping(value = "/menu/getAllMenu.json",name = "菜单-获取所有菜单树")
    public CPViewResultInfo getAllMenu(CPViewResultInfo info){
        try{
            info.newSuccess(sysMenuConfigAppService.getAdminTree());
        }catch (Exception e){
            info.newFalse(e);
            log.error("菜单-获取所有菜单",e);
        }
        return info;
    }

//    @GetMapping(value = "/menu/getMenuWithUserId.json",name = "菜单-根据给定用户ID获得菜单配置")
//    public CPViewResultInfo getMenu(CPViewResultInfo info,Long id){
//        try{
//            info.setData(userMenuAuthConfAppService.findAndPackByUserWithId(id));
//            info.setSuccess(true);
//        }catch (Exception e){
//            info.setSuccess(false);
//            info.setMessage(e.getMessage());
//            e.printStackTrace();
//            log.error("菜单-根据给定用户ID获得菜单配置",e);
//        }
//        return info;
//    }


    /**
     * @Author: Away
     * @Description: 编辑系统菜单
     * @Param: info
     * @Param: sysMenuConfigDto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/2/23 10:52
     * @Copyright: 重庆拉曼科技有限公司
     */
    @PostMapping(value = "/menu/editSysMenu.json",name = "菜单-编辑系统配置菜单")
    public CPViewResultInfo editSysMenu(CPViewResultInfo info,@RequestBody SysMenuConfigDto sysMenuConfigDto){
        try{
            info.setData(sysMenuConfigAppService.saveOrUpdate(sysMenuConfigDto));
            info.setSuccess(true);
        }catch (Exception e){
            info.setSuccess(false);
            info.setMessage(e.getMessage());
            e.printStackTrace();
            log.error("编辑系统菜单出错",e);
        }
        return info;
    }

    /**
     * @Author: Away
     * @Description: 获得已经配置系统菜单列表
     * @Param: info
     * @Return com.laman.web.common.dto.CPViewResultInfo
     * @Date 2018/2/23 11:02
     * @Copyright 重庆拉曼科技有限公司
     */
    @GetMapping(value = "/menu/getSysMenuList.json",name = "菜单-获得系统菜单列表")
    public CPViewResultInfo getSysMenuList(DataTablesPage dataTablesPage, CPViewResultInfo info, SysMenuConfigDto condition){
        try{
            Page<SysMenuConfigDto> sourceData=this.sysMenuConfigAppService.findByConditions(new DataTablesPageRequest(dataTablesPage),condition);
            info.setData(sourceData);
            info.setSuccess(true);
            info.setDraw(dataTablesPage.getDraw());
        }catch (Exception e){
            info.setSuccess(false);
            info.setMessage(e.getMessage());
            e.printStackTrace();
            log.error("获得已经配置系统菜单列表出错",e);
        }
        return info;
    }

    /**
     * @Author: Away
     * @Description: 根据ID删除菜单
     * @Param: info
     * @Param: id
     * @Return com.laman.web.common.dto.CPViewResultInfo
     * @Date 2018/2/23 14:23
     * @Copyright 重庆拉曼科技有限公司
     */
    @GetMapping(value = "/menu/deleteSysMenu.json",name = "菜单-删除菜单(同时也会删除子菜单以及角色已经配置好的菜单权限)")
    public CPViewResultInfo deleteSysMenu(CPViewResultInfo info,String menuCodes){
        try{
            this.sysMenuConfigAppService.deleteMenuAndChildAndMenuAuth(menuCodes);
            info.setSuccess(true);
        }catch (Exception e){
            info.setSuccess(false);
            info.setMessage(e.getMessage());
            e.printStackTrace();
            log.error("根据ID删除菜单出错",e);
        }
        return info;
    }

//    /**
//     * @Author: Away
//     * @Description: 新增用户菜单权限
//     * @Param: info
//     * @Param: sourceDatas
//     * @Return com.laman.web.common.dto.CPViewResultInfo
//     * @Date 2018/3/2 17:50
//     * @Copyright 重庆拉曼科技有限公司
//     */
//    @PostMapping(value = "/menu/saveUserMenuAuth.json",name = "菜单-新增用户菜单权限")
//    public CPViewResultInfo saveUserMenuAuth(CPViewResultInfo info, @RequestBody List<UserMenuAuthConfDto> sourceDatas){
//        try{
//            info.setData(userMenuAuthConfAppService.saveUserMenuAuth(sourceDatas));
//            info.setSuccess(true);
//        }catch (Exception e){
//            info.setSuccess(false);
//            info.setMessage(e.getMessage());
//            e.printStackTrace();
//            log.error("菜单-新增用户菜单权限",e);
//        }
//        return info;
//    }

//    /**
//     * @Author: Away
//     * @Description: 根据用户获得功能列表
//     * @Param: dataTablesPage
//     * @Param: info
//     * @Param: condition
//     * @Return com.laman.web.common.dto.CPViewResultInfo
//     * @Date 2018/3/5 14:39
//     * @Copyright 重庆拉曼科技有限公司
//     */
//    @GetMapping(value = "/menu/getUserFunAuthPage.json",name = "菜单-根据用户获得已授权列表")
//    public CPViewResultInfo getUserFunAuthPage(DataTablesPage dataTablesPage, CPViewResultInfo info, UserFunctionAuthConfDto condition){
//        try{
//            Page<UserFunctionAuthConfDto> sourceData=this.userFunctionAuthConfAppService.findByConditions(new DataTablesPageRequest(dataTablesPage),condition);
//            info.setData(sourceData);
//            info.setSuccess(true);
//            info.setDraw(dataTablesPage.getDraw());
//        }catch (Exception e){
//            info.setSuccess(false);
//            info.setMessage(e.getMessage());
//            e.printStackTrace();
//            log.error("根据用户获得已授权列表",e);
//        }
//        return info;
//    }

//    /**
//     * @Author: Away
//     * @Description: 获得所有功能地址
//     * @Param: dataTablesPage
//     * @Param: info
//     * @Param: condition
//     * @Return: com.laman.web.common.dto.CPViewResultInfo
//     * @Date: 2018/3/5 14:39
//     * @Copyright: 重庆拉曼科技有限公司
//     */
//    @GetMapping(value = "/menu/getAllFunctionUrl.json",name = "菜单-获得所有功能地址")
//    public CPViewResultInfo getAllFunctionUrl(DataTablesPage dataTablesPage, CPViewResultInfo info, SysUrlsDto condition){
//        try{
//            Page<SysUrlsDto> sourceData=this.sysUrlsAppService.findByConditions(new DataTablesPageRequest(dataTablesPage),condition);
//            info.setData(sourceData);
//            info.setSuccess(true);
//            info.setDraw(dataTablesPage.getDraw());
//        }catch (Exception e){
//            info.setSuccess(false);
//            info.setMessage(e.getMessage());
//            e.printStackTrace();
//            log.error("根据用户获得已授权列表",e);
//        }
//        return info;
//    }

//    /**
//     * @Author: Away
//     * @Description: 保存用户功能权限
//     * @Param: dataTablesPage
//     * @Param: info
//     * @Param: condition
//     * @Return com.laman.web.common.dto.CPViewResultInfo
//     * @Date 2018/3/5 14:39
//     * @Copyright 重庆拉曼科技有限公司
//     */
//    @PostMapping(value = "/menu/saveUserFunAuth.json",name = "菜单-保存用户功能权限")
//    public CPViewResultInfo saveUserFunAuth(CPViewResultInfo info, @RequestBody List<UserFunctionAuthConfDto> toSaveDatas){
//        try{
//            this.userFunctionAuthConfAppService.saveFunctionAuth(toSaveDatas);
//            info.setSuccess(true);
//        }catch (Exception e){
//            info.setSuccess(false);
//            info.setMessage(e.getMessage());
//            e.printStackTrace();
//            log.error("保存用户功能权限",e);
//        }
//        return info;
//    }

//    /**
//     * @Author: Away
//     * @Description; 通过ids删除用户功能权限设置
//     * @Param: info
//     * @Param: ids
//     * @Return com.laman.web.common.dto.CPViewResultInfo
//     * @Date 2018/3/5 19:40
//     * @Copyright 重庆拉曼科技有限公司
//     */
//    @GetMapping(value = "/menu/deleteByIds.json",name = "菜单-通过ids删除用户功能权限")
//    public CPViewResultInfo deleteByIds(CPViewResultInfo info,String ids){
//        try{
//            this.userFunctionAuthConfAppService.deleteByIds(ids);
//            info.setSuccess(true);
//        }catch (Exception e){
//            info.setSuccess(false);
//            info.setMessage(e.getMessage());
//            e.printStackTrace();
//            log.error("通过ids删除用户功能权限",e);
//        }
//        return info;
//    }



}
