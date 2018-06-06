package com.laman.web.common.component;

import com.laman.biz.user.app.dto.LoginModel;
import com.laman.biz.user.app.dto.UserDto;
import com.laman.biz.user.app.service.UserAppService;
import com.laman.biz.user.app.service.UserPrivateKeyAppService;
import com.laman.fusion.base.CPContext;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.mail.model.Message;
import com.laman.fusion.base.mail.service.MailService;
import com.laman.fusion.base.util.ObjectHelper;
import com.laman.fusion.base.util.ObjectProperUtil;
import com.laman.fusion.base.util.RandomImgCodeUtil;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

/**
 * @Title: LoginComponent.java
 * @Description: 登录组件service
 * @Author: Away
 * @Date: 2018/4/11 9:50
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@Service
@Transactional
public class LoginComponent {

    private final UserAppService userAppService;

    private final MailService mailService;

    private final UserPrivateKeyAppService userPrivateKeyAppService;

    @Autowired
    public LoginComponent(UserAppService userAppService,MailService mailService,UserPrivateKeyAppService userPrivateKeyAppService) {
        this.userAppService = userAppService;
        this.mailService=mailService;
        this.userPrivateKeyAppService=userPrivateKeyAppService;
    }

    /**
     * @Author: Away
     * @Title: register
     * @Description: 用户注册
     * @Param: httpServletRequest 请求
     * @Param: loginModel 登录数据模型
     * @Return: com.laman.biz.user.app.dto.UserDto
     * @Date: 2018/4/12 15:49
     * @Version: 2018/4/12 15:49
     */
    public UserDto register(HttpSession session,LoginModel loginModel) throws Exception {
        //比较验证码
        String random =session.getAttribute(RandomImgCodeUtil.RANDOMCODEKEY).toString();
        if(ObjectHelper.isNotEmpty(random)&&random.equalsIgnoreCase(loginModel.getValidateCode())){
            UserDto returnData=this.userAppService.userRegister(loginModel);
            this.mailService.sendMessageMail(new Message(),loginModel.getEmailAddress(),"注册成功","message.ftl");
            return returnData;
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10025.code,ENUM_EXCEPTION.E10025.msg);
        }
    }

    /**
     * @Method:  loginOut
     * @Author: Away
     * @Version: v1.0
     * @See: 退出登录
     * @Param:
     * @Return: void
     * @Date: 2018/6/5 10:32
     */
    public void loginOut() throws Exception{
        this.userPrivateKeyAppService.writeData(ObjectProperUtil.compareAndValue(CPContext.getContext().getSeUserInfo(),new UserDto(),true,null));
    }

}
