package com.laman.biz.user.domain.service;

import com.laman.biz.user.app.dto.UserVerifyDto;
import com.laman.biz.user.domain.entity.UserVerify;
import com.laman.biz.user.domain.repository.UserVerifyRepository;
import com.laman.fusion.base.base.BaseDomainService;
import org.springframework.stereotype.Service;

/**
* @Title: UserVerifyDomainService
* @Description:  用户审核
* @Author: Away
* @Date: 2018/6/5 14:55
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
@Service
public class UserVerifyDomainService extends BaseDomainService<UserVerifyRepository, UserVerify, UserVerifyDto> {

}
