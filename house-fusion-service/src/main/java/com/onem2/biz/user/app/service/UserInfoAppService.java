package com.onem2.biz.user.app.service;

import com.onem2.biz.user.app.dto.UserDto;
import com.onem2.biz.user.app.dto.UserInfoDto;
import com.onem2.biz.user.domain.service.UserInfoDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Title: UserInfoAppService.java
 * @Description:  用户信息app服务
 * @Author: Away
 * @Date: 2018/4/12 14:19
 * @Copyright: 重庆壹平方米网络科技有限公司
 * @Version: V1.0
 */
@Transactional
@Service
public class UserInfoAppService {

    private final UserInfoDomainService userInfoDomainService;

    @Autowired
    public UserInfoAppService(UserInfoDomainService userInfoDomainService) {
        this.userInfoDomainService = userInfoDomainService;
    }

    /**
     * @Author: Away
     * @Title: creatNewUserInfo
     * @Description: 根据用户表数据新建用户信息数据
     * @Param: userDto
     * @Return: com.onem2.biz.user.app.dto.UserInfoDto
     * @Date: 2018/4/12 14:22
     * @Version: 2018/4/12 14:22
     */
    public UserInfoDto creatNewUserInfo(UserDto userDto) throws Exception{
        return this.userInfoDomainService.creatNewUserInfo(userDto);
    }
}
