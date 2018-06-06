package com.laman.biz.user.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
* @Title: UserFile
* @Description:  附件
* @Author: Away
* @Date: 2018/5/30 19:33
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Getter
@Setter
public class UserFileDto extends BaseDto {

    /**用户ID**/
    private Long userId;

    /**上传时间**/
    private Date uploadTime;

    /**附件名称**/
    private String fileName;

    /**附件路径**/
    private String filePath;

    /**附件描述**/
    private String fileMemo;

}
