package com.laman.biz.lottery.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;


/**
* @Title: Questionnaire
* @Description:  问卷调查
* @Author: Away
* @Date: 2018/5/23 19:56
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Getter
@Setter
public class QuestionnaireDto extends BaseDto{

    @Column(name="question1",columnDefinition="varchar(100) comment '第一题'")
    private String question1;

    @Column(name="question2",columnDefinition="varchar(100) comment '第二题'")
    private String question2;

    @Column(name="question3",columnDefinition="varchar(255) comment '第三题'")
    private String question3;

    @Column(name="name",columnDefinition="varchar(255) comment '姓名'")
    private String name;

    @Column(name="phone",columnDefinition="varchar(255) comment '电话'")
    private String phone;

    @Column(name="unit_name",columnDefinition="varchar(255) comment '单位名称'")
    private String unitName;

    @Column(name="job",columnDefinition="varchar(255) comment '职位'")
    private String job;



}
