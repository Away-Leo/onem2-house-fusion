package com.laman.biz.role.domain.service;

import com.laman.biz.role.app.dto.SysRoleConfigDto;
import com.laman.biz.role.domain.entity.SysRoleConfig;
import com.laman.biz.role.domain.repository.SysRoleConfigRepository;
import com.laman.fusion.base.base.BaseDomainService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Title: SysRoleConfigDomainService
* @Description:  角色配置数据服务
* @Author: Away
* @Date: 2018/6/1 17:48
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
public class SysRoleConfigDomainService extends BaseDomainService<SysRoleConfigRepository,SysRoleConfig,SysRoleConfigDto>{

    /**
     * @Method:  findPageByConditions
     * @Author: Away
     * @Version: v1.0
     * @See: 根据查询条件查找分页
     * @Param: pageRequest
     * @Param: conditions
     * @Return: org.springframework.data.domain.Page<com.laman.biz.role.domain.entity.SysRoleConfig>
     * @Date: 2018/6/4 17:43
     */
    public Page<SysRoleConfig> findPageByConditions(Pageable pageRequest, SysRoleConfigDto conditions){
        return toDtoPage(this.CT.findPageByConditions(pageRequest, conditions),SysRoleConfig.class,pageRequest);
    }

    /**
     * @Method:  findListByConditions
     * @Author: Away
     * @Version: v1.0
     * @See: 根据查询条件查询集合
     * @Param: conditions
     * @Return: java.util.List<com.laman.biz.role.domain.entity.SysRoleConfig>
     * @Date: 2018/6/4 17:45
     */
    public List<SysRoleConfigDto> findListByConditions(SysRoleConfigDto conditions){
        return this.toDtoList(this.CT.findListByConditions(conditions),SysRoleConfigDto.class);
    }
}
