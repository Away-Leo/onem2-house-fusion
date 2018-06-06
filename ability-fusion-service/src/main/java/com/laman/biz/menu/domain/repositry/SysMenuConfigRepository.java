package com.laman.biz.menu.domain.repositry;


import com.laman.biz.menu.app.dto.SysMenuConfigDto;
import com.laman.biz.menu.domain.entity.SysMenuConfig;
import com.laman.fusion.base.base.BaseRepository;
import com.laman.fusion.base.util.ObjectHelper;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Title: SysUrlsRepository.java
* @Description: 系统菜单配置操作库
* @author Away
* @date 2018/2/7 18:25
* @copyright 重庆拉曼科技有限公司
* @version V1.0
*/
public interface SysMenuConfigRepository extends BaseRepository<SysMenuConfig,Long> {

    /**
     * @Author: Away
     * @Description: 按照条件查找
     * @Param: pageable
     * @Param: condition
     * @Return: org.springframework.data.domain.Page<com.laman.biz.menu.domain.entity.SysMenuConfig>
     * @Date: 2018/2/23 11:34
     * @Copyright: 重庆拉曼科技有限公司
     */
     default Page<SysMenuConfig> findByConditions(Pageable pageable,SysMenuConfigDto condition){
        Map<String,Object> param=new HashMap<>();
        StringBuffer hql=new StringBuffer(" from SysMenuConfig s where 1=1 ");
        //菜单名称
        if(ObjectHelper.isNotEmpty(condition.getMenuName())){
            hql.append(" and s.menuName like :menuName ");
            param.put("menuName","%"+ StringEscapeUtils.escapeSql(condition.getMenuName())+"%");
        }
        //菜单链接
        if(ObjectHelper.isNotEmpty(condition.getMenuUrl())){
            hql.append(" and s.menuUrl like :menuUrl ");
            param.put("menuUrl","%"+StringEscapeUtils.escapeSql(condition.getMenuUrl())+"%");
        }
        //是否为公共菜单
        if(ObjectHelper.isNotEmpty(condition.isCommon())){
            hql.append(" and s.common = :common ");
            param.put("common",condition.isCommon());
        }
        hql.append(" order by s.rawAddTime desc ");
        return this.findByHqlPage(pageable,hql.toString(),param);
    }

    /**
     * @Method:  findListByConditions
     * @Author: Away
     * @Version: v1.0
     * @See: 根据条件查找集合
     * @Param: condition
     * @Return: java.util.List<com.laman.biz.menu.domain.entity.SysMenuConfig>
     * @Date: 2018/6/4 10:25
     */
    default List<SysMenuConfig> findListByConditions(SysMenuConfigDto condition){
        Map<String,Object> param=new HashMap<>();
        StringBuffer hql=new StringBuffer(" from SysMenuConfig s where 1=1 ");
        //菜单名称
        if(ObjectHelper.isNotEmpty(condition.getMenuName())){
            hql.append(" and s.menuName like :menuName ");
            param.put("menuName","%"+ StringEscapeUtils.escapeSql(condition.getMenuName())+"%");
        }
        //菜单链接
        if(ObjectHelper.isNotEmpty(condition.getMenuUrl())){
            hql.append(" and s.menuUrl like :menuUrl ");
            param.put("menuUrl","%"+StringEscapeUtils.escapeSql(condition.getMenuUrl())+"%");
        }
        //是否为公共菜单
        if(ObjectHelper.isNotEmpty(condition.isCommon())){
            hql.append(" and s.common = :common ");
            param.put("common",condition.isCommon());
        }
        hql.append(" order by s.rawAddTime desc ");
        return this.findByHql(hql.toString(),param);
    }

    /**
     * @Method:  findAllByCommonFalse
     * @Author: Away
     * @Version: v1.0
     * @See: 查找所有非公共的菜单
     * @Param:
     * @Return: java.util.List<com.laman.biz.menu.domain.entity.SysMenuConfig>
     * @Date: 2018/6/1 16:43
     */
    List<SysMenuConfig> findAllByCommonFalse();

    /**
     * @Method:  deleteByMenuCode
     * @Author: Away
     * @Version: v1.0
     * @See: 根据菜单编号删除
     * @Param: menuCode
     * @Return: void
     * @Date: 2018/6/5 15:49
     */
    void deleteByMenuCode(String menuCode);
}
