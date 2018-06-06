package com.laman.biz.user.domain.repository;

import com.laman.biz.user.domain.entity.UserScientificInvention;
import com.laman.fusion.base.base.BaseRepository;

import java.util.List;

/**
* @Title: UserScientificInventionRepository
* @Description:  科学发明
* @Author: Away
* @Date: 2018/5/31 15:32
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public interface UserScientificInventionRepository extends BaseRepository<UserScientificInvention, Long> {

    /**
     * @Method:  findByUserId
     * @Author: Away
     * @Version: v1.0
     * @See: 根据用户ID查找
     * @Param: userId
     * @Return: java.util.List<com.laman.biz.user.domain.entity.UserScientificInvention>
     * @Date: 2018/6/4 14:26
     */
    List<UserScientificInvention> findByUserId(Long userId);
}
