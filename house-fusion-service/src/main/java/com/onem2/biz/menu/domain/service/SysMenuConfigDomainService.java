package com.onem2.biz.menu.domain.service;


import com.onem2.biz.menu.app.dto.SysMenuConfigDto;
import com.onem2.biz.menu.domain.entity.SysMenuConfig;
import com.onem2.biz.menu.domain.repositry.SysMenuConfigRepository;
import com.onem2.fusion.base.CPContext;
import com.onem2.fusion.base.base.BaseDomainService;
import com.onem2.fusion.base.util.ObjectHelper;
import com.onem2.fusion.base.util.ObjectProperUtil;
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
 * @copyright 重庆壹平方米网络科技有限公司
 */
@Service
public class SysMenuConfigDomainService extends BaseDomainService<SysMenuConfigRepository,SysMenuConfig,SysMenuConfigDto>{

    @Autowired
    private SysMenuConfigRepository sysMenuConfigRepository;


    /**
     * @Author: Away
     * @Description: 更新或保存系统菜单配置
     * @Param: sourceData
     * @Return com.onem2.biz.menu.app.dto.SysMenuConfigDto
     * @Date 2018/2/8 11:30
     * @Copyright 重庆壹平方米网络科技有限公司
     */
    public SysMenuConfigDto saveOrUpdate(SysMenuConfigDto sourceData) throws Exception {
        if (ObjectHelper.isNotEmpty(sourceData)) {
            //更新
            if (ObjectHelper.isNotEmpty(sourceData.getId())) {
                SysMenuConfigDto oldData = findById(sourceData.getId(),SysMenuConfigDto.class);
                oldData = ObjectProperUtil.compareAndValue(sourceData, oldData, false, new String[]{"menuUrl"});
                oldData.setRawUpdateTime(new Date());
                oldData.setRawModifier(CPContext.getContext().getSeUserInfo().getUserName());
                return this.sysMenuConfigRepository.updateEntity(oldData.toEntity(SysMenuConfig.class)).to(SysMenuConfigDto.class);
            } else {
                return this.sysMenuConfigRepository.saveEntity(sourceData.toEntity(SysMenuConfig.class)).to(SysMenuConfigDto.class);
            }
        } else {
            throw new BusinessException("PX001", "修改或保存系统菜单配置出错，参数为空");
        }
    }

    public List<SysMenuConfigDto> getAdminTree() throws Exception {
        List<SysMenuConfig> allData = this.sysMenuConfigRepository.findAll();
        if (ObjectHelper.isNotEmpty(allData) && allData.size() > 0) {
            List<SysMenuConfigDto> dtos = new ArrayList<>();
            for (SysMenuConfig temp : allData) {
                SysMenuConfigDto dto = temp.to(SysMenuConfigDto.class);
                dto.setMenuId(temp.getId());
                dto.setText(dto.getMenuName());
                dto.setTags(dto.getId()+"");
                dtos.add(dto);
            }
            List<SysMenuConfigDto> returnData=createTreeMenus(dtos);
            return returnData;
        } else {
            return null;
        }
    }

    public static List<SysMenuConfigDto> createTreeMenus(List<SysMenuConfigDto> menus) {
        List<SysMenuConfigDto> treeMenus = null;
        if (null != menus && !menus.isEmpty()) {
            // 创建根节点
            SysMenuConfigDto root = new SysMenuConfigDto();
            root.setMenuName("菜单根目录");

            // 组装Map数据
            Map<Long, SysMenuConfigDto> dataMap = new HashMap<>();
            for (SysMenuConfigDto menu : menus) {
                dataMap.put(menu.getMenuId(), menu);
            }

            // 组装树形结构
            Set<Map.Entry<Long, SysMenuConfigDto>> entrySet = dataMap.entrySet();
            for (Map.Entry<Long, SysMenuConfigDto> entry : entrySet) {
                SysMenuConfigDto menu = entry.getValue();
                if (null == menu.getMenuPid() || 0 == menu.getMenuPid()) {
                    root.getNodes().add(menu);
                } else {
                    dataMap.get(menu.getMenuPid()).getNodes().add(menu);
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
     * @Return: org.springframework.data.domain.Page<com.onem2.biz.menu.app.dto.SysMenuConfigDto>
     * @Date: 2018/4/24 16:40
     * @Version: 2018/4/24 16:40
     */
    public Page<SysMenuConfigDto> findByConditions(Pageable pageable, SysMenuConfigDto condition) throws Exception {
        Page<SysMenuConfig> sourceData = this.sysMenuConfigRepository.findByConditions(pageable, condition);
        return toDtoPage(sourceData,SysMenuConfigDto.class);
    }

    /**
     * @Author: Away
     * @Description: 删除
     * @Param: id
     * @Return void
     * @Date 2018/2/23 14:12
     * @Copyright 重庆壹平方米网络科技有限公司
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

}
