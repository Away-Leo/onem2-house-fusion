package com.laman.biz.user.domain.repository;

import com.laman.biz.user.app.dto.UserDto;
import com.laman.biz.user.domain.entity.User;
import com.laman.fusion.base.base.BaseRepository;
import com.laman.fusion.base.util.ObjectHelper;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Away
 * @version V1.0
 * @Title: SeUserRepository.java
 * @Description: 用户操作库
 * @date 2018/2/7 13:52
 * @copyright 重庆拉曼科技有限公司
 */
public interface UserRepository extends BaseRepository<User, Long> {

    /**
     * @Author: Away
     * @Title: findByUserNameAndPasswordAndTypeNot
     * @Description: 按照用户名，密码，类型查找
     * @Param: userName 账号
     * @Param: passWord 密码
     * @Param: type 用户类型
     * @Return: com.laman.biz.user.domain.entity.User
     * @Date: 2018/4/12 11:13
     * @Version: 2018/4/12 11:13
     */
    public User findByUserNameAndPasswordAndTypeNot(String userName,String passWord,String type);

    /**
     * @Author: Away
     * @Title: findManageUserPageByCondition
     * @Description: 按照条件查找管理员分页数据
     * @Param: pageable 分页参数
     * @Param: condition 查询条件
     * @Return: org.springframework.data.domain.Page<com.laman.biz.user.domain.entity.User>
     * @Date: 2018/4/12 11:14
     * @Version: 2018/4/12 11:14
     */
    public default Page<User> findManageUserPageByCondition(Pageable pageable, UserDto condition){
        StringBuffer hql=new StringBuffer(" from User u where u.type != :defaultType ");
        Map<String,Object> qry=new HashMap<>();
        qry.put("defaultType","user");
        if(ObjectHelper.isNotEmpty(condition)){
            if(ObjectHelper.isNotEmpty(condition.getUserName())){
                hql.append(" and u.username like :username ");
                qry.put("username","%"+ StringEscapeUtils.escapeSql(condition.getUserName())+"%");
            }
            if(ObjectHelper.isNotEmpty(condition.getType())){
                hql.append(" and u.type = :type ");
                qry.put("type",condition.getType());
            }
        }
        return this.findByHqlPage(pageable,hql.toString(),qry);
    }

    /**
     * @Author: Away
     * @Title: findByUserNameAndDeletedFalse
     * @Description: 按照用户账号查找
     * @Param: userName 用户账号
     * @Return: com.laman.biz.user.domain.entity.User
     * @Date: 2018/4/12 11:17
     * @Version: 2018/4/12 11:17
     */
    public User findByUserNameAndDeletedFalse(String userName);
}
