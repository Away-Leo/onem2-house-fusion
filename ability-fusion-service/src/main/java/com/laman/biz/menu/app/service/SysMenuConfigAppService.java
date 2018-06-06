package com.laman.biz.menu.app.service;


import com.laman.biz.menu.app.dto.SysMenuConfigDto;
import com.laman.biz.menu.domain.entity.SysMenuConfig;
import com.laman.biz.menu.domain.service.SysMenuConfigDomainService;
import com.laman.fusion.base.base.BaseAppService;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
* @Title: SysUrlsDomainService.java
* @Description:  系统菜单配置服务
* @author Away
* @Date: 2018/2/7 18:26
* @Copyright: 重庆拉曼科技有限公司
* @version V1.0
*/
@Service
@Transactional
public class SysMenuConfigAppService extends BaseAppService<SysMenuConfigDomainService>{

    /**
     * @Author: Away
     * @Description: 更新或保存系统菜单配置
     * @Param: sourceData
     * @Return: com.laman.biz.menu.app.dto.SysMenuConfigDto
     * @Date: 2018/2/8 11:30
     * @Copyright: 重庆拉曼科技有限公司
     */
    public SysMenuConfigDto saveOrUpdate(SysMenuConfigDto sourceData) throws Exception{
        return this.BDS.saveOrUpdateData(sourceData, SysMenuConfig.class);
    }

    /**
     * @Author: Away
     * @Description: 获得超级管理员的树形菜单
     * @Param:
     * @Return: java.util.List<com.laman.biz.menu.app.dto.SysMenuConfigDto>
     * @Date: 2018/2/8 16:44
     * @Copyright: 重庆拉曼科技有限公司
     */
    public List<SysMenuConfigDto> getAdminTree() throws Exception{
        return this.BDS.getAdminTree();
    }

    /**
     * @Method:  getAllMenuTreeWithoutCommon
     * @Author: Away
     * @Version: v1.0
     * @See: 获取所有非公共的菜单树
     * @Param:
     * @Return: java.util.List<com.laman.biz.menu.app.dto.SysMenuConfigDto>
     * @Date: 2018/6/1 16:49
     */
    public List<SysMenuConfigDto> getAllMenuTreeWithoutCommon() throws Exception{
        return this.BDS.getMenuTreeWithouCommon();
    }

    /**
     * @Author: Away
     * @Description: 根据参数查找分页数据
     * @Param: pageable
     * @Param: condition
     * @Return: org.springframework.data.domain.Page<com.laman.biz.menu.app.dto.SysMenuConfigDto>
     * @Date: 2018/2/23 14:02
     * @Copyright: 重庆拉曼科技有限公司
     */
    public Page<SysMenuConfigDto> findByConditions(Pageable pageable,SysMenuConfigDto condition) throws Exception{
        return this.BDS.findByConditions(pageable, condition);
    }

    /**
     * @Method:  findListByConditions
     * @Author: Away
     * @Version: v1.0
     * @See: 根据条件查找
     * @Param: pageable
     * @Param: condition
     * @Return: java.util.List<com.laman.biz.menu.app.dto.SysMenuConfigDto>
     * @Date: 2018/6/4 10:27
     */
    public List<SysMenuConfigDto> findListByConditions(SysMenuConfigDto condition) throws Exception{
        return this.BDS.findListByConditions(condition);
    }



    /**
     * @Author: Away
     * @Description: 根据ID删除
     * @Param: id
     * @Return: void
     * @Date: 2018/2/23 14:17
     * @Copyright: 重庆拉曼科技有限公司
     */
    public void deleteMenu(Long id) throws Exception{
        this.BDS.deleteSysMenuConf(id);
    }

    /**
     * @Method:  deleteMenuAndChildAndMenuAuth
     * @Author: Away
     * @Version: v1.0
     * @See: 删除菜单（对应的子菜单和角色菜单配置也一并删除）
     * @Param: userId
     * @Param: menuCodes
     * @Return: void
     * @Date: 2018/6/5 15:36
     */
    public void deleteMenuAndChildAndMenuAuth(String menuCodes) throws BusinessException{
        if(ObjectHelper.isNotEmpty(menuCodes)){
            //分解待删除菜单编号
            String[] menuCodeArray=menuCodes.split(",");
            for(String temp:menuCodeArray){
                //删除系统菜单配置
                BDS.deleteSysMenuConfByCode(temp);
//                //删除角色菜单配置
                BDS.deleteByMenuCode(temp);
            }
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

}
