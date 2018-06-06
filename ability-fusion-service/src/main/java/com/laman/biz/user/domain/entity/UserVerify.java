package com.laman.biz.user.domain.entity;

import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
* @Title: UserVerify
* @Description:  用户审核表
* @Author: Away
* @Date: 2018/6/5 14:47
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Table(name = "fusion_user_verify")
@org.hibernate.annotations.Table(appliesTo = "fusion_user_verify",comment = "用户审核")
@Entity
@Getter
@Setter
public class UserVerify extends BaseEntity {

    @Column(name = "user_id", columnDefinition = "int(11) not null comment '用户ID'")
    private Long userId;

    @Column(name = "content", columnDefinition = "varchar(500) comment '审核意见'")
    private String content;

    @Column(name = "result", columnDefinition = "int(2) comment '审核结果'")
    private Integer result;

}
