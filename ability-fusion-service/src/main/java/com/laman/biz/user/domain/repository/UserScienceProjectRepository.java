package com.laman.biz.user.domain.repository;

import com.laman.biz.user.domain.entity.UserScienceProject;
import com.laman.fusion.base.base.BaseRepository;

import java.util.List;

/**
* @Title: UserScienceProjectRepository
* @Description:  科研项目
* @Author: Away
* @Date: 2018/5/31 15:31
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public interface UserScienceProjectRepository extends BaseRepository<UserScienceProject, Long> {

    /**
     * @Method:  findByUserId
     * @Author: Away
     * @Version: v1.0
     * @See: 按照用户ID查找
     * @Param: userId
     * @Return: java.util.List<com.laman.biz.user.domain.entity.UserScienceProject>
     * @Date: 2018/6/4 14:00
     */
    List<UserScienceProject> findByUserId(Long userId);
}
