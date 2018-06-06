package com.laman.biz.role.domain.entity;

import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


/**
* @Title: SysRoleConfig
* @Description:  角色菜单权限配置表
* @Author: Away
* @Date: 2018/6/1 15:45
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Entity
@Table(name="fusion_sys_role_menu_auth_conf",uniqueConstraints = {@UniqueConstraint(columnNames={"role_code", "menu_code"})})//联合唯一
@org.hibernate.annotations.Table(appliesTo = "fusion_sys_role_menu_auth_conf",comment = "角色菜单配置表")
@Getter
@Setter
public class SysRoleMenuAuthConfig extends BaseEntity{

    @Column(name="role_code",columnDefinition="varchar(30) comment '角色编号'")
    private String roleCode;

    @Column(name="menu_name",columnDefinition="varchar(100)  comment '菜单名称'")
    private String menuName;

    @Column(name="menu_code",columnDefinition="varchar(100) comment '菜单编码'")
    private String menuCode;

    @Column(name="menu_parent_code",columnDefinition="varchar(100) comment '上级菜单编号'")
    private String menuParentCode;

    @Column(name="menu_parent_name",columnDefinition="varchar(100) comment '上级菜单名称'")
    private String menuParentName;

    @Column(name="menu_url",columnDefinition="varchar(100)  comment '菜单链接地址'")
    private String menuUrl;

    @Column(name="menu_ordby",columnDefinition="int(11) comment '菜单顺序'")
    private Integer ordby;

    @Column(name="menu_state",columnDefinition="int(1) comment '菜单状态'")
    private Integer state;

}
