package com.laman.biz.user.domain.service;

import com.laman.biz.user.app.dto.UserLearningExperienceDto;
import com.laman.biz.user.domain.entity.UserLearningExperience;
import com.laman.biz.user.domain.repository.UserLearningExperienceRepository;
import com.laman.fusion.base.base.BaseDomainService;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Title: UserFileDomainService
* @Description:  用户学习经历
* @Author: Away
* @Date: 2018/5/31 15:44
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
public class UserLearningExperienceDomainService extends BaseDomainService<UserLearningExperienceRepository,UserLearningExperience,UserLearningExperienceDto> {

    /**
     * @Method:  findByUserId
     * @Author: Away
     * @Version: v1.0
     * @See: 按照用户ID查找
     * @Param: userId
     * @Return: java.util.List<com.laman.biz.user.app.dto.UserLearningExperienceDto>
     * @Date: 2018/6/4 13:47
     */
    public List<UserLearningExperienceDto> findByUserId(Long userId) throws BusinessException{
        if(ObjectHelper.isNotEmpty(userId)){
            return toDtoList(CT.findByUserId(userId),UserLearningExperienceDto.class);
        }else {
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }
}
