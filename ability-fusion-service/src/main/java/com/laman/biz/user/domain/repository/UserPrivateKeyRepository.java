package com.laman.biz.user.domain.repository;

import com.laman.biz.user.domain.entity.UserPrivateKey;
import com.laman.fusion.base.base.BaseRepository;

/**
 * @Title: UserPrivateKeyRepository.java
 * @Description: 用户私钥表操作库
 * @Author: Away
 * @Date: 2018/4/18 15:47
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
public interface UserPrivateKeyRepository extends BaseRepository<UserPrivateKey,Long>{

    /**
     * @Author: Away
     * @Title: findByUserIdAndPlatformCode
     * @Description: 按照用户ID和平台编号查找
     * @Param: userId
     * @Param: platformCode
     * @Return: com.laman.biz.user.domain.entity.UserPrivateKey
     * @Date: 2018/4/18 15:51
     * @Version: 2018/4/18 15:51
     */
    public UserPrivateKey findByUserIdAndPlatformCode(Long userId,String platformCode);

}
