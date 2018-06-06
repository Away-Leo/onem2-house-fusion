package com.laman;

import com.github.tobato.fastdfs.FdfsClientConfig;
import com.laman.fusion.base.base.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * @Title: Main.java
 * @Description:
 * @Author: Away
 * @Date: 2018/4/10 16:56
 * @Copyright: 重庆拉曼科技有限公司
 * @Version: V1.0
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"com.laman"},
        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class//指定自己的工厂类
)
@EnableAspectJAutoProxy
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class Main {

    public static void main(String[] args){
        System.setProperty("laman.appName","ability-fusion");
        SpringApplication.run(Main.class,args);
    }

}
