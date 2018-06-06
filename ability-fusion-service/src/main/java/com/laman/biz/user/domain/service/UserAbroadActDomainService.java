package com.laman.biz.user.domain.service;

import com.laman.biz.user.app.dto.UserAbroadActDto;
import com.laman.biz.user.domain.entity.UserAbroadAct;
import com.laman.biz.user.domain.repository.UserAbroadActRepository;
import com.laman.fusion.base.base.BaseDomainService;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Title: UserFileDomainService
* @Description:  境外活动
* @Author: Away
* @Date: 2018/5/31 15:44
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
public class UserAbroadActDomainService extends BaseDomainService<UserAbroadActRepository,UserAbroadAct,UserAbroadActDto> {

    /**
     * @Method:  findListByUserId
     * @Author: Away
     * @Version: v1.0
     * @See: 根据用户查找
     * @Param: userId
     * @Return: java.util.List<com.laman.biz.user.app.dto.UserAbroadActDto>
     * @Date: 2018/6/4 11:59
     */
    public List<UserAbroadActDto> findListByUserId(Long userId) throws BusinessException{
        if(ObjectHelper.isNotEmpty(userId)){
            return toDtoList(this.CT.findByUserId(userId),UserAbroadActDto.class);
        }else{
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }
}
