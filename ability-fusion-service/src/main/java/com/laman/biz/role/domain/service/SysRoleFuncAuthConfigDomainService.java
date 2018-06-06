package com.laman.biz.role.domain.service;

import com.laman.biz.role.app.dto.SysRoleFuncAuthConfigDto;
import com.laman.biz.role.domain.entity.SysRoleFuncAuthConfig;
import com.laman.biz.role.domain.repository.SysRoleFuncAuthConfigRepository;
import com.laman.fusion.base.base.BaseDomainService;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
* @Title: SysRoleConfigDomainService
* @Description:  角色功能权限配置数据服务
* @Author: Away
* @Date: 2018/6/1 17:48
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
public class SysRoleFuncAuthConfigDomainService extends BaseDomainService<SysRoleFuncAuthConfigRepository,SysRoleFuncAuthConfig,SysRoleFuncAuthConfigDto>{

    /**
     * @Method:  findByRoleCodes
     * @Author: Away
     * @Version: v1.0
     * @See: 根据角色编号查找功能权限列表
     * @Param: roleCodes
     * @Return: java.util.List<com.laman.biz.role.app.dto.SysRoleFuncAuthConfigDto>
     * @Date: 2018/6/3 17:25
     */
    public List<SysRoleFuncAuthConfigDto> findByRoleCodes(String roleCodes){
        if(ObjectHelper.isNotEmpty(roleCodes)){
            return toDtoList(this.CT.findDistinctByRoleCodeIn(Arrays.asList(roleCodes.split(","))),SysRoleFuncAuthConfigDto.class);
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }
}
