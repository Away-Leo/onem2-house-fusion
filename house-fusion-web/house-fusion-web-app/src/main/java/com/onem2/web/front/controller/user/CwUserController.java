package com.onem2.web.front.controller.user;

import com.onem2.biz.user.app.dto.UserDto;
import com.onem2.biz.user.app.service.UserAppService;
import com.onem2.fusion.base.dtos.DataTablesPage;
import com.onem2.fusion.base.page.DataTablesPageRequest;
import com.onem2.web.common.dto.CPViewResultInfo;
import com.onem2.web.front.controller.AbstractFrontController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: CwUserController.java
 * @Description:  用户个人信息
 * @Author: Away
 * @Date: 2018/4/12 18:06
 * @Copyright: 重庆壹平方米网络科技有限公司
 * @Version: V1.0
 */
@RestController
public class CwUserController extends AbstractFrontController {

    private final UserAppService userAppService;

    private static final Logger logger= LoggerFactory.getLogger(CwUserController.class);

    @Autowired
    public CwUserController(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    @PostMapping(value = "/userInfo/getUsersPageWithoutUser.json",name = "用户-获得用户列表(排除一般用户)")
    public CPViewResultInfo getUsersPageWithoutUser(DataTablesPage dataTablesPage, CPViewResultInfo info, UserDto condition){
        try{
            Page<UserDto> sourceData=this.userAppService.findManageUserPage(new DataTablesPageRequest(dataTablesPage),condition);
            info.setData(sourceData);
            info.setSuccess(true);
            info.setDraw(dataTablesPage.getDraw());
        }catch (Exception e){
            info.setSuccess(false);
            info.setMessage(e.getMessage());
            e.printStackTrace();
            logger.error("获得用户原始数据列表出错",e);
        }
        return info;
    }

//    /**
//     * 头像上传
//     * @param file
//     * @return
//     */
//    @PostMapping("/userInfo/upload.json")
//    @ResponseBody
//    public CPViewResultInfo upload(MultipartFile file) {
//        CPViewResultInfo cpViewResultInfo = new CPViewResultInfo();
//        String filePath = uploadFileComponent.upload(file);
//        UserInfoDto UserInfoDto = new UserInfoDto();
//        UserInfoDto.setHeadImg(filePath);
//        UserInfoDto.setUserId(CPContext.getContext().getSeUserInfo().getId());
//        Long id = UserInfoAppService.update(UserInfoDto);
//        cpViewResultInfo.setData(filePath);
//        cpViewResultInfo.setSuccess(true);
//        cpViewResultInfo.setMessage("上传成功");
//        return cpViewResultInfo;
//    }
}
