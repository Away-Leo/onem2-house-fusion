package com.onem2.biz.menu.domain.repositry;


import com.onem2.biz.menu.app.dto.SysUrlsDto;
import com.onem2.biz.menu.domain.entity.SysUrls;
import com.onem2.fusion.base.base.BaseRepository;
import com.onem2.fusion.base.util.ObjectHelper;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.Map;

/**
* @Title: SysUrlsRepository.java
* @Description: 系统连接操作库
* @author Away
* @date 2018/2/7 18:25
* @copyright 重庆壹平方米网络科技有限公司
* @version V1.0
*/
public interface SysUrlsRepository extends BaseRepository<SysUrls,Long> {


    public default Page<SysUrlsDto> findByConditions(Pageable pageable, SysUrlsDto params){
        StringBuffer hql=new StringBuffer(" from SysUrls u where 1=1");
        Map<String,Object> conditions=new HashMap<>();
        if(ObjectHelper.isNotEmpty(params)){
            if(ObjectHelper.isNotEmpty(params.getUrlName())){
                hql.append(" and u.urlName like :urlName ");
                conditions.put("urlName","%"+ StringEscapeUtils.escapeSql(params.getUrlName())+"%");
            }
            if(ObjectHelper.isNotEmpty(params.getUrl())){
                hql.append(" and u.url like :url ");
                conditions.put("url","%"+params.getUrl()+"%");
            }
        }
        hql.append(" order by u.rawAddTime desc ");
//        return this.findByHqlPage(pageable,hql.toString(),conditions);
        return null;
    }
}
