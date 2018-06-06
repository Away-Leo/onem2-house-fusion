package com.laman.biz.user.domain.repository;

import com.laman.biz.user.domain.entity.UserRewardSituation;
import com.laman.fusion.base.base.BaseRepository;

import java.util.List;

/**
* @Title: UserRewardSituationRepository
* @Description:  用户奖励情况
* @Author: Away
* @Date: 2018/5/31 15:30
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public interface UserRewardSituationRepository extends BaseRepository<UserRewardSituation, Long> {

    /**
     * @Method:  findByUserId
     * @Author: Away
     * @Version: v1.0
     * @See: 按照用户ID查找
     * @Param: userId
     * @Return: java.util.List<com.laman.biz.user.domain.entity.UserRewardSituation>
     * @Date: 2018/6/4 13:49
     */
    List<UserRewardSituation> findByUserId(Long userId);
}
