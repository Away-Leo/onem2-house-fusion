package com.onem2.web.common.component;

import com.onem2.biz.menu.app.service.UserFunctionAuthConfAppService;
import com.onem2.biz.user.app.dto.UserDto;
import com.onem2.biz.user.app.service.UserAppService;
import com.onem2.fusion.base.enums.ENUM_EXCEPTION;
import com.onem2.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

    private final UserFunctionAuthConfAppService userFunctionAuthConfAppService;

    @Autowired
    public LoginComponent(UserAppService userAppService, UserFunctionAuthConfAppService userFunctionAuthConfAppService) {
        this.userAppService = userAppService;
        this.userFunctionAuthConfAppService = userFunctionAuthConfAppService;
    }

    /**
     * @Author: Away
     * @Title: loginWithPassword
     * @Description: 密码登录
     * @Param: httpServletRequest 请求体
     * @Param: userDto 登录数据模型
     * @Return: com.onem2.biz.user.app.dto.UserDto
     * @Date: 2018/4/12 15:51
     * @Version: 2018/4/12 15:51
     */
    public UserDto loginWithPassword(HttpServletRequest httpServletRequest, UserDto userDto) throws Exception {
        UserDto seUser = loginVerify(userDto);
//        AuthenticationToken token = new CwAuthenticationToken(userDto.getUserName(), userDto.getPassword());
//        login(httpServletRequest, seUser, token);
        return seUser;
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

    /**
     * @Author: Away
     * @Title: loginVerify
     * @Description: 用户验证（检验是否存在此用户）
     * @Param: userDto 待验证用户
     * @Return: com.onem2.biz.user.app.dto.UserDto
     * @Date: 2018/4/12 15:20
     * @Version: 2018/4/12 15:20
     */
    private UserDto loginVerify(UserDto userDto) throws Exception {
        if (ObjectHelper.isNotEmpty(userDto) && ObjectHelper.isNotEmpty(userDto.getUserName())) {
            UserDto oldUser = this.userAppService.findByUserName(userDto.getUserName());
            if (ObjectHelper.isEmpty(oldUser))
                throw new BusinessException(ENUM_EXCEPTION.E10011.code, ENUM_EXCEPTION.E10011.msg);
            return oldUser;
        } else {
            throw new BusinessException(ENUM_EXCEPTION.E10001.code, ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @Author: Away
     * @Title: login
     * @Description: 登录核心方法
     * @Param: httpServletRequest 请求
     * @Param: userDto 登录参数
     * @Param: token 生成令牌
     * @Return: void
     * @Date: 2018/4/12 15:52
     * @Version: 2018/4/12 15:52
     */
//    private void login(HttpServletRequest httpServletRequest, UserDto userDto, AuthenticationToken token) {
//        try {
//            Subject subject = SecurityUtils.getSubject();
//            subject.login(token);
//        } catch (AuthenticationException e) {
//            CwException.throwIt(e.getMessage());
//        }
//        CPContext.SeUserInfo seUserInfo = Copier.copy(userDto, CPContext.SeUserInfo.class);
//        httpServletRequest.getSession().setAttribute("seUserInfo", seUserInfo);
//        seUserInfo.setAuthUrls(this.userFunctionAuthConfAppService.authoriedUrls(userDto.getId()));
//    }

}
