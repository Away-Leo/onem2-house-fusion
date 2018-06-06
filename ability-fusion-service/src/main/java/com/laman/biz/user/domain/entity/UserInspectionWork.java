package com.laman.biz.user.domain.entity;

import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
* @Title: UserInspectionWork
* @Description:  重要工作或检查工作
* @Author: Away
* @Date: 2018/5/30 19:33
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Entity
@Table(name = "fusion_user_inspectionwork")
@org.hibernate.annotations.Table(appliesTo = "fusion_user_inspectionwork",comment = "重要工作或检查工作")
@Getter
@Setter
public class UserInspectionWork extends BaseEntity {

    @Column(name = "user_id", columnDefinition = "int(11) not null comment '用户ID'")
    private Long userId;

    @Column(name = "start_time", columnDefinition = "datetime comment '起始时间'")
    private Date startTime;

    @Column(name = "end_time", columnDefinition = "datetime comment '终止时间'")
    private Date endTime;

    @Column(name = "work_name", columnDefinition = "varchar(100)  comment '工作名称'")
    private String workName;

    @Column(name = "org", columnDefinition = "varchar(100)  comment '组织单位'")
    private String org;

    @Column(name = "participation_identity", columnDefinition = "varchar(100)  comment '参与身份'")
    private String participationIdentity;

}
