package com.laman.biz.user.domain.entity;

import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
* @Title: UserThesis
* @Description:  论文
* @Author: Away
* @Date: 2018/5/30 19:33
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Entity
@Table(name = "fusion_user_thesis")
@org.hibernate.annotations.Table(appliesTo = "fusion_user_thesis",comment = "论文")
@Getter
@Setter
public class UserThesis extends BaseEntity {

    @Column(name = "user_id", columnDefinition = "int(11) not null comment '用户ID'")
    private Long userId;

    @Column(name = "publish_time", columnDefinition = "datetime comment '发表时间'")
    private Date publishTime;

    @Column(name = "thesis_name", columnDefinition = "varchar(500)  comment '论文名称'")
    private String thesisName;

    @Column(name = "book_concern_name", columnDefinition = "varchar(500)  comment '出版社名称'")
    private String bookConcernName;

    @Column(name = "personal_sort", columnDefinition = "int(10)  comment '个人排名'")
    private Integer personalSort;

}
