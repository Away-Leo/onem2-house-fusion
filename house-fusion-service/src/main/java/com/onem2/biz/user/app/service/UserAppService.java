package com.onem2.biz.user.app.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.onem2.biz.user.app.dto.UserDto;
import com.onem2.biz.user.domain.service.UserDomainService;
import com.onem2.fusion.apis.test.TestInter;
import com.onem2.fusion.base.enums.ENUM_EXCEPTION;
import com.onem2.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Title: UserAppService.java
 * @Description:  用户服务
 * @Author: Away
 * @Date: 2018/4/12 13:53
 * @Copyright: 重庆壹平方米网络科技有限公司
 * @Version: V1.0
 */
@Service
@Transactional
public class UserAppService {

    private final UserDomainService userDomainService;

    private final UserInfoAppService userInfoAppService;

    @Reference
    private TestInter testInter;


    @Autowired
    public UserAppService(UserDomainService userDomainService, UserInfoAppService userInfoAppService) {
        this.userDomainService = userDomainService;
        this.userInfoAppService = userInfoAppService;
    }


    /**
     * @Author: Away
     * @Description: 按照条件查找
     * @Param: pageable
     * @Param: conditions
     * @Return org.springframework.data.domain.Page<com.onem2.biz.user.app.dto.UserDto>
     * @Date 2018/3/1 14:54
     * @Copyright 重庆壹平方米网络科技有限公司
     */
    public Page<UserDto> findManageUserPage(Pageable pageable,UserDto conditions){
        return this.userDomainService.findManageUserPageByCondition(pageable, conditions);
    }

    /**
     * @Author: Away
     * @Title: findByUserName
     * @Description: 按照注册帐号查找
     * @Param: userName 注册帐号
     * @Return: java.util.List<com.onem2.biz.user.app.dto.UserDto>
     * @Date: 2018/4/12 15:01
     * @Version: 2018/4/12 15:01
     */
    public UserDto findByUserName(String userName) throws BusinessException{
        return this.userDomainService.findByUserName(userName);
    }

    /**
     * @Author: Away
     * @Title: userRegister
     * @Description: 用户注册
     * @Param: toRegisterUser 待注册用户数据
     * @Return: com.onem2.biz.user.app.dto.UserDto
     * @Date: 2018/4/12 14:59
     * @Version: 2018/4/12 14:59
     */
    public UserDto userRegister(UserDto toRegisterUser) throws Exception{
        String test=testInter.FirstTest("");
        if(ObjectHelper.isNotEmpty(toRegisterUser)){
            UserDto sameNameUser=this.userDomainService.findByUserName(toRegisterUser.getUserName());
            if(ObjectHelper.isEmpty(sameNameUser)){
                //密码掩码器
                BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
                toRegisterUser.setPassword(bCryptPasswordEncoder.encode(toRegisterUser.getPassword()));
                //新建账号
                UserDto createdUser=this.userDomainService.createUser(toRegisterUser);
                //新建用户信息数据
                this.userInfoAppService.creatNewUserInfo(createdUser);
                return createdUser;
            }else{
                throw new BusinessException(ENUM_EXCEPTION.E10010.code,ENUM_EXCEPTION.E10010.msg);
            }
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }
}
