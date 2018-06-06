package com.laman.biz.lottery.app.enums;

/**
* @Title: ENUM_LOTTERY_STATUS
* @Description:  抽奖状态
* @Author: Away
* @Date: 2018/5/23 14:26
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public enum ENUM_LOTTERY_STATUS {

    /**
     * 开始前
     */
    AFTER(1),

    /**
     * 进行中
     */
    ING(2),

    /**
     * 抽奖
     */
    PRICE(3),

    /**
     * 结束
     */
    END(4);

    private final  int value;

    private ENUM_LOTTERY_STATUS(int value){
        this.value=value;
    }
}
