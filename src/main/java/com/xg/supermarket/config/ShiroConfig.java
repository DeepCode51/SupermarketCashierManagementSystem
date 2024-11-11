package com.xg.supermarket.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.xg.supermarket.shiro.CostomRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 村头老杨头
 * @version 1.0.0
 * @ClassName ShiroConfig.java
 * @Description Shiro配置
 * @createTime 2021年07月01日 08:47:00
 */
@Configuration
public class ShiroConfig {
    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroBean = new ShiroFilterFactoryBean();
        shiroBean.setSecurityManager(securityManager);
        Map<String, String> map = new LinkedHashMap<>();

//      无需认证
        map.put("/doLogin","anon");
        map.put("/code/**","anon");
        //静态资源放行
        map.put("/js/**","anon");
        map.put("/css/**","anon");
        map.put("/fonts/**","anon");
        map.put("/img/**","anon");
//        所有请求必须认证
        map.put("/**","authc");
        shiroBean.setLoginUrl("/login");
        shiroBean.setFilterChainDefinitionMap(map);
        return shiroBean;
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public DefaultWebSecurityManager securityManager(Realm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    /*自定义Realm注入Spring容器*/
    @Bean
    public Realm realm() {
        return new CostomRealm();
    }
    /*配置Shiro的方言：*/
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
