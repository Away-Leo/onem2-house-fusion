package com.laman.biz.user.domain.repository;

import com.laman.biz.user.domain.entity.UserWorkExperience;
import com.laman.fusion.base.base.BaseRepository;

import java.util.List;

/**
* @Title: UserWorkExperienceRepository
* @Description:  用户工作经历
* @Author: Away
* @Date: 2018/5/31 15:37
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public interface UserWorkExperienceRepository extends BaseRepository<UserWorkExperience, Long> {

    /**
     * @Method:  findByUserId
     * @Author: Away
     * @Version: v1.0
     * @See: 根据用户ID查找
     * @Param: userId
     * @Return: java.util.List<com.laman.biz.user.domain.entity.UserWorkExperience>
     * @Date: 2018/6/4 14:36
     */
    List<UserWorkExperience> findByUserId(Long userId);
}
