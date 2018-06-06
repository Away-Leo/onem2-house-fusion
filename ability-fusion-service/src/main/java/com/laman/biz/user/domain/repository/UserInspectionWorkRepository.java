package com.laman.biz.user.domain.repository;

import com.laman.biz.user.domain.entity.UserInspectionWork;
import com.laman.fusion.base.base.BaseRepository;

import java.util.List;

/**
* @Title: UserInspectionWorkRepository
* @Description:  重要工作或检查工作
* @Author: Away
* @Date: 2018/5/31 15:28
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public interface UserInspectionWorkRepository extends BaseRepository<UserInspectionWork, Long> {

    /**
     * @Method:  findByUserId
     * @Author: Away
     * @Version: v1.0
     * @See: 根据用户ID查找
     * @Param: userId
     * @Return: java.util.List<com.laman.biz.user.app.dto.UserInspectionWorkDto>
     * @Date: 2018/6/4 12:07
     */
    List<UserInspectionWork> findByUserId(Long userId);
}
