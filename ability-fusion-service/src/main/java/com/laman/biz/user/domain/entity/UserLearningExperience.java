package com.laman.biz.user.domain.entity;

import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
* @Title: UserLearningExperience
* @Description:  学习经历
* @Author: Away
* @Date: 2018/5/30 19:33
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Entity
@Table(name = "fusion_user_learning")
@org.hibernate.annotations.Table(appliesTo = "fusion_user_learning",comment = "用户学习经历")
@Getter
@Setter
public class UserLearningExperience extends BaseEntity {

    @Column(name = "user_id", columnDefinition = "int(11) not null comment '用户ID'")
    private Long userId;

    @Column(name = "start_time", columnDefinition = "datetime comment '开始时间'")
    private Date startTime;

    @Column(name = "end_time", columnDefinition = "datetime comment '结束时间'")
    private Date endTime;

    @Column(name = "school_name", columnDefinition = "varchar(50)  comment '学校名称'")
    private String schoolName;

    @Column(name = "major_name", columnDefinition = "varchar(50)  comment '专业名称'")
    private String majorName;

}
