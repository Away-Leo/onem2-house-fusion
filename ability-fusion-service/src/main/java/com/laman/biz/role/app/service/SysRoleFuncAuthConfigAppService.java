package com.laman.biz.role.app.service;

import com.laman.biz.role.app.dto.SysRoleFuncAuthConfigDto;
import com.laman.biz.role.domain.service.SysRoleFuncAuthConfigDomainService;
import com.laman.fusion.base.base.BaseAppService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
* @Title: SysRoleConfigDomainService
* @Description:  角色功能权限配置应用服务
* @Author: Away
* @Date: 2018/6/1 17:48
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
@Transactional
public class SysRoleFuncAuthConfigAppService extends BaseAppService<SysRoleFuncAuthConfigDomainService>{

    /**
     * @Method:  findFuncByRoles
     * @Author: Away
     * @Version: v1.0
     * @See: 根据角色编号查找功能权限
     * @Param: roles
     * @Return: java.util.List<com.laman.biz.role.app.dto.SysRoleFuncAuthConfigDto>
     * @Date: 2018/6/3 17:28
     */
    public List<SysRoleFuncAuthConfigDto> findFuncByRoles(String roles){
        return this.BDS.findByRoleCodes(roles);
    }
}
