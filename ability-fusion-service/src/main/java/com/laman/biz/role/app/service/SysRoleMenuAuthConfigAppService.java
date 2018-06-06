package com.laman.biz.role.app.service;

import com.laman.biz.menu.app.dto.SysMenuConfigDto;
import com.laman.biz.menu.app.service.SysMenuConfigAppService;
import com.laman.biz.menu.domain.service.SysMenuConfigDomainService;
import com.laman.biz.role.app.dto.SysRoleMenuAuthConfigDto;
import com.laman.biz.role.domain.entity.SysRoleMenuAuthConfig;
import com.laman.biz.role.domain.service.SysRoleMenuAuthConfigDomainService;
import com.laman.biz.user.app.dto.UserInfoDto;
import com.laman.biz.user.app.service.UserAppService;
import com.laman.fusion.base.base.BaseAppService;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.enums.ENUM_USER_TYPE;
import com.laman.fusion.base.util.ObjectHelper;
import com.laman.fusion.base.util.ObjectProperUtil;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* @Title: SysRoleConfigDomainService
* @Description:  角色菜单权限配置应用服务
* @Author: Away
* @Date: 2018/6/1 17:48
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
@Transactional
public class SysRoleMenuAuthConfigAppService extends BaseAppService<SysRoleMenuAuthConfigDomainService>{

    private final UserAppService userAppService;

    private final SysMenuConfigAppService sysMenuConfigAppService;

    @Autowired
    public SysRoleMenuAuthConfigAppService(UserAppService userAppService,SysMenuConfigAppService sysMenuConfigAppService) {
        this.userAppService = userAppService;
        this.sysMenuConfigAppService=sysMenuConfigAppService;
    }

    /**
     * @Method:  findRoleMenuConf
     * @Author: Away
     * @Version: v1.0
     * @See: 根据角色获取菜单配置并组装树形菜单
     * @Param:
     * @Return: java.util.List<com.laman.biz.menu.app.dto.SysMenuConfigDto>
     * @Date: 2018/6/2 15:24
     */
    public List<SysMenuConfigDto> findRoleMenuConf(boolean isShowCommon) throws Exception{
        //获取当前登录用户基本信息
        UserInfoDto userInfoDto=userAppService.findCurrentUserInfo();
        //获取当前登录用户的角色信息
        String roleCodes=userInfoDto.getRoles();
        if(ObjectHelper.isNotEmpty(roleCodes)){
            //如果是超级管理员则查找所有菜单
            if(roleCodes.contains(ENUM_USER_TYPE.ADMIN.code)){
                return sysMenuConfigAppService.getAdminTree();
            }
            String[] roleArray=roleCodes.split(",");
            //查找当前角色的菜单配置
            List<SysMenuConfigDto> menuConfs=BDS.findMenuByRoleCodesAndCast(Arrays.asList(roleArray));
            if(isShowCommon){
                //查找公共菜单
                SysMenuConfigDto menuConfigDto=new SysMenuConfigDto();
                menuConfigDto.setCommon(true);
                List<SysMenuConfigDto>  menuConfigDtos=this.sysMenuConfigAppService.findListByConditions(menuConfigDto);
                menuConfs.addAll(menuConfigDtos);
            }
            return SysMenuConfigDomainService.createTreeMenus(menuConfs);
        }else{
            return null;
        }
    }

    /**
     * @Method:  batchSaveMenuAuth
     * @Author: Away
     * @Version: v1.0
     * @See: 批量保存角色菜单权限配置
     * @Param: roleCode
     * @Param: dtos
     * @Return: java.util.List<com.laman.biz.role.app.dto.SysRoleMenuAuthConfigDto>
     * @Date: 2018/6/5 17:56
     */
    public List<SysRoleMenuAuthConfigDto> batchSaveMenuAuth(String roleCode,List<SysRoleMenuAuthConfigDto> dtos) throws Exception{
        if(ObjectHelper.isNotEmpty(roleCode)&&ObjectHelper.isNotEmpty(dtos)){
            List<SysRoleMenuAuthConfig> toSaveDatas=new ArrayList<>();
            for(SysRoleMenuAuthConfigDto temp:dtos){
                if(ObjectHelper.isEmpty(this.BDS.findByRoleCodeAndMenuCode(roleCode,temp.getMenuCode()))){
                    toSaveDatas.add(ObjectProperUtil.compareAndValue(temp,new SysRoleMenuAuthConfig(),true,new String[]{"id","rawVersion"}));
                }
            }
            return this.BDS.batchSave(toSaveDatas,SysRoleMenuAuthConfigDto.class);
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

}
