package com.laman.biz.user.domain.repository;

import com.laman.biz.user.app.dto.UserInfoDto;
import com.laman.biz.user.domain.entity.UserInfo;
import com.laman.fusion.base.base.BaseRepository;
import com.laman.fusion.base.util.ObjectHelper;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: UserInfoRepository.java
 * @Description: 用户基本信息操作库
 * @Author: Away
 * @Date: 2018/4/12 14:35
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
public interface UserInfoRepository extends BaseRepository<UserInfo,Long> {

    /**
     * @Method:  findByUserId
     * @Author: Away
     * @Version: v1.0
     * @See: 根据用户ID查找
     * @Param: userId
     * @Return: com.laman.biz.user.domain.entity.UserInfo
     * @Date: 2018/6/4 16:55
     */
    UserInfo findByUserId(Long userId);

    /**
     * @Method:  findPageByConditins
     * @Author: Away
     * @Version: v1.0
     * @See: 按照条件查找分页数据
     * @Param: pageRequest
     * @Param: conditions
     * @Return: org.springframework.data.domain.Page<com.laman.biz.user.domain.entity.UserInfo>
     * @Date: 2018/6/4 17:05
     */
    default Page<UserInfo> findPageByConditins(PageRequest pageRequest, UserInfoDto conditions){
        StringBuilder hql=new StringBuilder(" from UserInfo u where 1=1 ");
        Map<String,Object> params=new HashMap<>();
        if(ObjectHelper.isNotEmpty(conditions)){
            if(ObjectHelper.isNotEmpty(conditions.getPhone())){
                hql.append(" and u.phone like :phone ");
                params.put("phone","%"+ StringEscapeUtils.escapeSql(conditions.getPhone())+"%");
            }
            if(ObjectHelper.isNotEmpty(conditions.getName())){
                hql.append(" and u.name like :name ");
                params.put("name","%"+ StringEscapeUtils.escapeSql(conditions.getName())+"%");
            }
        }
        hql.append(" order by u.rawAddTime desc ");
        return this.findByHqlPage(pageRequest,hql.toString(),params);
    }
}
