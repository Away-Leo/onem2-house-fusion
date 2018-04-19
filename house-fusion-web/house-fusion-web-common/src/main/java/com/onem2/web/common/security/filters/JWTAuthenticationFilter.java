package com.onem2.web.common.security.filters;

import com.alibaba.dubbo.common.utils.IOUtils;
import com.alibaba.fastjson.JSONObject;
import com.onem2.biz.user.app.dto.UserDto;
import com.onem2.biz.user.app.dto.UserPrivateKeyDto;
import com.onem2.biz.user.app.service.UserPrivateKeyAppService;
import com.onem2.fusion.base.CPContext;
import com.onem2.fusion.base.enums.ENUM_EXCEPTION;
import com.onem2.fusion.base.util.DateHelper;
import com.onem2.fusion.base.util.ObjectHelper;
import com.onem2.fusion.base.util.ZipStrUtil;
import com.onem2.web.common.security.auth.TokenComponent;
import com.zds.common.lang.exception.BusinessException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * token的校验
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 *
 * @author zhaoxinguo on 2017/9/13.
 */
@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {


    private final Environment env;

    private final TokenComponent tokenComponent;

    private final UserPrivateKeyAppService userPrivateKeyAppService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, Environment env,TokenComponent tokenComponent,UserPrivateKeyAppService userPrivateKeyAppService) {
        super(authenticationManager);
        this.env = env;
        this.tokenComponent=tokenComponent;
        this.userPrivateKeyAppService=userPrivateKeyAppService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //进行签名认证
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(request,response);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } catch (Exception e) {
            log.error("",e);
            gennerateResponse(request,response,e.getMessage());
            return;
        }
    }

    /**
     * @Author: Away
     * @Title: gennerateResponse
     * @Description: 创建返回信息
     * @Param: request
     * @Param: response
     * @Return: void
     * @Date: 2018/4/16 11:02
     * @Version: 2018/4/16 11:02
     */
    private void gennerateResponse(HttpServletRequest request, HttpServletResponse response,String msg)throws IOException{
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().print("{\"success\":false,\"code\":\"999999\",\"message\":\""+msg+"\",\"data\":[]}");
    }

    /**
     * @Author: Away
     * @Title: getAuthentication
     * @Description: 根据传递的token信息进行验证
     * @Param: response
     * @Param: request
     * @Return: org.springframework.security.authentication.UsernamePasswordAuthenticationToken
     * @Date: 2018/4/16 11:01
     * @Version: 2018/4/16 11:01
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request,HttpServletResponse response) throws Exception{
        //获得token
        String token=getToken(request, response);
        //解析token
        if (token != null) {
            CPContext.SeUserInfo user = null;
            try {
                //组装token中用户信息
                user=packageUserFromToken(token,response);
                //检测访问权限
                checkAccessAuth(user,request);
                if (user != null) {
                    return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                }
            } catch (ExpiredJwtException e) {
                log.error("Token已过期: {} " + e);
                throw new BusinessException(ENUM_EXCEPTION.E10012.code, ENUM_EXCEPTION.E10012.msg);
            } catch (UnsupportedJwtException e) {
                log.error("Token格式错误: {} " + e);
                throw new BusinessException(ENUM_EXCEPTION.E10013.code, ENUM_EXCEPTION.E10013.msg);
            } catch (MalformedJwtException e) {
                log.error("Token没有被正确构造: {} " + e);
                throw new BusinessException(ENUM_EXCEPTION.E10014.code, ENUM_EXCEPTION.E10014.msg);
            } catch (SignatureException e) {
                log.error("签名失败: {} " + e);
                throw new BusinessException(ENUM_EXCEPTION.E10015.code, ENUM_EXCEPTION.E10015.msg);
            } catch (IllegalArgumentException e) {
                log.error("非法参数异常: {} " + e);
                throw new BusinessException(ENUM_EXCEPTION.E10016.code, ENUM_EXCEPTION.E10016.msg);
            }
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10019.code, ENUM_EXCEPTION.E10019.msg);
        }
        return null;
    }

    /**
     * @Author: Away
     * @Title: getToken
     * @Description: 获取token
     * @Param: request
     * @Param: response
     * @Return: java.lang.String
     * @Date: 2018/4/17 15:40
     * @Version: 2018/4/17 15:40
     */
    private String getToken(HttpServletRequest request,HttpServletResponse response) throws Exception{
        String token="";
        //获取请求头中的token信息
        token=request.getHeader("Authorization");
        if(ObjectHelper.isEmpty(token)){
            //获取参数中的token信息
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String body = IOUtils.read(reader);
            JSONObject jsonObject=JSONObject.parseObject(body);
            if(ObjectHelper.isNotEmpty(jsonObject))token=jsonObject.getString("Authorization");
            if(ObjectHelper.isEmpty(token))throw new BusinessException(ENUM_EXCEPTION.E10019.code,ENUM_EXCEPTION.E10019.msg);
        }
        return token;
    }

    /**
     * @Author: Away
     * @Title: packageUserFromToken
     * @Description: 组装token中包含的用户信息
     * @Param: token
     * @Return: com.onem2.fusion.base.CPContext.SeUserInfo
     * @Date: 2018/4/17 11:57
     * @Version: 2018/4/17 11:57
     */
    private CPContext.SeUserInfo packageUserFromToken(String token,HttpServletResponse response) throws Exception{
        if(ObjectHelper.isNotEmpty(token)){
            //获得token中的claims信息
            Claims claims = Jwts.parser()
                    .setSigningKey(env.getProperty("spring.security.signingkey"))
                    .parseClaimsJws(token.replace("Bearer", ""))
                    .getBody();
            Map<String,Object> mapUser= (LinkedHashMap)claims.get("user");
            UserDto userDto=new UserDto();
            userDto.setAuthUrls(ObjectHelper.isNotEmpty(mapUser.get("authUrls"))? ZipStrUtil.unzip(mapUser.get("authUrls")+""):null);
            userDto.setPhone(ObjectHelper.isNotEmpty(mapUser.get("phone"))?mapUser.get("phone")+"":null);
            userDto.setType(ObjectHelper.isNotEmpty(mapUser.get("type"))?mapUser.get("type")+"":null);
            userDto.setId(ObjectHelper.isNotEmpty(mapUser.get("id"))?Long.valueOf(mapUser.get("id")+""):null);
            userDto.setUserName(ObjectHelper.isNotEmpty(mapUser.get("userName"))?mapUser.get("userName")+"":null);
            userDto.setPlatformCode(ObjectHelper.isNotEmpty(mapUser.get("platformCode"))?mapUser.get("platformCode")+"":null);
            userDto.setPrivateKey(ObjectHelper.isNotEmpty(mapUser.get("privateKey"))?mapUser.get("privateKey")+"":null);
            //为全局线程变量赋值
            CPContext.copyValueAndSetSeUserInfo(userDto);
            //token的续命
            tokenExpirationContinue(claims.getExpiration(),response,userDto);
            //赋值
            return CPContext.getContext().getSeUserInfo();
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10019.code,ENUM_EXCEPTION.E10019.msg);
        }
    }

    /**
     * @Author: Away
     * @Title: tokenExpirationContinue
     * @Description: 检测token过期时间，如若离过期在2分钟之内则颁发个新token
     * @Param: expirationDate
     * @Param: request
     * @Param: response
     * @Return: void
     * @Date: 2018/4/17 17:15
     * @Version: 2018/4/17 17:15
     */
    private void  tokenExpirationContinue(Date expirationDate,HttpServletResponse response,UserDto userDto) throws Exception{
        if(ObjectHelper.isNotEmpty(expirationDate)){
            //token的验证
            UserPrivateKeyDto userPrivateKeyDto=this.userPrivateKeyAppService.findByUserIdAndPlatformCode(userDto);
            if(!userPrivateKeyDto.getPrivateKey().equalsIgnoreCase(userDto.getPrivateKey()))
                throw new BusinessException(ENUM_EXCEPTION.E10012.code,ENUM_EXCEPTION.E10012.msg);

            //过期时间剩余时间
            Long leftExp= DateHelper.getDateBetween(new Date(),expirationDate);
            if(leftExp<60 * 1000){
                String token=tokenComponent.buildToken(env);
                response.addHeader("Authorization","Bearer"+token);
            }
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @Author: Away
     * @Title: checkAccessAuth
     * @Description: 检查访问权限
     * @Param: userDto
     * @Param: request
     * @Return: void
     * @Date: 2018/4/17 16:14
     * @Version: 2018/4/17 16:14
     */
    private void checkAccessAuth(CPContext.SeUserInfo userDto,HttpServletRequest request) throws BusinessException{
        if(ObjectHelper.isNotEmpty(userDto)&&ObjectHelper.isNotEmpty(userDto.getAuthUrls())){
            //获得访问地址
            String url=getRequestURI(request);
            //比对此地址是否在已经授权的地址中
            if(!userDto.getAuthUrls().contains(url+";")){
                throw new BusinessException(ENUM_EXCEPTION.E10022.code,ENUM_EXCEPTION.E10022.msg);
            }
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }

    }

    /**
     * @Author: Away
     * @Title: getRequestURI
     * @Description: 获得访问地址
     * @Param: request
     * @Return: java.lang.String
     * @Date: 2018/4/17 16:11
     * @Version: 2018/4/17 16:11
     */
    private String getRequestURI(HttpServletRequest request) {
        String uri = request.getRequestURI();
        int idx = uri.indexOf(';');
        if (idx > -1) {
            uri = uri.substring(0, idx);
        }
        return uri;
    }

}
