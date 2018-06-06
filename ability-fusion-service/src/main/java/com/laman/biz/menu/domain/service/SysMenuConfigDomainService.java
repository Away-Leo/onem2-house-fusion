package com.laman.biz.menu.domain.service;


import com.laman.biz.menu.app.dto.SysMenuConfigDto;
import com.laman.biz.menu.domain.entity.SysMenuConfig;
import com.laman.biz.menu.domain.repositry.SysMenuConfigRepository;
import com.laman.biz.role.domain.service.SysRoleMenuAuthConfigDomainService;
import com.laman.fusion.base.base.BaseDomainService;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Away
 * @version V1.0
 * @Title: SysUrlsDomainService.java
 * @Description: 系统菜单配置服务
 * @date 2018/2/7 18:26
 * @copyright 重庆拉曼科技有限公司
 */
@Service
public class SysMenuConfigDomainService extends BaseDomainService<SysMenuConfigRepository,SysMenuConfig,SysMenuConfigDto>{

    private final SysMenuConfigRepository sysMenuConfigRepository;

    private final SysRoleMenuAuthConfigDomainService sysRoleMenuAuthConfigDomainService;


    @Autowired
    public SysMenuConfigDomainService(SysMenuConfigRepository sysMenuConfigRepository,SysRoleMenuAuthConfigDomainService sysRoleMenuAuthConfigDomainService) {
        this.sysMenuConfigRepository = sysMenuConfigRepository;
        this.sysRoleMenuAuthConfigDomainService=sysRoleMenuAuthConfigDomainService;
    }


    public List<SysMenuConfigDto> getAdminTree() throws Exception {
        List<SysMenuConfig> allData = this.sysMenuConfigRepository.findAll();
        if (ObjectHelper.isNotEmpty(allData) && allData.size() > 0) {
            return createTreeMenus(toDtoList(allData,SysMenuConfigDto.class));
        } else {
            return null;
        }
    }

    /**
     * @Method:  getMenuTreeWithouCommon
     * @Author: Away
     * @Version: v1.0
     * @See: 获取所有非公共的菜单
     * @Param:
     * @Return: java.util.List<com.laman.biz.menu.app.dto.SysMenuConfigDto>
     * @Date: 2018/6/1 16:46
     */
    public List<SysMenuConfigDto> getMenuTreeWithouCommon() throws Exception{
        return createTreeMenus(toDtoList(this.sysMenuConfigRepository.findAllByCommonFalse(),SysMenuConfigDto.class));
    }

    public static List<SysMenuConfigDto> createTreeMenus(List<SysMenuConfigDto> menus) {
        List<SysMenuConfigDto> treeMenus = null;
        if (null != menus && !menus.isEmpty()) {
            // 创建根节点
            SysMenuConfigDto root = new SysMenuConfigDto();
            root.setMenuName("菜单根目录");

            // 组装Map数据
            Map<String, SysMenuConfigDto> dataMap = new HashMap<>();
            for (SysMenuConfigDto menu : menus) {
                dataMap.put(menu.getMenuCode(), menu);
            }

            // 组装树形结构
            Set<Map.Entry<String, SysMenuConfigDto>> entrySet = dataMap.entrySet();
            for (Map.Entry<String, SysMenuConfigDto> entry : entrySet) {
                SysMenuConfigDto menu = entry.getValue();
                if (ObjectHelper.isEmpty(menu.getMenuParentCode())) {
                    root.getNodes().add(menu);
                } else {
                    if(ObjectHelper.isNotEmpty(dataMap.get(menu.getMenuParentCode())))
                    dataMap.get(menu.getMenuParentCode()).getNodes().add(menu);
                }
            }

            // 对树形结构进行二叉树排序
            root.sortChildren();
            treeMenus = root.getNodes();
        }
        return treeMenus;
    }

    /**
     * @Author: Away
     * @Title: findByConditions
     * @Description: 根据条件查询分页数据
     * @Param: pageable
     * @Param: condition
     * @Return: org.springframework.data.domain.Page<com.laman.biz.menu.app.dto.SysMenuConfigDto>
     * @Date: 2018/4/24 16:40
     * @Version: 2018/4/24 16:40
     */
    public Page<SysMenuConfigDto> findByConditions(Pageable pageable, SysMenuConfigDto condition) throws Exception {
        Page<SysMenuConfig> sourceData = this.sysMenuConfigRepository.findByConditions(pageable, condition);
        return toDtoPage(sourceData,SysMenuConfigDto.class,pageable);
    }

    /**
     * @Method:  findListByConditions
     * @Author: Away
     * @Version: v1.0
     * @See: 根据条件查找集合
     * @Param: condition
     * @Return: java.util.List<com.laman.biz.menu.app.dto.SysMenuConfigDto>
     * @Date: 2018/6/4 10:22
     */
    public List<SysMenuConfigDto> findListByConditions(SysMenuConfigDto condition) throws Exception {
        return toDtoList(this.sysMenuConfigRepository.findListByConditions(condition),SysMenuConfigDto.class);
    }

    /**
     * @Author: Away
     * @Description: 删除
     * @Param: id
     * @Return void
     * @Date 2018/2/23 14:12
     * @Copyright 重庆拉曼科技有限公司
     */
    public void deleteSysMenuConf(Long id) throws Exception {
        if (ObjectHelper.isNotEmpty(id)) {
            SysMenuConfig sourceData = this.sysMenuConfigRepository.getOne(id);
            if (ObjectHelper.isNotEmpty(sourceData)) {
                this.sysMenuConfigRepository.delete(sourceData);
            } else {
                throw new BusinessException("PX004", "根据ID删除对象出错，无此数据");
            }
        } else {
            throw new BusinessException("PX001", "根据ID删除对象出错，参数为空");
        }
    }

    /**
     * @Method:  deleteSysMenuConfByCode
     * @Author: Away
     * @Version: v1.0
     * @See: 根据菜单编号删除
     * @Param: code
     * @Return: void
     * @Date: 2018/6/5 15:50
     */
    public void deleteSysMenuConfByCode(String code) throws BusinessException {
        if (ObjectHelper.isNotEmpty(code)) {
            this.CT.deleteByMenuCode(code);
        } else {
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @Method:  deleteByMenuCodeAndRoleCode
     * @Author: Away
     * @Version: v1.0
     * @See: 根据菜单编号和角色编号删除
     * @Param: menuCode
     * @Param: roleCode
     * @Return: void
     * @Date: 2018/6/5 16:06
     */
    public void deleteByMenuCode(String menuCodes) throws BusinessException{
        if(ObjectHelper.isNotEmpty(menuCodes)){
            this.sysRoleMenuAuthConfigDomainService.deleteByMenuCode(menuCodes);
        }
    }

}
