package com.laman.biz.role.domain.repository;

import com.laman.biz.role.domain.entity.SysRoleFuncAuthConfig;
import com.laman.fusion.base.base.BaseRepository;

import java.util.List;

/**
* @Title: SysRoleConfigRepository
* @Description:  角色功能配置操作库
* @Author: Away
* @Date: 2018/6/1 17:38
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public interface SysRoleFuncAuthConfigRepository extends BaseRepository<SysRoleFuncAuthConfig,Long>{

    /**
     * @Method:  findByRoleCode
     * @Author: Away
     * @Version: v1.0
     * @See: 根据角色编号查找相应的功能权限配置
     * @Param: roleCode
     * @Return: java.util.List<com.laman.biz.role.domain.entity.SysRoleFuncAuthConfig>
     * @Date: 2018/6/3 17:20
     */
    List<SysRoleFuncAuthConfig> findDistinctByRoleCodeIn(List<String> roleCodes);
}
