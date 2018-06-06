package com.laman.biz.user.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
* @Title: UserThesis
* @Description:  论文
* @Author: Away
* @Date: 2018/5/30 19:33
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Getter
@Setter
public class UserThesisDto extends BaseDto {

    /**用户ID**/
    private Long userId;

    /**发表时间**/
    private Date publishTime;

    /**论文名称**/
    private String thesisName;

    /**出版社名称**/
    private String bookConcernName;

    /**个人排名**/
    private Integer personalSort;

}
