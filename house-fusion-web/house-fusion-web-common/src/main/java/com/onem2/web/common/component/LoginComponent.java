package com.onem2.web.common.component;

import com.onem2.biz.user.app.dto.UserDto;
import com.onem2.biz.user.app.service.UserAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Title: LoginComponent.java
 * @Description: 登录组件service
 * @Author: Away
 * @Date: 2018/4/11 9:50
 * @Copyright: 重庆壹平方米网络科技有限公司
 * @Version: V1.0
 */
@Service
@Transactional
public class LoginComponent {
    public static Logger logger = LoggerFactory.getLogger(LoginComponent.class);

    private final UserAppService userAppService;

    @Autowired
    public LoginComponent(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    /**
     * @Author: Away
     * @Title: register
     * @Description: 用户注册
     * @Param: httpServletRequest 请求
     * @Param: loginModel 登录数据模型
     * @Return: com.onem2.biz.user.app.dto.UserDto
     * @Date: 2018/4/12 15:49
     * @Version: 2018/4/12 15:49
     */
    public UserDto register(UserDto loginModel) throws Exception {
        return this.userAppService.userRegister(loginModel);
    }

}
