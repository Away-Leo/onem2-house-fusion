package com.onem2;

import com.onem2.biz.menu.app.dto.SysUrlsDto;
import com.onem2.biz.menu.app.service.SysUrlsAppService;
import com.onem2.fusion.base.RequestMappingHandlerConfig;
import com.onem2.fusion.base.base.BaseRepositoryFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Title: Main.java
 * @Description:
 * @Author: Away
 * @Date: 2018/4/10 16:56
 * @Copyright: 重庆壹平方米网络科技有限公司
 * @Version: V1.0
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"com.onem2.biz"},
        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class//指定自己的工厂类
)
@EnableAspectJAutoProxy
public class Main {

    @Autowired
    private RequestMappingHandlerConfig requestMappingHandlerConfig;

    @Autowired
    private SysUrlsAppService sysUrlsAppService;

    public static final Logger logger= LoggerFactory.getLogger(Main.class);

    public static void main(String[] args){
        System.setProperty("onem2.appName","house-fusion");
        SpringApplication.run(Main.class,args);
    }

    /**
     * 扫描URL，如果数据库中不存在，则保存入数据库
     */
    @PostConstruct  //这个注解很重要，可以在每次启动的时候检查是否有URL更新，RequestMappingHandlerMapping只能在controller层用。这里我们放在主类中
    public void detectHandlerMethods(){
        final RequestMappingHandlerMapping requestMappingHandlerMapping = requestMappingHandlerConfig.requestMappingHandlerMapping ();
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        Set<RequestMappingInfo> mappings = map.keySet();
        List<SysUrlsDto> saveData=new ArrayList<>();
        for(RequestMappingInfo info : mappings) {
            String urlName=info.getName();
            String urlparm = info.getPatternsCondition().toString();
            String url = urlparm.substring(1, urlparm.length()-1);
            saveData.add(new SysUrlsDto(url,urlName));
        }
        try {
            int save=this.sysUrlsAppService.batchSaveSysUrlsBeforeDeleteAll(saveData);
            logger.info("已保存URL>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+save+"条");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("写入链接数据出错",e);
        }
    }

}
