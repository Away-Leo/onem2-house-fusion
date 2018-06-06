package com.laman.biz.user.domain.repository;

import com.laman.biz.user.domain.entity.UserStandard;
import com.laman.fusion.base.base.BaseRepository;

import java.util.List;

/**
* @Title: UserStandardRepository
* @Description:  标砖制定
* @Author: Away
* @Date: 2018/5/31 15:35
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public interface UserStandardRepository extends BaseRepository<UserStandard, Long> {

    /**
     * @Method:  findByUserId
     * @Author: Away
     * @Version: v1.0
     * @See: 根据用户ID查找
     * @Param: userId
     * @Return: java.util.List<com.laman.biz.user.domain.entity.UserStandard>
     * @Date: 2018/6/4 14:29
     */
    List<UserStandard> findByUserId(Long userId);
}
