package com.laman.web.common.security.configs;

import com.laman.biz.role.app.service.SysRoleFuncAuthConfigAppService;
import com.laman.biz.user.app.service.UserAppService;
import com.laman.biz.user.app.service.UserPrivateKeyAppService;
import com.laman.web.common.security.auth.CustomAuthenticationProvider;
import com.laman.web.common.security.auth.TokenComponent;
import com.laman.web.common.security.filters.JWTAuthenticationFilter;
import com.laman.web.common.security.filters.JWTLoginFilter;
import com.laman.web.common.security.handles.RestAuthenticationAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * SpringSecurity的配置
 * 通过SpringSecurity的配置，将JWTLoginFilter，JWTAuthenticationFilter组合在一起
 * @author zhaoxinguo on 2017/9/13.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final Environment env;

    private final UserAppService userAppService;

    private final TokenComponent tokenComponent;

    private final UserPrivateKeyAppService userPrivateKeyAppService;

    private final SysRoleFuncAuthConfigAppService sysRoleFuncAuthConfigAppService;


    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService, Environment env,UserAppService userAppService,TokenComponent tokenComponent,UserPrivateKeyAppService userPrivateKeyAppService,SysRoleFuncAuthConfigAppService sysRoleFuncAuthConfigAppService) {
        this.userDetailsService = userDetailsService;
        this.env = env;
        this.userAppService=userAppService;
        this.tokenComponent=tokenComponent;
        this.userPrivateKeyAppService=userPrivateKeyAppService;
        this.sysRoleFuncAuthConfigAppService=sysRoleFuncAuthConfigAppService;
    }

    // 设置 HTTP 验证规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
            // 所有 /users/signup 的POST请求 都放行
            .antMatchers("/common/**").permitAll()
            .anyRequest().authenticated()  // 所有请求需要身份认证
            .and()
            .addFilter(new JWTLoginFilter(authenticationManager(),env,tokenComponent))
            .addFilter(new JWTAuthenticationFilter(authenticationManager(),env,tokenComponent,userPrivateKeyAppService))
            .logout() // 默认注销行为为logout，可以通过下面的方式来修改
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login")
            .permitAll();// 设置注销成功后跳转页面，默认是跳转到登录页面;
        http.exceptionHandling().accessDeniedHandler(getAccessDeniedHandler());
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //指定密码加密所使用的加密器为passwordEncoder()
        //需要将密码加密后写入数据库
        auth.authenticationProvider(new CustomAuthenticationProvider(userDetailsService,passwordEncoder(),userAppService,sysRoleFuncAuthConfigAppService)); //增加
    }

    @Bean
    public AccessDeniedHandler getAccessDeniedHandler() {
        return new RestAuthenticationAccessDeniedHandler();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
