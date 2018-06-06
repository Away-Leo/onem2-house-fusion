package com.laman.web.common.security.auth;

import com.laman.biz.user.app.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;

/**
 * @Title: GrantedAuthorityImpl
 * @Description: 权限类型，负责存储权限和角色
 * @Author: Away
 * @Date: 2018/4/15 19:57
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
public class GrantedAuthorityImpl implements GrantedAuthority {

    private String authority;

    /**当前登录用户**/
    private UserDto userDto;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public GrantedAuthorityImpl(UserDto userDto) {
        this.userDto=userDto;
    }
    public GrantedAuthorityImpl(String authority,UserDto userDto) {
        this.userDto=userDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
