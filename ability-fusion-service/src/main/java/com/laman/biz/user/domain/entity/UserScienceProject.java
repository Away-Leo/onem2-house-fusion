package com.laman.biz.user.domain.entity;

import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
* @Title: UserScienceProject
* @Description:  科研项目
* @Author: Away
* @Date: 2018/5/30 19:33
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Entity
@Table(name = "fusion_user_science_project")
@org.hibernate.annotations.Table(appliesTo = "fusion_user_science_project",comment = "科研项目")
@Getter
@Setter
public class UserScienceProject extends BaseEntity {

    @Column(name = "user_id", columnDefinition = "int(11) not null comment '用户ID'")
    private Long userId;

    @Column(name = "start_time", columnDefinition = "datetime comment '起始时间'")
    private Date startTime;

    @Column(name = "end_time", columnDefinition = "datetime comment '终止时间'")
    private Date endTime;

    @Column(name = "project_name", columnDefinition = "varchar(100)  comment '项目名称'")
    private String projectName;

    @Column(name = "project_org", columnDefinition = "varchar(100)  comment '项目组织单位'")
    private String projectOrg;

    @Column(name = "personal_contribution", columnDefinition = "varchar(100)  comment '个人贡献'")
    private String personalContribution;

}
