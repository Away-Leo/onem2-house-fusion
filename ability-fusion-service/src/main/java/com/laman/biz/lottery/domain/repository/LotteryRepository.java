package com.laman.biz.lottery.domain.repository;

import com.laman.biz.lottery.domain.entity.LotteryConfig;
import com.laman.fusion.base.base.BaseRepository;

/**
* @Title: LotteryRepository
* @Description:  抽奖自定义操作库
* @Author: Away
* @Date: 2018/5/23 14:34
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public interface LotteryRepository  extends BaseRepository<LotteryConfig,Long>{

    /**
     * @Method:  findTop1OrderByRawAddTimeDesc
     * @Author: Away
     * @Version:  1.0
     * @See: 查找第一条
     * @Param:
     * @Return: com.laman.biz.lottery.domain.entity.LotteryConfig
     * @Exception:
     * @Date: 2018/5/23 14:36
     */
    LotteryConfig findTop1ByOrderByRawAddTimeDesc();
}
