package com.onem2.web.common.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onem2.biz.user.app.dto.UserDto;
import com.onem2.fusion.base.enums.ENUM_EXCEPTION;
import com.onem2.fusion.base.util.ObjectHelper;
import com.onem2.web.common.dto.CPViewResultInfo;
import com.onem2.web.common.security.auth.TokenComponent;
import com.onem2.web.common.security.moduls.Onem2AuthToken;
import com.zds.common.lang.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Title: JWTLoginFilter.java
 * @Description:   验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
 * attemptAuthentication ：接收并解析用户凭证。
 * successfulAuthentication ：用户成功登录后，这个方法会被调用，我们在这个方法里生成token。
 * @Author: Away
 * @Date: 2018/4/16 10:43
 * @Copyright: 重庆壹平方米网络科技有限公司
 * @Version: V1.0
 */
@Slf4j
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final Environment env;

    private final TokenComponent tokenComponent;

    public JWTLoginFilter(AuthenticationManager authenticationManager, Environment env,TokenComponent tokenComponent) {
        this.authenticationManager = authenticationManager;
        this.env = env;
        this.tokenComponent=tokenComponent;
    }

    /**
     * @Author: Away
     * @Title: attemptAuthentication
     * @Description: spring security自带/login方法的首个调用方法，此方法用于接收参数 尝试去验证登录
     * @Param: req
     * @Param: res
     * @Return: org.springframework.security.core.Authentication
     * @Date: 2018/4/19 11:16
     * @Version: 2018/4/19 11:16
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            UserDto user = new ObjectMapper().readValue(req.getInputStream(), UserDto.class);
            return authenticationManager.authenticate(new Onem2AuthToken(user));
        } catch (Exception e) {
            log.error("",e);
            if(e.getClass().getName().equalsIgnoreCase(BusinessException.class.getName())){
                BusinessException businessException=(BusinessException) e;
                throw new BadCredentialsException(businessException.getCode(),new Throwable(businessException.getMessage()));
            }else{
                throw new BadCredentialsException(e.getMessage(),e.getCause());
            }
        }
    }

    /**
     * @Author: Away
     * @Title: successfulAuthentication
     * @Description: 登录验证成功后的回调
     * @Param: req
     * @Param: res
     * @Param: chain 过滤器
     * @Param: auth UserDetailService返回的值
     * @Return: void
     * @Date: 2018/4/19 11:15
     * @Version: 2018/4/19 11:15
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req,HttpServletResponse res,FilterChain chain,Authentication auth) throws IOException, ServletException {
        String token = null;
        try {
            //生成token
            token=tokenComponent.buildToken(env);
            res.addHeader("Authorization","Bearer"+token);
            //组装返回信息
            CPViewResultInfo info=new CPViewResultInfo();
            info.setSuccess(true);
            info.setMessage(auth.getName());
            info.setData("Bearer"+token);
            buildReturnJsonMsg(req,res,info);
        } catch (Exception e) {
            CPViewResultInfo info=new CPViewResultInfo();
            info.setSuccess(false);
            if(e.getClass().getName().equalsIgnoreCase(BusinessException.class.getName())){
                BusinessException businessException=(BusinessException)e;
                info.setMessage(businessException.getMessage());
                info.setCode(businessException.getCode());
            }else{
                info.setCode(ENUM_EXCEPTION.E10018.code);
                info.setMessage(ENUM_EXCEPTION.E10018.msg);
            }
            buildReturnJsonMsg(req,res,info);
            log.error(ENUM_EXCEPTION.E10018.msg,e);
            e.printStackTrace();
        }
    }

    /**
     * @Author: Away
     * @Title: unsuccessfulAuthentication
     * @Description: 登录失败回调（此处有坑，要返回AuthenticationException才会被调用）
     * @Param: request
     * @Param: response
     * @Param: failed
     * @Return: void
     * @Date: 2018/4/19 11:13
     * @Version: 2018/4/19 11:13
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        CPViewResultInfo info=new CPViewResultInfo();
        info.setSuccess(false);
        info.setCode(failed.getMessage());
        info.setMessage(ObjectHelper.isNotEmpty(failed.getCause())?failed.getCause().getMessage():failed.getMessage());
        buildReturnJsonMsg(request,response,info);
    }

    /**
     * @Author: Away
     * @Title: buildReturnJsonMsg
     * @Description: 生成返回json数据
     * @Param: request 请求体
     * @Param: response 响应体
     * @Param: info 返回信息
     * @Return: void
     * @Date: 2018/4/19 10:48
     * @Version: 2018/4/19 10:48
     */
    private void buildReturnJsonMsg(HttpServletRequest request, HttpServletResponse response, CPViewResultInfo info) throws IOException {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().print("{\"success\":"+(ObjectHelper.isNotEmpty(info.getSuccess())?info.getSuccess():false)+",\"code\":\""+(ObjectHelper.isNotEmpty(info.getCode())?info.getCode():"")+"\",\"message\":\""+(ObjectHelper.isNotEmpty(info.getMessage())?info.getMessage():"") +"\",\"data\":\""+(ObjectHelper.isNotEmpty(info.getData())?info.getData():"[]")+"\"}");
        return;
    }

}
