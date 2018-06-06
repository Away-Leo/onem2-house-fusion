package com.laman.biz.role.domain.repository;

import com.laman.biz.role.app.dto.SysRoleConfigDto;
import com.laman.biz.role.domain.entity.SysRoleConfig;
import com.laman.fusion.base.base.BaseRepository;
import com.laman.fusion.base.util.ObjectHelper;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Title: SysRoleConfigRepository
* @Description:  角色配置操作库
* @Author: Away
* @Date: 2018/6/1 17:38
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public interface SysRoleConfigRepository extends BaseRepository<SysRoleConfig,Long>{

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
    default Page<SysRoleConfig> findPageByConditions(Pageable pageable, SysRoleConfigDto conditions){
        StringBuilder hql=new StringBuilder(" from SysRoleConfig c where 1=1 ");
        Map<String,Object> params=new HashMap<>();
        if(ObjectHelper.isNotEmpty(conditions)){
            if(ObjectHelper.isNotEmpty(conditions.getRoleName())){
                hql.append(" and c.roleName like :roleName ");
                params.put("roleName","%"+ StringEscapeUtils.escapeSql(conditions.getRoleName())+"%");
            }
        }
        hql.append(" order by c.rawAddTime desc ");
        return this.findByHqlPage(pageable,hql.toString(),params);
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
    default List<SysRoleConfig> findListByConditions(SysRoleConfigDto conditions){
        StringBuilder hql=new StringBuilder(" from SysRoleConfig c where 1=1 ");
        Map<String,Object> params=new HashMap<>();
        if(ObjectHelper.isNotEmpty(conditions)){
            if(ObjectHelper.isNotEmpty(conditions.getRoleName())){
                hql.append(" and c.roleName like :roleName ");
                params.put("roleName","%"+ StringEscapeUtils.escapeSql(conditions.getRoleName())+"%");
            }
        }
        hql.append(" order by c.rawAddTime desc ");
        return this.findByHql(hql.toString(),params);
    }
}
