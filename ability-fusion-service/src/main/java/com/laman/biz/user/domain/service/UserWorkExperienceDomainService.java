package com.laman.biz.user.domain.service;

import com.laman.biz.user.app.dto.UserWorkExperienceDto;
import com.laman.biz.user.domain.entity.UserWorkExperience;
import com.laman.biz.user.domain.repository.UserWorkExperienceRepository;
import com.laman.fusion.base.base.BaseDomainService;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Title: UserFileDomainService
* @Description:  用户工作经历
* @Author: Away
* @Date: 2018/5/31 15:44
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
public class UserWorkExperienceDomainService extends BaseDomainService<UserWorkExperienceRepository,UserWorkExperience,UserWorkExperienceDto> {

    /**
     * @Method:  findByUserId
     * @Author: Away
     * @Version: v1.0
     * @See: 根据用户ID查找
     * @Param: userId
     * @Return: java.util.List<com.laman.biz.user.app.dto.UserWorkExperienceDto>
     * @Date: 2018/6/4 14:38
     */
    public List<UserWorkExperienceDto> findByUserId(Long userId) throws BusinessException{
        if(ObjectHelper.isNotEmpty(userId)){
            return toDtoList(CT.findByUserId(userId),UserWorkExperienceDto.class);
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }
}
