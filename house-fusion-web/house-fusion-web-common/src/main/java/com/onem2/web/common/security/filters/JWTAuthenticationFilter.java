package com.onem2.web.common.security.filters;

import com.onem2.fusion.base.enums.ENUM_EXCEPTION;
import com.zds.common.lang.exception.BusinessException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * token的校验
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 * @author zhaoxinguo on 2017/9/13.
 */
@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    @Value("${spring.security.signingkey}")
    private String signKey;

    private final Environment env;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,Environment env) {
        super(authenticationManager);
        this.env=env;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(response,request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletResponse response,HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            // parse the token.
            String user = null;
            try {
                user = Jwts.parser()
                        .setSigningKey(signKey)
                        .parseClaimsJws(token.replace("Bearer ", ""))
                        .getBody()
                        .getSubject();
                if (user != null) {
                    return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                }
            } catch (ExpiredJwtException e) {
                log.error("Token已过期: {} " + e);
                throw new BusinessException(ENUM_EXCEPTION.E10012.code,ENUM_EXCEPTION.E10012.msg);
            } catch (UnsupportedJwtException e) {
                log.error("Token格式错误: {} " + e);
                throw new BusinessException(ENUM_EXCEPTION.E10013.code,ENUM_EXCEPTION.E10013.msg);
            } catch (MalformedJwtException e) {
                log.error("Token没有被正确构造: {} " + e);
                throw new BusinessException(ENUM_EXCEPTION.E10014.code,ENUM_EXCEPTION.E10014.msg);
            } catch (SignatureException e) {
                log.error("签名失败: {} " + e);
                throw new BusinessException(ENUM_EXCEPTION.E10015.code,ENUM_EXCEPTION.E10015.msg);
            } catch (IllegalArgumentException e) {
                log.error("非法参数异常: {} " + e);
                throw new BusinessException(ENUM_EXCEPTION.E10016.code,ENUM_EXCEPTION.E10016.msg);
            }
        }
        return null;
    }

}
