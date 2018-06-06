package com.laman.biz.lottery.domain.entity;

import com.laman.fusion.base.entity.AggEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
* @Title: LotteryConfig
* @Description:  抽奖配置
* @Author: Away
* @Date: 2018/5/23 14:07
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Entity
@Table(name="fusion_lottery_conf")
@org.hibernate.annotations.Table(appliesTo = "fusion_lottery_conf",comment = "抽奖配置")
@Getter
@Setter
public class LotteryConfig extends AggEntity{

    @Column(name="menu_state",columnDefinition="int(1) comment '菜单状态'")
    private Integer state;

}
