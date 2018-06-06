package com.laman.biz.user.app.service;

import com.laman.biz.user.app.dto.*;
import com.laman.biz.user.domain.entity.*;
import com.laman.biz.user.domain.service.*;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import com.laman.fusion.base.util.ObjectProperUtil;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Title: UserAppService.java
 * @Description: 用户服务
 * @Author: Away
 * @Date: 2018/4/12 13:53
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@Service
@Transactional
public class UserAppService {

    private final UserDomainService userDomainService;

    private final UserInfoAppService userInfoAppService;

    private final UserAbroadActDomainService userAbroadActDomainService;

    private final UserFileDomainService userFileDomainService;

    private final UserInspectionWorkDomainService userInspectionWorkDomainService;

    private final UserLearningExperienceDomainService userLearningExperienceDomainService;

    private final UserRewardSituationDomainService userRewardSituationDomainService;

    private final UserScienceProjectDomainService userScienceProjectDomainService;

    private final UserScientificInventionDomainService userScientificInventionDomainService;

    private final UserStandardDomainService userStandardDomainService;

    private final UserThesisDomainService userThesisDomainService;

    private final UserWorkExperienceDomainService userWorkExperienceDomainService;

    private final UserVerifyDomainService userVerifyDomainService;


    @Autowired
    public UserAppService(UserDomainService userDomainService, UserInfoAppService userInfoAppService, UserWorkExperienceDomainService userWorkExperienceDomainService, UserFileDomainService userFileDomainService, UserInspectionWorkDomainService userInspectionWorkDomainService, UserLearningExperienceDomainService userLearningExperienceDomainService, UserRewardSituationDomainService userRewardSituationDomainService, UserScienceProjectDomainService userScienceProjectDomainService, UserScientificInventionDomainService userScientificInventionDomainService, UserStandardDomainService userStandardDomainService, UserThesisDomainService userThesisDomainService, UserAbroadActDomainService userAbroadActDomainService, UserVerifyDomainService userVerifyDomainService) {
        this.userDomainService = userDomainService;
        this.userInfoAppService = userInfoAppService;
        this.userWorkExperienceDomainService = userWorkExperienceDomainService;
        this.userFileDomainService = userFileDomainService;
        this.userInspectionWorkDomainService = userInspectionWorkDomainService;
        this.userLearningExperienceDomainService = userLearningExperienceDomainService;
        this.userRewardSituationDomainService = userRewardSituationDomainService;
        this.userScienceProjectDomainService = userScienceProjectDomainService;
        this.userScientificInventionDomainService = userScientificInventionDomainService;
        this.userStandardDomainService = userStandardDomainService;
        this.userThesisDomainService = userThesisDomainService;
        this.userAbroadActDomainService = userAbroadActDomainService;
        this.userVerifyDomainService = userVerifyDomainService;
    }


    /**
     * @Author: Away
     * @Description: 按照条件查找
     * @Param: pageable
     * @Param: conditions
     * @Return org.springframework.data.domain.Page<com.laman.biz.user.app.dto.UserDto>
     * @Date 2018/3/1 14:54
     * @Copyright 重庆拉曼科技有限公司
     */
    public Page<UserDto> findManageUserPage(Pageable pageable, UserDto conditions) {
        return this.userDomainService.findManageUserPageByCondition(pageable, conditions);
    }

    /**
     * @Author: Away
     * @Title: findByUserName
     * @Description: 按照注册帐号查找
     * @Param: userName 注册帐号
     * @Return: java.util.List<com.laman.biz.user.app.dto.UserDto>
     * @Date: 2018/4/12 15:01
     * @Version: 2018/4/12 15:01
     */
    public UserDto findByUserName(String userName) throws BusinessException {
        return this.userDomainService.findByUserName(userName);
    }

    /**
     * @Method:  findUserInfoByUserId
     * @Author: Away
     * @Version: v1.0
     * @See: 按照用户ID查找用户基本信息
     * @Param: id
     * @Return: com.laman.biz.user.app.dto.UserInfoDto
     * @Date: 2018/6/3 17:08
     */
    public UserInfoDto findUserInfoByUserId(Long id) throws BusinessException {
        return this.userInfoAppService.findByUserId(id);
    }

    /**
     * @Method:  findCurrentUserInfo
     * @Author: Away
     * @Version: v1.0
     * @See: 查找当前登录用户的基本信息
     * @Param:
     * @Return: com.laman.biz.user.app.dto.UserInfoDto
     * @Date: 2018/6/1 14:32
     */
    public UserInfoDto findCurrentUserInfo() throws Exception{
        return this.userInfoAppService.findCurrentUserInfo();
    }

    /**
     * @Author: Away
     * @Title: userRegister
     * @Description: 用户注册
     * @Param: toRegisterUser 待注册用户数据
     * @Return: com.laman.biz.user.app.dto.UserDto
     * @Date: 2018/4/12 14:59
     * @Version: 2018/4/12 14:59
     */
    public UserDto userRegister(LoginModel toRegisterUser) throws Exception {
        if (ObjectHelper.isNotEmpty(toRegisterUser)) {
            UserDto sameNameUser = this.userDomainService.findByUserName(toRegisterUser.getUserName());
            if (ObjectHelper.isEmpty(sameNameUser)) {
                //密码掩码器
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                toRegisterUser.setPassword(bCryptPasswordEncoder.encode(toRegisterUser.getPassword()));
                //新建账号
                UserDto createdUser = this.userDomainService.createUser(ObjectProperUtil.compareAndValue(toRegisterUser,new UserDto(),true,null));
                //新建用户信息数据
                createdUser.setEmail(toRegisterUser.getEmailAddress());//赋值邮件地址
                this.userInfoAppService.creatNewUserInfo(createdUser);
                return createdUser;
            } else {
                throw new BusinessException(ENUM_EXCEPTION.E10010.code, ENUM_EXCEPTION.E10010.msg);
            }
        } else {
            throw new BusinessException(ENUM_EXCEPTION.E10001.code, ENUM_EXCEPTION.E10001.msg);
        }
    }

    /**
     * @Method:  saveOrUpdateUserInfo
     * @Author: Away
     * @Version: v1.0
     * @See: 新增或修改用户信息
     * @Param: userInfoDto
     * @Return: com.laman.biz.user.app.dto.UserInfoDto
     * @Date: 2018/6/1 14:19
     */
    public UserInfoDto saveOrUpdateUserInfo(UserInfoDto userInfoDto) throws Exception{
        return this.userInfoAppService.saveOrUpdateUserInfo(userInfoDto);
    }

    /**
     * @Method:  saveOrUpdateUserAbroadAct
     * @Author: Away
     * @Version: v1.0
     * @See: 境外活动
     * @Param: dto
     * @Return: com.laman.biz.user.app.dto.UserAbroadActDto
     * @Date: 2018/5/31 16:26
     */
    public UserAbroadActDto saveOrUpdateUserAbroadAct(UserAbroadActDto dto) throws Exception{
        return this.userAbroadActDomainService.saveOrUpdateData(dto,UserAbroadAct.class);
    }

    public List<UserAbroadActDto> findUserAbroadActList(Long userId){
        return this.userAbroadActDomainService.findListByUserId(userId);
    }

    public void deleteUserAbroadAct(Long id){
        this.userAbroadActDomainService.physicalDelete(id);
    }

    /**
     * @Method:  saveOrUpdateUserFile
     * @Author: Away
     * @Version: v1.0
     * @See: 用户文件
     * @Param: dto
     * @Return: com.laman.biz.user.app.dto.UserFileDto
     * @Date: 2018/5/31 16:49
     */
    public UserFileDto saveOrUpdateUserFile(UserFileDto dto) throws Exception{
        return this.userFileDomainService.saveOrUpdateData(dto, UserFile.class);
    }

    public List<UserFileDto> findUserFileList(Long userId){
        return this.userFileDomainService.findByUserId(userId);
    }

    public void deleteUserFile(Long id){
        this.userFileDomainService.physicalDelete(id);
    }

    /**
     * @Method:  saveOrUpdateUserInspection
     * @Author: Away
     * @Version: v1.0
     * @See: 重要工作或检查工作
     * @Param: dto
     * @Return: com.laman.biz.user.app.dto.UserInspectionWorkDto
     * @Date: 2018/5/31 16:59
     */
    public UserInspectionWorkDto saveOrUpdateUserInspection(UserInspectionWorkDto dto) throws Exception{
        return this.userInspectionWorkDomainService.saveOrUpdateData(dto, UserInspectionWork.class);
    }

    public List<UserInspectionWorkDto> findUserInspectionWorkList(Long userId){
        return this.userInspectionWorkDomainService.findByUserId(userId);
    }

    public void deleteUserInspectionWork(Long id){
        this.userInspectionWorkDomainService.physicalDelete(id);
    }

    /**
     * @Method:  saveOrUpdateUserLearning
     * @Author: Away
     * @Version: v1.0
     * @See: 用户学习经历
     * @Param: dto
     * @Return: com.laman.biz.user.app.dto.UserLearningExperienceDto
     * @Date: 2018/5/31 17:03
     */
    public UserLearningExperienceDto saveOrUpdateUserLearning(UserLearningExperienceDto dto) throws Exception{
        return this.userLearningExperienceDomainService.saveOrUpdateData(dto, UserLearningExperience.class);
    }

    public List<UserLearningExperienceDto> findUserLearningList(Long userId){
        return this.userLearningExperienceDomainService.findByUserId(userId);
    }

    public void deleteUserLearning(Long id){
        this.userLearningExperienceDomainService.physicalDelete(id);
    }
    /**
     * @Method:  saveOrUpdateUserRewardSituation
     * @Author: Away
     * @Version: v1.0
     * @See: 用户奖励
     * @Param: dto
     * @Return: com.laman.biz.user.app.dto.UserRewardSituationDto
     * @Date: 2018/5/31 17:07
     */
    public UserRewardSituationDto saveOrUpdateUserRewardSituation(UserRewardSituationDto dto) throws Exception{
        return this.userRewardSituationDomainService.saveOrUpdateData(dto, UserRewardSituation.class);
    }

    public List<UserRewardSituationDto> findUserRewardSituationList(Long userId){
        return this.userRewardSituationDomainService.findByUserId(userId);
    }

    public void deleteUserRewardSituation(Long id){
        this.userRewardSituationDomainService.physicalDelete(id);
    }

    /**
     * @Method:  saveOrUpdateUserScienceProject
     * @Author: Away
     * @Version: v1.0
     * @See: 用户科学项目
     * @Param: dto
     * @Return: com.laman.biz.user.app.dto.UserScienceProjectDto
     * @Date: 2018/5/31 17:12
     */
    public UserScienceProjectDto saveOrUpdateUserScienceProject(UserScienceProjectDto dto) throws Exception{
        return this.userScienceProjectDomainService.saveOrUpdateData(dto,UserScienceProject.class);
    }

    public List<UserScienceProjectDto> findScienceProjectList(Long userId){
        return this.userScienceProjectDomainService.findByUserId(userId);
    }

    public void deleteScienceProject(Long id){
        this.userScienceProjectDomainService.physicalDelete(id);
    }
    /**
     * @Method:  saveOrUpdateScientificInvention
     * @Author: Away
     * @Version: v1.0
     * @See: 用户科学发明
     * @Param: dto
     * @Return: com.laman.biz.user.app.dto.UserScientificInventionDto
     * @Date: 2018/5/31 17:15
     */
    public UserScientificInventionDto saveOrUpdateScientificInvention(UserScientificInventionDto dto) throws Exception{
        return this.userScientificInventionDomainService.saveOrUpdateData(dto,UserScientificInvention.class);
    }

    public List<UserScientificInventionDto> findScientificInventionList(Long userId){
        return this.userScientificInventionDomainService.findByUserId(userId);
    }

    public void deleteScientificInvention(Long id){
        this.userScientificInventionDomainService.physicalDelete(id);
    }

    /**
     * @Method:  saveOrUpdateUserStandard
     * @Author: Away
     * @Version: v1.0
     * @See: 用户标准
     * @Param: dto
     * @Return: com.laman.biz.user.app.dto.UserStandardDto
     * @Date: 2018/5/31 17:31
     */
    public UserStandardDto saveOrUpdateUserStandard(UserStandardDto dto) throws Exception{
        return this.userStandardDomainService.saveOrUpdateData(dto,UserStandard.class);
    }

    public List<UserStandardDto> findUserStandardList(Long userId){
        return this.userStandardDomainService.findByUserId(userId);
    }

    public void deleteUserStandard(Long id){
        this.userStandardDomainService.physicalDelete(id);
    }

    /**
     * @Method:  saveOrUpdateUserThesis
     * @Author: Away
     * @Version: v1.0
     * @See: 用户论文
     * @Param: dto
     * @Return: com.laman.biz.user.app.dto.UserThesisDto
     * @Date: 2018/5/31 17:44
     */
    public UserThesisDto saveOrUpdateUserThesis(UserThesisDto dto) throws Exception{
        return this.userThesisDomainService.saveOrUpdateData(dto,UserThesis.class);
    }

    public List<UserThesisDto> findUserThesisList(Long userId){
        return this.userThesisDomainService.findByUserId(userId);
    }

    public void deleteUserThesis(Long id){
        this.userThesisDomainService.physicalDelete(id);
    }

    /**
     * @Method:  saveOrUpdateUserWorkExperience
     * @Author: Away
     * @Version: v1.0
     * @See: 用户经历
     * @Param: dto
     * @Return: com.laman.biz.user.app.dto.UserWorkExperienceDto
     * @Date: 2018/5/31 17:47
     */
    public UserWorkExperienceDto saveOrUpdateUserWorkExperience(UserWorkExperienceDto dto) throws Exception{
        return this.userWorkExperienceDomainService.saveOrUpdateData(dto,UserWorkExperience.class);
    }

    public List<UserWorkExperienceDto> findUserWorkExperinceList(Long userId) {
        return this.userWorkExperienceDomainService.findAllList(UserWorkExperienceDto.class);
    }

    public void deleteUserWorkExperience(Long id){
        this.userWorkExperienceDomainService.physicalDelete(id);
    }

    /**
     * @Method:  saveOrUpdateUserVerify
     * @Author: Away
     * @Version: v1.0
     * @See: 新增或修改用户审核
     * @Param: dto
     * @Return: com.laman.biz.user.app.dto.UserVerifyDto
     * @Date: 2018/6/5 15:00
     */
    public UserVerifyDto saveOrUpdateUserVerify(UserVerifyDto dto) throws Exception{
        return this.userVerifyDomainService.saveOrUpdateData(dto,UserVerify.class);
    }
}
