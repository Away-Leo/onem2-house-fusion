package com.onem2.biz.user.domain.repository;

import com.onem2.biz.user.domain.entity.UserInfo;
import com.onem2.fusion.base.base.BaseRepository;

/**
 * @Title: UserInfoRepository.java
 * @Description: 用户基本信息操作库
 * @Author: Away
 * @Date: 2018/4/12 14:35
 * @Copyright: 重庆壹平方米网络科技有限公司
 * @Version: V1.0
 */
public interface UserInfoRepository extends BaseRepository<UserInfo,Long> {

    UserInfo findByUserId(Long userId);
}
