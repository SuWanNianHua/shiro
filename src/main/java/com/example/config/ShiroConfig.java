package com.example.config;


import com.example.comment.PasswordHelper;
import com.example.comment.RealmShiro;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 张佳奇
 * @Description shiro配置
 * @Date 2020-04-26 16:35
 */
@Configuration
public class ShiroConfig {

    /**
     * 开启aop注解支持 下面这两个必须  如若不加则注解不生效
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        autoProxyCreator.setProxyTargetClass(true);
        return autoProxyCreator;
    }


    /**
     * 改方法用于过滤器配置用户权限的url 若采用注解可以不用
     * @param securityManager shiro的核心组件,可以看做前端控制器,负责和其他的组件进行交互,所有安全有关的操作都和它有关
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String,String> filterChainDefinitionMap=new HashMap<>();
        //如若用户没有登陆就访问了某资源则请求跳转到login请求
        shiroFilterFactoryBean.setLoginUrl("/login");
        //用户没有某个权限则走unauthc请求
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthc");
        //登陆成功后走/home/index请求
        shiroFilterFactoryBean.setSuccessUrl("/home");
        //filterChainDefinitionMap 增加其他过滤器
        return shiroFilterFactoryBean;
    }


    /**
     * 配置加密方式(密码加密)
     * @return 返回加密后的密码
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 加密方式(MD5加密)
        hashedCredentialsMatcher.setHashAlgorithmName(PasswordHelper.ALGORITHE_NAME);
        // 散列次数(2次)
        hashedCredentialsMatcher.setHashIterations(PasswordHelper.HASH_ITERATIONS);
        return hashedCredentialsMatcher;
    }

    /**
     * 配置Realm
     * @return
     */
    @Bean
    public RealmShiro shiroRealm() {
        RealmShiro shiroRealm = new RealmShiro();
        // 调用上面方法设置加密方式
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealm;
    }

    /**
     * 配置安全管理器(核心组件)
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //将角色权限初始化到管理器中
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    @Bean
    public PasswordHelper passwordHelper() {
        return new PasswordHelper();
    }



}
