package com.laman.biz.user.domain.entity;

import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
* @Title: UserScientificInvention
* @Description:  科学发明
* @Author: Away
* @Date: 2018/5/30 19:33
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Entity
@Table(name = "fusion_user_science_invention")
@org.hibernate.annotations.Table(appliesTo = "fusion_user_science_invention",comment = "科学发明")
@Getter
@Setter
public class UserScientificInvention extends BaseEntity {

    @Column(name = "user_id", columnDefinition = "int(11) not null comment '用户ID'")
    private Long userId;

    @Column(name = "patent_time", columnDefinition = "datetime comment '专利获得时间'")
    private Date patentTime;

    @Column(name = "patent_name", columnDefinition = "varchar(100)  comment '专利名称'")
    private String patentName;

    @Column(name = "certificate_code", columnDefinition = "varchar(100)  comment '证书编号'")
    private String certificateCode;

    @Column(name = "use_situation", columnDefinition = "varchar(100)  comment '应用情况'")
    private String useSituation;

}
