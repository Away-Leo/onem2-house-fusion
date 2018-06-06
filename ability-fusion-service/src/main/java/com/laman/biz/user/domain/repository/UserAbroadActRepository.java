package com.laman.biz.user.domain.repository;

import com.laman.biz.user.domain.entity.UserAbroadAct;
import com.laman.fusion.base.base.BaseRepository;

import java.util.List;

/**
* @Title: UserAbroadActRepository
* @Description:  海外活动
* @Author: Away
* @Date: 2018/5/31 15:24
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public interface UserAbroadActRepository extends BaseRepository<UserAbroadAct, Long> {

    /**
     * @Method:  findByUserId
     * @Author: Away
     * @Version: v1.0
     * @See: 按照用户Id查找
     * @Param: userId
     * @Return: java.util.List<com.laman.biz.user.domain.entity.UserAbroadAct>
     * @Date: 2018/6/4 11:57
     */
    List<UserAbroadAct> findByUserId(Long userId);
}
