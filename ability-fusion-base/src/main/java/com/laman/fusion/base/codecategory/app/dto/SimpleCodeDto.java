package com.laman.fusion.base.codecategory.app.dto;

import com.laman.fusion.base.dtos.BaseDto;
import lombok.Getter;
import lombok.Setter;


/**
 * @Title: SimpleCode.java
 * @Description: 系统基本组件-下拉框数据字典
 * @Author: Away
 * @Date: 2018/4/23 17:44
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@Getter
@Setter
public class SimpleCodeDto extends BaseDto{

    /**是否为树形结构 0是,1不是**/
    private boolean isTree = false;

    /**选项编号**/
    private String code;

    /**选项名称**/
    private String name;

    /**选项全编号**/
    private String fullCode;

    /**选项描述**/
    private String description;

    /**显示顺序**/
    private Integer dispOrder;

    /**是否为默认0是,1否**/
    private boolean isDefault = false;

    /**是否为固定的 0是,1否'**/
    private boolean isFixed = false;

    /**所属大类编号**/
    protected String categoryCd;





}
