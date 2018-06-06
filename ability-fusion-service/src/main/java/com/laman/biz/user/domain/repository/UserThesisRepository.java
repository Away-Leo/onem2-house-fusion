package com.laman.biz.user.domain.repository;

import com.laman.biz.user.domain.entity.UserThesis;
import com.laman.fusion.base.base.BaseRepository;

import java.util.List;

/**
* @Title: UserThesisRepository
* @Description:  用户论文
* @Author: Away
* @Date: 2018/5/31 15:36
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public interface UserThesisRepository extends BaseRepository<UserThesis, Long> {

    /**
     * @Method:  findByUserId
     * @Author: Away
     * @Version: v1.0
     * @See: 根据用户ID查找
     * @Param: userId
     * @Return: java.util.List<com.laman.biz.user.domain.entity.UserThesis>
     * @Date: 2018/6/4 14:33
     */
    List<UserThesis> findByUserId(Long userId);
}
