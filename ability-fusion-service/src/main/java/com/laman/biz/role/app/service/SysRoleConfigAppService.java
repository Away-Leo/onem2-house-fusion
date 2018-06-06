package com.laman.biz.role.app.service;

import com.laman.biz.role.app.dto.SysRoleConfigDto;
import com.laman.biz.role.domain.entity.SysRoleConfig;
import com.laman.biz.role.domain.service.SysRoleConfigDomainService;
import com.laman.fusion.base.base.BaseAppService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
@Transactional
public class SysRoleConfigAppService extends BaseAppService<SysRoleConfigDomainService>{

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
    public Page<SysRoleConfig> findPageByConditions(PageRequest pageRequest, SysRoleConfigDto conditions){
        return this.BDS.findPageByConditions(pageRequest, conditions);
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
        return this.BDS.findListByConditions(conditions);
    }
}
