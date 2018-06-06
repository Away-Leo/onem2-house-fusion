package com.laman.biz.user.domain.entity;

import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
* @Title: UserRewardSituation
* @Description:  奖励情况
* @Author: Away
* @Date: 2018/5/30 19:33
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Entity
@Table(name = "fusion_user_reward_situation")
@org.hibernate.annotations.Table(appliesTo = "fusion_user_reward_situation",comment = "奖励情况")
@Getter
@Setter
public class UserRewardSituation extends BaseEntity {

    @Column(name = "user_id", columnDefinition = "int(11) not null comment '用户ID'")
    private Long userId;

    @Column(name = "reward_time", columnDefinition = "datetime comment '奖励时间'")
    private Date rewardTime;

    @Column(name = "reward_name", columnDefinition = "varchar(100)  comment '奖励名称'")
    private String rewardName;

    @Column(name = "org", columnDefinition = "varchar(100)  comment '组织单位'")
    private String org;

    @Column(name = "participation_identity", columnDefinition = "varchar(100)  comment '参与身份'")
    private String participationIdentity;

}
