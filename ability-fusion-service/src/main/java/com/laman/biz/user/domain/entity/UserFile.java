package com.laman.biz.user.domain.entity;

import com.laman.fusion.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
* @Title: UserFile
* @Description:  附件
* @Author: Away
* @Date: 2018/5/30 19:33
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Entity
@Table(name = "fusion_user_file")
@org.hibernate.annotations.Table(appliesTo = "fusion_user_file",comment = "附件")
@Getter
@Setter
public class UserFile extends BaseEntity {

    @Column(name = "user_id", columnDefinition = "int(11) not null comment '用户ID'")
    private Long userId;

    @Column(name = "upload_time", columnDefinition = "datetime comment '上传时间'")
    private Date uploadTime;

    @Column(name = "file_name", columnDefinition = "varchar(100)  comment '附件名称'")
    private String fileName;

    @Column(name = "file_path", columnDefinition = "varchar(100)  comment '附件路径'")
    private String filePath;

    @Column(name = "file_memo", columnDefinition = "varchar(100)  comment '附件描述'")
    private String fileMemo;

}
