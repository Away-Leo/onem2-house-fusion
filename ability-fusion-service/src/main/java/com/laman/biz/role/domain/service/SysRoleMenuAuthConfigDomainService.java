package com.laman.biz.role.domain.service;

import com.laman.biz.menu.app.dto.SysMenuConfigDto;
import com.laman.biz.role.app.dto.SysRoleMenuAuthConfigDto;
import com.laman.biz.role.domain.entity.SysRoleMenuAuthConfig;
import com.laman.biz.role.domain.repository.SysRoleMenuAuthConfigRepository;
import com.laman.fusion.base.base.BaseDomainService;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @Title: SysRoleConfigDomainService
* @Description:  角色菜单权限配置数据服务
* @Author: Away
* @Date: 2018/6/1 17:48
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
public class SysRoleMenuAuthConfigDomainService extends BaseDomainService<SysRoleMenuAuthConfigRepository,SysRoleMenuAuthConfig,SysRoleMenuAuthConfigDto>{


    /**
     * @Method:  findByRoleCodes
     * @Author: Away
     * @Version: v1.0
     * @See: 根据多个角色查找去重菜单
     * @Param: roleCodes
     * @Return: java.util.List<com.laman.biz.role.app.dto.SysRoleMenuAuthConfigDto>
     * @Date: 2018/6/2 14:44
     */
    public List<SysRoleMenuAuthConfigDto> findByRoleCodes(List<String> roleCodes) throws BusinessException{
        if(ObjectHelper.isNotEmpty(roleCodes)){
            return toDtoList(this.CT.findDistinctByRoleCodeIn(roleCodes),SysRoleMenuAuthConfigDto.class);
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @Method:  findMenuByRoleCodesAndCast
     * @Author: Away
     * @Version: v1.0
     * @See: 根据角色查找具有权限的菜单树
     * @Param: roleCodes
     * @Return: java.util.List<com.laman.biz.menu.app.dto.SysMenuConfigDto>
     * @Date: 2018/6/4 10:05
     */
    public List<SysMenuConfigDto> findMenuByRoleCodesAndCast(List<String> roleCodes) throws Exception{
        //查找权限配置
        List<SysRoleMenuAuthConfigDto> source=this.findByRoleCodes(roleCodes);
        //转化数据类型
        if(ObjectHelper.isNotEmpty(source)){
            List<SysMenuConfigDto> returnData=new ArrayList<>();
            for(SysRoleMenuAuthConfigDto temp:source){
                SysMenuConfigDto dto=new SysMenuConfigDto();
                BeanUtils.copyProperties(temp,dto);
                returnData.add(dto);
            }
            return returnData;
        }else{
            return null;
        }
    }

    /**
     * @Method:  deleteByMenuCodeAndRoleCode
     * @Author: Away
     * @Version: v1.0
     * @See: 根据角色编号和菜单编号删除
     * @Param: menuCode
     * @Param: roleCode
     * @Return: void
     * @Date: 2018/6/5 15:58
     */
    public void deleteByMenuCodeAndRoleCode(String menuCode,List<String> roleCode) throws BusinessException{
        if(ObjectHelper.isNotEmpty(menuCode)&&ObjectHelper.isNotEmpty(roleCode)){
            CT.deleteByMenuCodeAndRoleCodeIn(menuCode, roleCode);
        }else {
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @Method:  deleteByMenuCodeAndRoleCode
     * @Author: Away
     * @Version: v1.0
     * @See: 根据单编号删除
     * @Param: menuCode
     * @Param: roleCode
     * @Return: void
     * @Date: 2018/6/5 15:58
     */
    public void deleteByMenuCode(String menuCode) throws BusinessException{
        if(ObjectHelper.isNotEmpty(menuCode)){
            CT.deleteByMenuCode(menuCode);
        }else {
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @Method:  findByRoleCodeAndMenuCode
     * @Author: Away
     * @Version: v1.0
     * @See: 根据角色编号和菜单编号查找
     * @Param: roleCode
     * @Param: menuCode
     * @Return: com.laman.biz.role.app.dto.SysRoleMenuAuthConfigDto
     * @Date: 2018/6/5 17:41
     */
    public SysRoleMenuAuthConfigDto findByRoleCodeAndMenuCode(String roleCode,String menuCode) throws BusinessException{
        if(ObjectHelper.isNotEmpty(roleCode)&&ObjectHelper.isNotEmpty(menuCode)){
            return toDto(CT.findByRoleCodeAndMenuCode(roleCode, menuCode),SysRoleMenuAuthConfigDto.class);
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

}
