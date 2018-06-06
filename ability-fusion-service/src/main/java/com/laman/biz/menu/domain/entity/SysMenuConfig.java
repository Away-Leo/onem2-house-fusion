package com.laman.biz.menu.domain.entity;

import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
* @Title: SysMenuConfig.java
* @Description:  系统菜单配置
* @author Away
* @date 2018/2/8 10:29
* @copyright 重庆拉曼科技有限公司
* @version V1.0
*/
@Entity
@Table(name="fusion_sys_menu_conf")
@org.hibernate.annotations.Table(appliesTo = "fusion_sys_menu_conf",comment = "整个系统的全部菜单配置")
@Getter
@Setter
public class SysMenuConfig extends BaseEntity{

    @Column(name="menu_parent_code",columnDefinition="varchar(100) comment '上级菜单编号'")
    private String menuParentCode;

    @Column(name="menu_parent_name",columnDefinition="varchar(100) comment '上级菜单名称'")
    private String menuParentName;

    @Column(name="menu_name",columnDefinition="varchar(100)  comment '菜单名称'")
    private String menuName;

    @Column(name="menu_code",unique = true,columnDefinition="varchar(100) comment '菜单编码'")
    private String menuCode;

    @Column(name="menu_url",columnDefinition="varchar(100)  comment '菜单链接地址'")
    private String menuUrl;

    @Column(name="menu_ordby",columnDefinition="int(11) comment '菜单顺序'")
    private Integer ordby;

    @Column(name="menu_state",columnDefinition="int(1) comment '菜单状态'")
    private Integer state;

    @Column(columnDefinition = " tinyint default 0  COMMENT '是否为公共菜单 0否,1是' ")
    private boolean common = false;

}
