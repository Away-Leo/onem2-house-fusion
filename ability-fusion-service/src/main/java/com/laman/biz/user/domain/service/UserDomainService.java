package com.laman.biz.user.domain.service;

import com.laman.biz.user.app.dto.UserDto;
import com.laman.biz.user.domain.entity.User;
import com.laman.biz.user.domain.repository.UserRepository;
import com.laman.fusion.base.base.BaseDomainService;
import com.laman.fusion.base.enums.ENUM_EXCEPTION;
import com.laman.fusion.base.util.ObjectHelper;
import com.zds.common.lang.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDomainService extends BaseDomainService<UserRepository, User, UserDto> {

    private final UserRepository userRepository;

    @Autowired
    public UserDomainService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @Author: Away
     * @Description: 按照条件查找
     * @Param: pageable
     * @Param: conditions
     * @Return org.springframework.data.domain.Page<com.laman.biz.user.app.dto.UserDto>
     * @Date 2018/3/1 14:50
     * @Copyright 重庆拉曼科技有限公司
     */
    public Page<UserDto> findManageUserPageByCondition(Pageable pageable, UserDto conditions) {
        Page<User> sourceData = this.userRepository.findManageUserPageByCondition(pageable, conditions);
        List<UserDto> returnListData = new ArrayList<>();
        if (ObjectHelper.isNotEmpty(sourceData.getContent())) {
            for (User temp : sourceData) {
                returnListData.add(temp.to(UserDto.class));
            }
        }
        return new PageImpl<UserDto>(returnListData, pageable, sourceData.getTotalElements());
    }

    /**
     * @Author: Away
     * @Title: findByUserName
     * @Description: 按照用户账号查找
     * @Param: userName 用户账号
     * @Return: java.util.List<com.laman.biz.user.domain.entity.User>
     * @Date: 2018/4/12 11:17
     * @Version: 2018/4/12 11:17
     */
    public UserDto findByUserName(String userName) throws BusinessException {
        if (ObjectHelper.isNotEmpty(userName)) {
            User user=this.userRepository.findByUserNameAndDeletedFalse(userName);
            if(ObjectHelper.isNotEmpty(user)){
                return user.to(UserDto.class);
            }else{
                return null;
            }
        } else {
            throw new BusinessException("E10001", "按照用户名查找用户出错，参数为空");
        }
    }

    /**
     * @Author: Away
     * @Title: createUser
     * @Description: 创建用户
     * @Param: user 用户
     * @Return: com.laman.biz.user.domain.entity.SeUser
     * @Date: 2018/4/12 11:19
     * @Version: 2018/4/12 11:19
     */
    public UserDto createUser(UserDto user) throws Exception {
        if (ObjectHelper.isNotEmpty(user)) {
            //加密密码
//            passwordHelper.encryptPassword(user);
            return this.saveOrUpdateData(user, User.class);
        } else {
            throw new BusinessException(ENUM_EXCEPTION.E10001.code,ENUM_EXCEPTION.E10001.msg);
        }
    }

}
