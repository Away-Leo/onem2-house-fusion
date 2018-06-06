package com.laman.web.common.security.auth;

import com.laman.biz.user.app.dto.UserDto;
import com.laman.biz.user.app.dto.UserPrivateKeyDto;
import com.laman.biz.user.app.service.UserPrivateKeyAppService;
import com.laman.fusion.base.CPContext;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: TokenComponent.java
 * @Description:  token组件
 * @Author: Away
 * @Date: 2018/4/18 17:25
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@Service
@Transactional
public class TokenComponent {

    private final UserPrivateKeyAppService userPrivateKeyAppService;

    @Autowired
    public TokenComponent(UserPrivateKeyAppService userPrivateKeyAppService) {
        this.userPrivateKeyAppService = userPrivateKeyAppService;
    }

    /**
     * @Author: Away
     * @Title: buildToken
     * @Description: 生成token
     * @Param: env 应用环境
     * @Param: userDto 用户从前台传入的参数
     * @Return: java.lang.String
     * @Date: 2018/4/18 17:21
     * @Version: 2018/4/18 17:21
     */
    public String buildToken(Environment env) throws Exception {
        //非空判断
        String token="";
        //将当前登录用户放入token中
        Map<String,Object> claims=new HashMap<>();
        claims=setCurrentLoginUserToToken(claims);
        token = Jwts.builder()
                .setSubject(CPContext.getContext().getSeUserInfo().getUserName())
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 24*60*(60 * 1000))) // 设置过期时间 10分钟
                .signWith(SignatureAlgorithm.HS512,env.getProperty("spring.security.signingkey")) //采用什么算法是可以自己选择的，不一定非要采用HS512
                .compact();
        if(ObjectHelper.isEmpty(token))throw new BusinessException(ENUM_EXCEPTION.E10018.code,ENUM_EXCEPTION.E10018.msg);
        return token;
    }

    /**
     * @Author: Away
     * @Title: setCurrentLoginUserToToken
     * @Description: 将当前登录用户存入token中
     * @Param: auth
     * @Param: params
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     * @Date: 2018/4/16 17:46
     * @Version: 2018/4/16 17:46
     */
    private Map<String,Object> setCurrentLoginUserToToken(Map<String,Object> params) throws Exception{
        if(ObjectHelper.isEmpty(CPContext.getContext())||ObjectHelper.isEmpty(CPContext.getContext().getSeUserInfo()));
        //生成当前登录用户的私钥
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(CPContext.getContext().getSeUserInfo(),userDto);
        UserPrivateKeyDto userPrivateKeyDto=this.userPrivateKeyAppService.writeData(userDto);
        //将私人密钥存入当前用户中
        CPContext.getContext().getSeUserInfo().setPrivateKey(userPrivateKeyDto.getPrivateKey());
        params.put("user", CPContext.getContext().getSeUserInfo());
        return params;
    }

}
