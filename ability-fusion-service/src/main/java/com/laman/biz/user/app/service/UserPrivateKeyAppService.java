package com.laman.biz.user.app.service;

import com.laman.biz.user.app.dto.UserDto;
import com.laman.biz.user.app.dto.UserPrivateKeyDto;
import com.laman.biz.user.domain.service.UserPrivateKeyDomainService;
import com.zds.common.lang.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Title: UserPrivateKeyDomainService.java
 * @Description: 用户私有密钥数据服务
 * @Author: Away
 * @Date: 2018/4/18 15:55
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@Slf4j
@Service
@Transactional
public class UserPrivateKeyAppService {

    private final UserPrivateKeyDomainService userPrivateKeyDomainService;

    @Autowired
    public UserPrivateKeyAppService(UserPrivateKeyDomainService userPrivateKeyDomainService){
        this.userPrivateKeyDomainService=userPrivateKeyDomainService;
    }

    /**
     * @Author: Away
     * @Title: wirteData
     * @Description: 写入数据（先按照用户ID和平台编码查找，如果无数据则新建，有了则修改生成新的key）
     * @Param: userDto
     * @Return: com.laman.biz.user.app.dto.UserPrivateKeyDto
     * @Date: 2018/4/18 16:07
     * @Version: 2018/4/18 16:07
     */
    @CachePut(value = "userKey",key = "#userDto.platformCode+#userDto.id")
    public UserPrivateKeyDto writeData(UserDto userDto) throws Exception{
        return this.userPrivateKeyDomainService.writeData(userDto);
    }

    /**
     * @Author: Away
     * @Title: findByUserIdAndPlatformCode
     * @Description: 按照用户和平台编码查找密钥
     * @Param: userDto
     * @Return: com.laman.biz.user.app.dto.UserPrivateKeyDto
     * @Date: 2018/4/18 16:38
     * @Version: 2018/4/18 16:38
     */
    @Cacheable(value = "userKey",key = "#userDto.platformCode+#userDto.id")
    public UserPrivateKeyDto findByUserIdAndPlatformCode(UserDto userDto) throws BusinessException{
        log.info("-----------------执行了------------------------------");
        return this.userPrivateKeyDomainService.findByUserIdAndPlatformCode(userDto);
    }

}
