package com.laman.web.common.security.auth;

import com.laman.biz.user.app.dto.UserDto;
import com.laman.biz.user.app.service.UserAppService;
import com.laman.fusion.base.CPContext;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

/**
 * @Title: UserDetailsServiceImpl
 * @Description: spring security自带用户信息认证服务
 * @Author: Away
 * @Date: 2018/4/15 19:54
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@Slf4j
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserAppService userAppService;

    /**
     * 通过构造器注入userAppService
     */
    @Autowired
    public UserDetailsServiceImpl(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询数据库
        UserDto user = userAppService.findByUserName(username);
        if(ObjectHelper.isEmpty(user)){
            throw new UsernameNotFoundException(ENUM_EXCEPTION.E10011.code,new Throwable(ENUM_EXCEPTION.E10011.msg));
        }
        CPContext.copyValueAndSetSeUserInfo(user);
        //将查找后的用户作为授权信息传递
        return new org.springframework.security.core.userdetails.User(user.getId()+"", user.getPassword(), emptyList());
    }

}
