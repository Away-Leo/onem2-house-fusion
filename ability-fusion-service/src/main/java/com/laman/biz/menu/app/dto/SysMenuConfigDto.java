package com.laman.biz.menu.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.*;


/**
 * @author Away
 * @version V1.0
 * @Title: SysMenuConfig.java
 * @Description: 系统菜单配置
 * @Date:  2018/2/8 10:29
 * @Copyright: 重庆拉曼科技有限公司
 */
@Getter
@Setter
public class SysMenuConfigDto extends BaseDto {

    /**用户ID**/
    private Long userId;

    /**菜单ID**/
    private Long menuId;

    /**上级菜单编号**/
    private String menuParentCode;

    /**上级菜单名称**/
    private String menuParentName;

    /**
     * 菜单名称
     **/
    private String menuName;

    /**
     * 菜单唯一编号
     */
    private String menuCode;

    /**
     * 菜单链接地址
     **/
    private String menuUrl;

    /**
     * 菜单顺序
     **/
    private Integer ordby;

    /**
     * 菜单状态
     **/
    private Map state;

    /**是否为公共菜单 0否,1是**/
    private boolean common;


    private String text;

    private String tags;

    List<SysMenuConfigDto> nodes = new ArrayList<>();

    /**
     * @Author: Away
     * @Title: sortChildren
     * @Description: 子节点排序
     * @Return: void
     * @Date: 2018/4/24 16:35
     * @Version: 2018/4/24 16:35
     */
    public void sortChildren() {
        Collections.sort(nodes, (menu1, menu2) -> {
            int result = 0;

            Integer ordby1 = menu1.getOrdby();
            Integer ordby2 = menu2.getOrdby();

            Long id1 = menu1.getId();
            Long id2 = menu2.getId();
            if (null != ordby1 && null != ordby2) {
                result = (ordby1 < ordby2 ? -1 : (ordby1 == ordby2 ? 0 : 1));
            } else {
                result = (id1 < id2 ? -1 : (id1 == id2 ? 0 : 1));
            }
            return result;
        });
        // 对每个节点的下一层节点进行排序
        for (SysMenuConfigDto node : nodes) {
            node.sortChildren();
        }
    }

}
