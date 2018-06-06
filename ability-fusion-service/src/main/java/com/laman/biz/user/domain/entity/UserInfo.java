package com.laman.biz.user.domain.entity;

import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
* @Title: UserInfo
* @Description:  用户基本信息表
* @Author: Away
* @Date: 2018/5/30 19:25
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Entity
@Table(name = "fusion_user_info")
@org.hibernate.annotations.Table(appliesTo = "fusion_user_info",comment = "用户基本信息表")
@Getter
@Setter
public class UserInfo extends BaseEntity {

    @Column(name = "user_id", columnDefinition = "int(11) not null comment '用户ID'")
    private Long userId;

    @Column(name = "name", columnDefinition = "varchar(100)  comment '姓名'")
    private String name;

    @Column(name = "roles", columnDefinition = "varchar(100)  comment '角色 比如  user,manager,admin  多个角色用逗号隔开'")
    private String roles;

    @Column(name = "head_img", columnDefinition = "varchar(150) comment '用户头像'")
    private String headImg;

    @Column(name = "sex", columnDefinition = "int(1) comment '性别 1、男  2、女  3、其他'")
    private Integer sex;

    @Column(name = "birthday", columnDefinition = "datetime comment '出生年月'")
    private Date birthday;

    @Column(name = "identify_code",columnDefinition = " int(2) comment '鉴别编号 1待选,2注销，3专家' ")
    private Integer identifyCode;

    @Column(name = "nation_name", columnDefinition = "varchar(50)  comment '民族名称'")
    private String nationName;

    @Column(name = "nation_code", columnDefinition = "varchar(20)  comment '民族编号'")
    private String nationCode;

    @Column(name = "education_code", columnDefinition = "varchar(20)  comment '学历编号'")
    private String educationCode;

    @Column(name = "education_name", columnDefinition = "varchar(20)  comment '学历名称'")
    private String educationName;

    @Column(name = "political_outlook_code", columnDefinition = "varchar(20)  comment '政治面貌编号'")
    private String politicalOutlookCode;

    @Column(name = "political_outlook_name", columnDefinition = "varchar(20)  comment '政治面貌名称'")
    private String politicalOutlookName;

    @Column(name = "technical_title", columnDefinition = "varchar(20)  comment '技术职称'")
    private String technicalTitle;

    @Column(name = "native_place_code", columnDefinition = "varchar(20)  comment '籍贯编号'")
    private String nativePlaceCode;

    @Column(name = "native_place_name", columnDefinition = "varchar(20)  comment '籍贯名称'")
    private String nativePlaceName;

    @Column(name = "administrative_post_code", columnDefinition = "varchar(20)  comment '行政职级编号'")
    private String administrativePostCode;

    @Column(name = "administrative_post_name", columnDefinition = "varchar(20)  comment '行政职级名称'")
    private String administrativePostName;

    @Column(name = "email", columnDefinition = "varchar(100)  comment '电子邮箱'")
    private String email;

    @Column(name = "work_unit", columnDefinition = "varchar(100)  comment '工作单位'")
    private String workUnit;

    @Column(name = "work_phone", columnDefinition = "varchar(100)  comment '工作电话'")
    private String workPhone;

    @Column(name = "post_code", columnDefinition = "varchar(100)  comment '邮政编码'")
    private String postCode;

    @Column(name="work_time",columnDefinition="datetime comment '工作时间'")
    private Date workTime;

    @Column(name = "id_card", columnDefinition = "varchar(20)  comment '证件号码'")
    private String idCard;

    @Column(name = "phone", columnDefinition = "varchar(32)  comment '手机号码'")
    private String phone;

    @Column(name = "email_address", columnDefinition = "varchar(100)  comment '用户邮箱'")
    private String emailAddress;

    @Column(name = "personnel_name", columnDefinition = "varchar(100)  comment '人才称号'")
    private String personnelName;

    @Column(name = "recommendation_area", columnDefinition = "varchar(100)  comment '自荐领域'")
    private String recommendationArea;

    @Column(name = "register_date", columnDefinition = "datetime comment '注册时间'")
    private Date registerDate;

}
