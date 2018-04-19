package com.onem2.web.common.security.auth;

import com.onem2.biz.user.app.service.UserAppService;
import com.onem2.fusion.base.CPContext;
import com.onem2.fusion.base.enums.ENUM_EXCEPTION;
import com.onem2.fusion.base.util.ObjectHelper;
import com.onem2.fusion.base.util.ZipStrUtil;
import com.onem2.web.common.security.moduls.Onem2AuthToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 自定义身份认证验证组件
 */
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserAppService userAppService;

    public CustomAuthenticationProvider(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder, UserAppService userAppService) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userAppService = userAppService;
    }

    private String getAuthorisedUrl() {
        String returnData = "/front/menu/editSysMenu.json;";
        for (int i = 0; i < 400; i++) {
            returnData += "/front/user/passwordLogin" + i + ".json;";
        }
        return returnData;
    }

    /**
     * @Author: Away
     * @Title: setAuthUrls
     * @Description: 查找并设置当前登录人接口权限
     * @Param: userDetails
     * @Return: void
     * @Date: 2018/4/16 18:16
     * @Version: 2018/4/16 18:16
     */
    private void setAuthUrls() throws AuthenticationException {
        try {
            if (ObjectHelper.isNotEmpty(CPContext.getContext()) && ObjectHelper.isNotEmpty(CPContext.getContext().getSeUserInfo())) {
                try {
                    CPContext.getContext().getSeUserInfo().setAuthUrls(ZipStrUtil.zip(getAuthorisedUrl()));
                } catch (Exception e) {
                    log.error("获取当前登录人功能权限出错", e);
                }
            }
        } catch (Exception e) {
            log.error("查找并设置当前登录人接口权限出错", e);
            throw new BadCredentialsException(ENUM_EXCEPTION.E10017.code, new Throwable(e.getMessage()));
        }
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Onem2AuthToken onem2AuthToken=(Onem2AuthToken)authentication;
        // 获取认证的用户名 & 密码
        String name = onem2AuthToken.getName();
        String password = onem2AuthToken.getCredentials().toString();
        // 认证逻辑
        UserDetails userDetails = userDetailsService.loadUserByUsername(name);
        if (bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {
            // TODO 查找权限并设置
            setAuthUrls();
            //将传递过来的平台编号存入当前登录用户中
            CPContext.getContext().getSeUserInfo().setPlatformCode(onem2AuthToken.getUserDto().getPlatformCode());
            // 生成令牌 这里令牌里面存入了:name,password,authorities, 当然你也可以放其他内容
            return new Onem2AuthToken(onem2AuthToken.getUserDto(),userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException(ENUM_EXCEPTION.E10020.code, new Throwable(ENUM_EXCEPTION.E10020.msg));
        }
    }

    /**
     * 是否可以提供输入类型的认证服务
     *
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(Onem2AuthToken.class);
    }

}
