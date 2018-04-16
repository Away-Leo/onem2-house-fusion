package com.onem2.web.common.security.auth;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Title: GrantedAuthorityImpl
 * @Description: 权限类型，负责存储权限和角色
 * @Author: Away
 * @Date: 2018/4/15 19:57
 * @Copyright: 重庆壹平方米网络科技有限公司
 * @Version: V1.0
 */
public class GrantedAuthorityImpl implements GrantedAuthority {

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
