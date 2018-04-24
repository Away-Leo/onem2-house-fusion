package com.onem2.web.common.controller;


import com.onem2.biz.user.app.dto.UserDto;
import com.onem2.fusion.base.components.FastDFSClientWrapper;
import com.onem2.web.common.component.LoginComponent;
import com.onem2.web.common.dto.CPViewResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Title: CommonController.java
 * @Description: 公共控制器
 * @Author: Away
 * @Date: 2018/4/12 16:53
 * @Copyright: 重庆壹平方米网络科技有限公司
 * @Version: V1.0
 */
@Slf4j
@RestController
public class LoginController extends AbstractCommonController {

    private final LoginComponent loginComponent;

    private final FastDFSClientWrapper fastDFSClientWrapper;


    @Autowired
    public LoginController(LoginComponent loginComponent,FastDFSClientWrapper fastDFSClientWrapper) {
        this.loginComponent = loginComponent;
        this.fastDFSClientWrapper=fastDFSClientWrapper;
    }

    /**
     * @Author: Away
     * @Title: passwordRegister
     * @Description: 用户注册
     * @Param: cpViewResultInfo 数据交互体
     * @Param: httpServletRequest 请求体
     * @Param: loginModel 登录数据模型
     * @Return: com.onem2.web.common.dto.CPViewResultInfo
     * @Date: 2018/4/12 15:57
     * @Version: 2018/4/12 15:57
     */
    @PostMapping(value = "/passwordRegister.json",name = "登录注册-用户注册")
    public CPViewResultInfo passwordRegister(CPViewResultInfo cpViewResultInfo, @RequestBody UserDto loginModel) {
        try{
            cpViewResultInfo.newSuccess(loginComponent.register(loginModel));
        }catch (Exception e){
            cpViewResultInfo.newFalse(e);
            log.error("用户注册出错",e);
        }
        return cpViewResultInfo;
    }


    /**
     * @Author: Away
     * @Title: logout
     * @Description: 退出登录
     * @Param: info 数据交互体
     * @Return: com.onem2.web.common.dto.CPViewResultInfo
     * @Date: 2018/4/12 17:01
     * @Version: 2018/4/12 17:01
     */
    @GetMapping(value = "/logout.json",name = "登录注册-退出登录")
    public CPViewResultInfo logout(CPViewResultInfo info) {
        try{
//            SecurityUtils.getSubject().logout();
            info.newSuccess(null);
        }catch (Exception e){
            info.newFalse(e);
            log.error("退出登录出错",e);
        }
        return info;
    }

    @PostMapping(value = "/uploadFile.json",name = "文件上传demo")
    public CPViewResultInfo uploadFile(CPViewResultInfo info, MultipartFile multipartFile){
        try{
            info.newSuccess(fastDFSClientWrapper.uploadFile(multipartFile));
        }catch (Exception e){
            info.newFalse(e);
            log.error("",e);
        }
        return info;
    }

}
