package com.laman.biz.lottery.domain.entity;

import com.laman.fusion.base.entity.AggEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
* @Title: ScanerInfo
* @Description:  扫描者信息
* @Author: Away
* @Date: 2018/5/23 16:10
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Entity
@Table(name="fusion_scanner_info")
@org.hibernate.annotations.Table(appliesTo = "fusion_scanner_info",comment = "扫描者信息")
@Getter
@Setter
public class ScanerInfo extends AggEntity{

    @Column(name="scanner_name",columnDefinition="varchar(100) comment '名称'")
    private String name;

    @Column(name="company_name",columnDefinition="varchar(100)  comment '公司名称'")
    private String companyName;

    @Column(name="phone",columnDefinition="varchar(100)  comment '电话'")
    private String phone;

}
