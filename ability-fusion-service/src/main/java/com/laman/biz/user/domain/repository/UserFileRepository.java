package com.laman.biz.user.domain.repository;

import com.laman.biz.user.domain.entity.UserFile;
import com.laman.fusion.base.base.BaseRepository;

import java.util.List;

/**
* @Title: UserFileRepository
* @Description:  文件自定义操作库
* @Author: Away
* @Date: 2018/5/31 15:26
* @Copyright: 重庆拉曼科技有限公司
* @Version: V1.0
*/
public interface UserFileRepository extends BaseRepository<UserFile, Long> {

    /**
     * @Method:  findByUserId
     * @Author: Away
     * @Version: v1.0
     * @See: 通过用户ID查找
     * @Param: userId
     * @Return: java.util.List<com.laman.biz.user.domain.entity.UserFile>
     * @Date: 2018/6/4 12:03
     */
    List<UserFile> findByUserId(Long userId);
}
