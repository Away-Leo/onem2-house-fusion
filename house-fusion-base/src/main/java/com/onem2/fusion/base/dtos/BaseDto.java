
package com.onem2.fusion.base.dtos;

import com.onem2.fusion.base.util.ObjectProperUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: BaseDto.java
 * @Description:  基本数据传输体
 * @Author: Away
 * @Date: 2018/4/12 14:32
 * @Copyright: 重庆壹平方米网络科技有限公司
 * @Version: V1.0
 */
public abstract class BaseDto implements Serializable {

    /**主键ID**/
    private Long id;

    /**创建时间**/
    protected Date rawAddTime = null;

    /**修改时间**/
    protected Date rawUpdateTime;

    /**创建人**/
    protected String rawCreator = "1";

    /**修改人**/
    protected String rawModifier = "1";

    /**
     * @Author: Away
     * @Description: 转换为相应的域对象
     * @Param: tClass
     * @Return T
     * @Date 2018/1/11 11:35
     * @Copyright 重庆壹平方米网络科技有限公司
     */
    public <T> T toEntity(Class<T> tClass) throws Exception {
        T returnData = tClass.newInstance();
        returnData = (T) ObjectProperUtil.compareAndValue(this, returnData, true, null);
        return returnData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRawAddTime() {
        return rawAddTime;
    }

    public void setRawAddTime(Date rawAddTime) {
        this.rawAddTime = rawAddTime;
    }

    public Date getRawUpdateTime() {
        return rawUpdateTime;
    }

    public void setRawUpdateTime(Date rawUpdateTime) {
        this.rawUpdateTime = rawUpdateTime;
    }

    public String getRawCreator() {
        return rawCreator;
    }

    public void setRawCreator(String rawCreator) {
        this.rawCreator = rawCreator;
    }

    public String getRawModifier() {
        return rawModifier;
    }

    public void setRawModifier(String rawModifier) {
        this.rawModifier = rawModifier;
    }

}