package com.onem2.biz.user.domain.service;

import com.onem2.biz.user.app.dto.UserDto;
import com.onem2.biz.user.app.dto.UserInfoDto;
import com.onem2.biz.user.domain.entity.UserInfo;
import com.onem2.biz.user.domain.repository.UserInfoRepository;
import com.onem2.fusion.base.base.BaseDomainService;
import com.onem2.fusion.base.enums.ENUM_EXCEPTION;
import com.onem2.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title: UserInfoDomainService.java
 * @Description: 用户信息app服务
 * @Author: Away
 * @Date: 2018/4/12 14:25
 * @Copyright: 重庆壹平方米网络科技有限公司
 * @Version: V1.0
 */
@Service
public class UserInfoDomainService extends BaseDomainService<UserInfoRepository,UserInfo,UserInfoDto>{

    private final UserInfoRepository repository;

    @Autowired
    public UserInfoDomainService(UserInfoRepository repository) {
        this.repository = repository;
    }

    /**
     * 新增客户信息
     * @param UserInfoDto
     * @return
     */
    private UserInfo create(UserInfoDto UserInfoDto){
        UserInfo UserInfo = new UserInfo();
        BeanUtils.copyProperties(UserInfoDto,UserInfo);
        return repository.save(UserInfo);
    }

    /**
     * @Author: Away
     * @Title: creatNewUserInfo
     * @Description: 根据用户注册信息新建用户信息
     * @Param: userDto 用户注册信息
     * @Return: com.onem2.biz.user.app.dto.UserInfoDto
     * @Date: 2018/4/12 14:42
     * @Version: 2018/4/12 14:42
     */
    public UserInfoDto creatNewUserInfo(UserDto userDto) throws Exception{
        if(ObjectHelper.isNotEmpty(userDto)){
            UserInfoDto userInfoDto=new UserInfoDto();
            userInfoDto.setUserId(userDto.getId());
            return this.saveOrUpdateData(userInfoDto,UserInfo.class);
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

}
