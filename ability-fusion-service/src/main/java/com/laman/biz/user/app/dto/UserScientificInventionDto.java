package com.laman.biz.user.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
* @Title: UserScientificInvention
* @Description:  科学发明
* @Author: Away
* @Date: 2018/5/30 19:33
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Getter
@Setter
public class UserScientificInventionDto extends BaseDto {

    /**用户ID**/
    private Long userId;

    /**专利获得时间**/
    private Date patentTime;

    /**专利名称**/
    private String patentName;

    /**证书编号**/
    private String certificateCode;

    /**应用情况**/
    private String useSituation;

}
