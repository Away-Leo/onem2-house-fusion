package com.laman.web.front.controller.user;

import com.laman.biz.user.app.dto.*;
import com.laman.biz.user.app.service.UserAppService;
import com.laman.biz.user.app.service.UserInfoAppService;
import com.laman.fusion.base.dtos.DataTablesPage;
import com.laman.fusion.base.page.DataTablesPageRequest;
import com.laman.web.common.dto.CPViewResultInfo;
import com.laman.web.front.controller.AbstractFrontController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: CwUserController.java
 * @Description:  用户个人信息
 * @Author: Away
 * @Date: 2018/4/12 18:06
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@Slf4j
@RestController
public class CwUserController extends AbstractFrontController {

    private final UserAppService userAppService;

    private final UserInfoAppService userInfoAppService;


    @Autowired
    public CwUserController(UserAppService userAppService,UserInfoAppService userInfoAppService) {
        this.userAppService = userAppService;
        this.userInfoAppService=userInfoAppService;
    }

    /**
     * @Method:  getUsersPageWithoutUser
     * @Author: Away
     * @Version: v1.0
     * @See: 获取管理员以及以上用户
     * @Param: dataTablesPage
     * @Param: info
     * @Param: condition
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/5/31 17:52
     */
    @PostMapping(value = "/userInfo/getUsersPageWithoutUser.json",name = "用户-获得用户列表(排除一般用户)")
    public CPViewResultInfo getUsersPageWithoutUser(DataTablesPage dataTablesPage, CPViewResultInfo info, UserDto condition){
        try{
            info.newSuccess(this.userAppService.findManageUserPage(new DataTablesPageRequest(dataTablesPage),condition));
            info.setDraw(dataTablesPage.getDraw());
        }catch (Exception e){
            info.newFalse(e);
            log.error("获得用户原始数据列表出错",e);
        }
        return info;
    }

    /**
     * @Method:  saveOrUpdateUserInfo
     * @Author: Away
     * @Version: v1.0
     * @See: 新增或修改用户基本信息
     * @Param: info
     * @Param: userInfoDto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/1 14:24
     */
    @PostMapping(value = "/userInfo/saveOrUpdateUserInfo.json",name = "个人信息-新增或修改用户基本信息")
    public CPViewResultInfo saveOrUpdateUserInfo(CPViewResultInfo info,@RequestBody UserInfoDto userInfoDto){
        try{
            info.newSuccess(this.userAppService.saveOrUpdateUserInfo(userInfoDto));
        }catch (Exception e){
            info.newFalse(e);
            log.error("个人信息-新增或修改用户基本信息",e);
        }
        return info;
    }

    /**
     * @Method:  getCurrentUserInfo
     * @Author: Away
     * @Version: v1.0
     * @See: 获取当前登录用户的基本信息
     * @Param: info
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/1 14:34
     */
    @GetMapping(value = "/userInfo/getCurrentUserInfo.json",name = "个人信息-获取当前登录用户的基本信息")
    public CPViewResultInfo getCurrentUserInfo(CPViewResultInfo info){
        try{
            info.newSuccess(this.userAppService.findCurrentUserInfo());
        }catch (Exception e){
            info.newFalse(e);
            log.error("个人信息-获取当前登录用户的基本信息",e);
        }
        return info;
    }

    /**
     * @Method:  saveOrUpdateUserAbroadAct
     * @Author: Away
     * @Version: v1.0
     * @See: 个人信息-新增或修改用户国际活动
     * @Param: info
     * @Param: userAbroadActDto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/5/31 17:59
     */
    @PostMapping(value = "/user/saveOrUpdateUserAbroadAct.json",name = "个人信息-新增或修改用户国际活动")
    public CPViewResultInfo saveOrUpdateUserAbroadAct(CPViewResultInfo info,@RequestBody UserAbroadActDto userAbroadActDto){
        try{
            info.newSuccess(this.userAppService.saveOrUpdateUserAbroadAct(userAbroadActDto));
        }catch (Exception e){
            info.newFalse(e);
            log.error("个人信息-新增或修改用户国际活动",e);
        }
        return info;
    }

    /**
     * @Method:  getUserAbroadActList
     * @Author: Away
     * @Version: v1.0
     * @See: 个人信息-获取用户国际活动列表
     * @Param: info
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/5/31 18:09
     */
    @GetMapping(value = "/user/getUserAbroadActList.json",name = "个人信息-获取用户国际活动列表")
    public CPViewResultInfo getUserAbroadActList(CPViewResultInfo info,UserAbroadActDto userAbroadActDto){
        try{
            info.newSuccess(this.userAppService.findUserAbroadActList(userAbroadActDto.getUserId()));
        }catch (Exception e){
            info.newFalse(e);
            log.error("个人信息-获取用户国际活动列表",e);
        }
        return info;
    }

    @PostMapping(value = "/user/deleteUserAbroadAct.json",name = "个人信息-删除用户国际活动列表")
    public CPViewResultInfo deleteUserAbroadAct(CPViewResultInfo info,@RequestBody UserAbroadActDto userAbroadActDto){
        try{
            this.userAppService.deleteUserAbroadAct(userAbroadActDto.getId());
            info.newSuccess(null);
        }catch (Exception e){
            info.newFalse(e);
            log.error("个人信息-删除用户国际活动列表",e);
        }
        return info;
    }

    /**
     * @Method:  saveOrUpdateUserFile
     * @Author: Away
     * @Version: v1.0
     * @See: 个人信息-更新或保存用户文件
     * @Param: info
     * @Param: userFileDto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/5/31 18:16
     */
    @PostMapping(value = "/user/saveOrUpdateUserFile.json",name = "个人信息-更新或保存用户文件")
    public CPViewResultInfo saveOrUpdateUserFile(CPViewResultInfo info, @RequestBody UserFileDto userFileDto){
        try{
            info.newSuccess(this.userAppService.saveOrUpdateUserFile(userFileDto));
        }catch (Exception e){
            info.newFalse(e);
            log.error("",e);
        }
        return info;
    }

    /**
     * @Method:  getUserFileList
     * @Author: Away
     * @Version: v1.0
     * @See: 个人信息-获取用户文件
     * @Param: info
     * @Param: userFileDto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/5/31 18:18
     */
    @GetMapping(value = "/user/getUserFileList.json",name = "个人信息-获取用户文件")
    public CPViewResultInfo getUserFileList(CPViewResultInfo info,UserFileDto userFileDto){
        try{
            info.newSuccess(this.userAppService.findUserFileList(userFileDto.getUserId()));
        }catch (Exception e){
            info.newFalse(e);
            log.error("",e);
        }
        return info;
    }

    @PostMapping(value = "/user/deleteUserFile.json",name = "个人信息-删除用户文件")
    public CPViewResultInfo deleteUserFile(CPViewResultInfo info,@RequestBody UserFileDto userFileDto){
        try{
            this.userAppService.deleteUserFile(userFileDto.getId());
            info.newSuccess(null);
        }catch (Exception e){
            info.newFalse(e);
            log.error("个人信息-删除用户文件",e);
        }
        return info;
    }

    /**
     * @Method:  saveOrUpdateUserInspectionWork
     * @Author: Away
     * @Version: v1.0
     * @See: 个人信息-更新或保存重要工作或视察工作
     * @Param: info
     * @Param: userFileDto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/5/31 18:20
     */
    @PostMapping(value = "/user/saveOrUpdateUserInspectionWork.json",name = "个人信息-更新或保存重要工作或视察工作")
    public CPViewResultInfo saveOrUpdateUserInspectionWork(CPViewResultInfo info, @RequestBody UserInspectionWorkDto inspectionWorkDto){
        try{
            info.newSuccess(this.userAppService.saveOrUpdateUserInspection(inspectionWorkDto));
        }catch (Exception e){
            info.newFalse(e);
            log.error("",e);
        }
        return info;
    }

    /**
     * @Method:  getUserInspectionWorkList
     * @Author: Away
     * @Version: v1.0
     * @See: 个人信息-获取重要工作或视察工作列表
     * @Param: info
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/5/31 18:23
     */
    @GetMapping(value = "/user/getUserInspectionWorkList.json",name = "个人信息-获取重要工作或视察工作列表")
    public CPViewResultInfo getUserInspectionWorkList(CPViewResultInfo info,UserInspectionWorkDto inspectionWorkDto){
        try{
            info.newSuccess(this.userAppService.findUserInspectionWorkList(inspectionWorkDto.getUserId()));
        }catch (Exception e){
            info.newFalse(e);
            log.error("",e);
        }
        return info;
    }

    @PostMapping(value = "/user/deleteUserInspectionWork.json",name = "个人信息-删除重要工作或视察工作")
    public CPViewResultInfo deleteUserInspectionWork(CPViewResultInfo info,@RequestBody UserInspectionWorkDto inspectionWorkDto){
        try{
            this.userAppService.deleteUserInspectionWork(inspectionWorkDto.getId());
            info.newSuccess(null);
        }catch (Exception e){
            info.newFalse(e);
            log.error("个人信息-重要工作或视察工作",e);
        }
        return info;
    }

    /**
     * @Method:  saveOrUpdateUserLearningExperience
     * @Author: Away
     * @Version: v1.0
     * @See: 个人信息-更新或保存用户学习经历
     * @Param: info
     * @Param: userLearningExperienceDto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/5/31 18:25
     */
    @PostMapping(value = "/user/saveOrUpdateUserLearningExperience.json",name = "个人信息-更新或保存用户学习经历")
    public CPViewResultInfo saveOrUpdateUserLearningExperience(CPViewResultInfo info, @RequestBody UserLearningExperienceDto userLearningExperienceDto){
        try{
            info.newSuccess(this.userAppService.saveOrUpdateUserLearning(userLearningExperienceDto));
        }catch (Exception e){
            info.newFalse(e);
            log.error("",e);
        }
        return info;
    }

    /**
     * @Method:  getUserLearningExperienceList
     * @Author: Away
     * @Version: v1.0
     * @See: 个人信息-获取用户学习经历列表
     * @Param: info
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/5/31 18:27
     */
    @GetMapping(value = "/user/getUserLearningExperienceList.json",name = "个人信息-获取用户学习经历列表")
    public CPViewResultInfo getUserLearningExperienceList(CPViewResultInfo info,UserLearningExperienceDto userLearningExperienceDto){
        try{
            info.newSuccess(this.userAppService.findUserLearningList(userLearningExperienceDto.getUserId()));
        }catch (Exception e){
            info.newFalse(e);
            log.error("",e);
        }
        return info;
    }

    @PostMapping(value = "/user/deleteUserLearningExperience.json",name = "个人信息-删除用户学习经历")
    public CPViewResultInfo deleteUserLearningExperience(CPViewResultInfo info,@RequestBody UserLearningExperienceDto learningExperienceDto){
        try{
            this.userAppService.deleteUserLearning(learningExperienceDto.getId());
            info.newSuccess(null);
        }catch (Exception e){
            info.newFalse(e);
            log.error("个人信息-删除用户学习经历",e);
        }
        return info;
    }

    /**
     * @Method:  saveOrUpdateUserReward
     * @Author: Away
     * @Version: v1.0
     * @See: 个人信息-更新或保存用户的获奖情况
     * @Param: info
     * @Param: userRewardSituationDto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/5/31 18:29
     */
    @PostMapping(value = "/user/saveOrUpdateUserReward.json",name = "个人信息-更新或保存用户的获奖情况")
    public CPViewResultInfo saveOrUpdateUserReward(CPViewResultInfo info, @RequestBody UserRewardSituationDto userRewardSituationDto){
        try{
            info.newSuccess(this.userAppService.saveOrUpdateUserRewardSituation(userRewardSituationDto));
        }catch (Exception e){
            info.newFalse(e);
            log.error("",e);
        }
        return info;
    }

    /**
     * @Method:  getUserReward
     * @Author: Away
     * @Version: v1.0
     * @See: 个人信息-获得用户的获奖情况
     * @Param: info
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/5/31 18:31
     */
    @GetMapping(value = "/user/getUserReward.json",name = "个人信息-获得用户的获奖情况")
    public CPViewResultInfo getUserReward(CPViewResultInfo info,UserRewardSituationDto userRewardSituationDto){
        try{
            info.newSuccess(this.userAppService.findUserRewardSituationList(userRewardSituationDto.getUserId()));
        }catch (Exception e){
            info.newFalse(e);
            log.error("",e);
        }
        return info;
    }

    @PostMapping(value = "/user/deleteUserReward.json",name = "个人信息-删除用户的获奖情况")
    public CPViewResultInfo deleteUserReward(CPViewResultInfo info,@RequestBody UserRewardSituationDto userRewardSituationDto){
        try{
            this.userAppService.deleteUserRewardSituation(userRewardSituationDto.getId());
            info.newSuccess(null);
        }catch (Exception e){
            info.newFalse(e);
            log.error("个人信息-删除用户的获奖情况",e);
        }
        return info;
    }

    /**
     * @Method:  saveOrUpdateScienceProject
     * @Author: Away
     * @Version: v1.0
     * @See: 个人信息-新增或修改科研项目
     * @Param: info
     * @Param: userScienceProjectDto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/1 9:41
     */
    @PostMapping(value = "/user/saveOrUpdateScienceProject.json",name = "个人信息-新增或修改科研项目")
    public CPViewResultInfo saveOrUpdateScienceProject(CPViewResultInfo info,@RequestBody UserScienceProjectDto userScienceProjectDto){
        try{
            info.newSuccess(this.userAppService.saveOrUpdateUserScienceProject(userScienceProjectDto));
        }catch (Exception e){
            info.newFalse(e);
            log.error("",e);
        }
        return info;
    }

    /**
     * @Method:  getScienceProjectList
     * @Author: Away
     * @Version: v1.0
     * @See: 个人信息-获取科研项目列表
     * @Param: info
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/1 9:45
     */
    @GetMapping(value = "/user/getScienceProjectList.json",name = "个人信息-获取科研项目列表")
    public CPViewResultInfo getScienceProjectList(CPViewResultInfo info,UserScienceProjectDto userScienceProjectDto){
        try{
            info.newSuccess(this.userAppService.findScienceProjectList(userScienceProjectDto.getUserId()));
        }catch (Exception e){
            info.newFalse(e);
            log.error("",e);
        }
        return info;
    }

    @PostMapping(value = "/user/deleteScienceProject.json",name = "个人信息-删除科研项目")
    public CPViewResultInfo deleteScienceProject(CPViewResultInfo info,@RequestBody UserScienceProjectDto scienceProjectDto){
        try{
            this.userAppService.deleteScienceProject(scienceProjectDto.getId());
            info.newSuccess(null);
        }catch (Exception e){
            info.newFalse(e);
            log.error("个人信息-删除科研项目",e);
        }
        return info;
    }

    /**
     * @Method:  saveOrUpdateScientificInvention
     * @Author: Away
     * @Version: v1.0
     * @See: 个人信息-新增或修改科技发明
     * @Param: info
     * @Param: scientificInventionDto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/1 9:48
     */
    @PostMapping(value = "/user/saveOrUpdateScientificInvention.json",name = "个人信息-新增或修改科技发明")
    public CPViewResultInfo saveOrUpdateScientificInvention(CPViewResultInfo info,@RequestBody UserScientificInventionDto scientificInventionDto){
        try{
            info.newSuccess(this.userAppService.saveOrUpdateScientificInvention(scientificInventionDto));
        }catch (Exception e){
            info.newFalse(e);
            log.error("",e);
        }
        return info;
    }

    /**
     * @Method:  getScientificInventionList
     * @Author: Away
     * @Version: v1.0
     * @See: 个人信息-获取科技发明列表
     * @Param: info
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/1 9:49
     */
    @GetMapping(value = "/user/getScientificInventionList.json",name = "个人信息-获取科技发明列表")
    public CPViewResultInfo getScientificInventionList(CPViewResultInfo info,UserScientificInventionDto scientificInventionDto){
        try{
            info.newSuccess(this.userAppService.findScientificInventionList(scientificInventionDto.getUserId()));
        }catch (Exception e){
            info.newFalse(e);
            log.error("",e);
        }
        return info;
    }

    @PostMapping(value = "/user/deleteScientificInvention.json",name = "个人信息-删除科技发明")
    public CPViewResultInfo deleteScientificInvention(CPViewResultInfo info,@RequestBody UserScientificInventionDto scientificInventionDto){
        try{
            this.userAppService.deleteScientificInvention(scientificInventionDto.getId());
            info.newSuccess(null);
        }catch (Exception e){
            info.newFalse(e);
            log.error("个人信息-删除科技发明",e);
        }
        return info;
    }

    /**
     * @Method:  saveOrUpdateStandard
     * @Author: Away
     * @Version: v1.0
     * @See: 新增或修改标准制定
     * @Param: info
     * @Param: userStandardDto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/1 9:52
     */
    @PostMapping(value = "/user/saveOrUpdateStandard.json",name = "个人信息-新增或修改标准制定")
    public CPViewResultInfo saveOrUpdateStandard(CPViewResultInfo info,@RequestBody UserStandardDto userStandardDto){
        try{
            info.newSuccess(this.userAppService.saveOrUpdateUserStandard(userStandardDto));
        }catch (Exception e){
            info.newFalse(e);
            log.error("",e);
        }
        return info;
    }

    /**
     * @Method:  getStandardList
     * @Author: Away
     * @Version: v1.0
     * @See: 个人信息-获得标准制定列表
     * @Param: info
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/1 9:54
     */
    @GetMapping(value = "/user/getStandardList.json",name = "个人信息-获得标准制定列表")
    public CPViewResultInfo getStandardList(CPViewResultInfo info,UserStandardDto userStandardDto){
        try{
            info.newSuccess(this.userAppService.findUserStandardList(userStandardDto.getUserId()));
        }catch (Exception e){
            info.newFalse(e);
            log.error("",e);
        }
        return info;
    }

    @PostMapping(value = "/user/deleteStandard.json",name = "个人信息-删除标准制定")
    public CPViewResultInfo deleteStandard(CPViewResultInfo info,@RequestBody UserStandardDto userStandardDto){
        try{
            this.userAppService.deleteUserStandard(userStandardDto.getId());
            info.newSuccess(null);
        }catch (Exception e){
            info.newFalse(e);
            log.error("个人信息-删除标准制定",e);
        }
        return info;
    }

    /**
     * @Method:  saveOrUpdateThesis
     * @Author: Away
     * @Version: v1.0
     * @See: 个人信息-新增或修改用户论文
     * @Param: info
     * @Param: userThesisDto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/1 9:58
     */
    @PostMapping(value = "/user/saveOrUpdateThesis.json",name = "个人信息-新增或修改用户论文")
    public CPViewResultInfo saveOrUpdateThesis(CPViewResultInfo info,@RequestBody UserThesisDto userThesisDto){
        try{
            info.newSuccess(this.userAppService.saveOrUpdateUserThesis(userThesisDto));
        }catch (Exception e){
            info.newFalse(e);
            log.error("",e);
        }
        return info;
    }

    /**
     * @Method:  getThesisList
     * @Author: Away
     * @Version: v1.0
     * @See: 个人信息-获取用户论文列表
     * @Param: info
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/1 10:01
     */
    @GetMapping(value = "/user/getThesisList.json",name = "个人信息-获取用户论文列表")
    public CPViewResultInfo getThesisList(CPViewResultInfo info,UserThesisDto thesisDto){
        try{
            info.newSuccess(this.userAppService.findUserThesisList(thesisDto.getUserId()));
        }catch (Exception e){
            info.newFalse(e);
            log.error("",e);
        }
        return info;
    }

    @PostMapping(value = "/user/deleteThesis.json",name = "个人信息-删除用户论文")
    public CPViewResultInfo deleteThesis(CPViewResultInfo info,@RequestBody UserThesisDto thesisDto){
        try{
            this.userAppService.deleteUserThesis(thesisDto.getId());
            info.newSuccess(null);
        }catch (Exception e){
            info.newFalse(e);
            log.error("个人信息-删除用户论文",e);
        }
        return info;
    }

    /**
     * @Method:  saveOrUpdateWorkExperience
     * @Author: Away
     * @Version: v1.0
     * @See:  个人信息-新增和修改工作经历
     * @Param: info
     * @Param: workExperienceDto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/1 10:06
     */
    @PostMapping(value = "/user/saveOrUpdateWorkExperience.json",name = "个人信息-新增和修改工作经历")
    public CPViewResultInfo saveOrUpdateWorkExperience(CPViewResultInfo info,@RequestBody UserWorkExperienceDto workExperienceDto){
        try{
            info.newSuccess(this.userAppService.saveOrUpdateUserWorkExperience(workExperienceDto));
        }catch (Exception e){
            info.newFalse(e);
            log.error("",e);
        }
        return info;
    }

    /**
     * @Method:  getWorkExperienceList
     * @Author: Away
     * @Version: v1.0
     * @See: 个人信息-获取工作经历列表
     * @Param: info
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/1 10:10
     */
    @GetMapping(value = "/user/getWorkExperienceList.json",name = "个人信息-获取工作经历列表")
    public CPViewResultInfo getWorkExperienceList(CPViewResultInfo info,UserWorkExperienceDto workExperienceDto){
        try{
            info.newSuccess(this.userAppService.findUserWorkExperinceList(workExperienceDto.getUserId()));
        }catch (Exception e){
            info.newFalse(e);
            log.error("",e);
        }
        return info;
    }

    @PostMapping(value = "/user/deleteWorkExperience.json",name = "个人信息-删除工作经历")
    public CPViewResultInfo deleteWorkExperience(CPViewResultInfo info,@RequestBody UserWorkExperienceDto workExperienceDto){
        try{
            this.userAppService.deleteUserWorkExperience(workExperienceDto.getId());
            info.newSuccess(null);
        }catch (Exception e){
            info.newFalse(e);
            log.error("个人信息-删除工作经历",e);
        }
        return info;
    }

    /**
     * @Method:  saveOrUpdateUserVerify
     * @Author: Away
     * @Version: v1.0
     * @See: 新增或修改人才库审核结果
     * @Param: info
     * @Param: userVerifyDto
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/5 15:03
     */
    @PostMapping(value = "/user/saveOrUpdateUserVerify.json",name = "人才库-新增或修改审核结果")
    public CPViewResultInfo saveOrUpdateUserVerify(CPViewResultInfo info,@RequestBody UserVerifyDto userVerifyDto){
        try{
            info.newSuccess(this.userAppService.saveOrUpdateUserVerify(userVerifyDto));
        }catch (Exception e){
            info.newFalse(e);
            log.error("人才库-新增或修改审核结果",e);
        }
        return info;
    }

    /**
     * @Method:  getPageAllUserInfo
     * @Author: Away
     * @Version: v1.0
     * @See: 用户管理-获得所有用户分页数据
     * @Param: info
     * @Param: pageRequest
     * @Param: conditions
     * @Return: com.laman.web.common.dto.CPViewResultInfo
     * @Date: 2018/6/4 17:22
     */
    @PostMapping(value = "/user/getUserInfoPage.json",name = "用户管理-获得所有用户分页数据")
    public CPViewResultInfo getPageAllUserInfo(CPViewResultInfo info,@RequestBody UserInfoDto conditions){
        try{
            info.newSuccess(this.userInfoAppService.findPageByConditions(new PageRequest(conditions.getPage(),conditions.getSize()), conditions));
        }catch (Exception e){
            info.newFalse(e);
            log.error("用户管理-获得所有用户分页数据",e);
        }
        return info;
    }

}
