package com.onem2.web.common.security.auth;

import com.onem2.biz.user.app.dto.UserDto;
import com.onem2.biz.user.app.service.UserAppService;
import lombok.extern.log4j.Log4j;
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
 * @Copyright: 重庆壹平方米网络科技有限公司
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
        try{
            UserDto user = userAppService.findByUserName(username);
            if(user == null){
                throw new UsernameNotFoundException(username);
            }
            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), emptyList());
        }catch (Exception e){
            log.error("spring security认证服务获得用户出错",e);
            throw new UsernameNotFoundException(username);
        }
    }

}
