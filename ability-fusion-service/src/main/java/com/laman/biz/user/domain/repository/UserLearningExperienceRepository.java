package com.laman.biz.user.domain.repository;

import com.laman.biz.user.domain.entity.UserLearningExperience;
import com.laman.fusion.base.base.BaseRepository;

import java.util.List;

/**
* @Title: UserLearningExperienceRepository
* @Description:  用户学习经历
* @Author: Away
* @Date: 2018/5/31 15:29
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public interface UserLearningExperienceRepository extends BaseRepository<UserLearningExperience, Long> {

    /**
     * @Method:  findByUserId
     * @Author: Away
     * @Version: v1.0
     * @See: 按照用户ID查找
     * @Param: userId
     * @Return: java.util.List<com.laman.biz.user.domain.entity.UserLearningExperience>
     * @Date: 2018/6/4 13:45
     */
    List<UserLearningExperience> findByUserId(Long userId);
}
