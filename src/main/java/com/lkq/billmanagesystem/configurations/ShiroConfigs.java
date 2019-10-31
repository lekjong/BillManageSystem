package com.lkq.billmanagesystem.configurations;

import com.lkq.billmanagesystem.realms.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.LinkedHashMap;

/**
 * @Intrud: 该类配置了shiro运行时需要的各个组件，包括ShiroFilterFactoryBean,SecurityManager,Realm。
 *
 *
 *
 */

@Configuration
public class ShiroConfigs {

    /**
     * 配置凭证匹配器，其需要注入到realm中；
     * @return
     */

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean(name = "credentialMatcher")
    public HashedCredentialsMatcher  getCredentialMatcher(){

        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置md5加密算法,指明迭代次数；
        credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(2);

        return  credentialsMatcher;
    }


    //在realm中指定自定义的credentialMatcher组件，并在类中传入迭代所需要的盐值；
    @Bean
    public MyRealm myRealm(@Qualifier("credentialMatcher")HashedCredentialsMatcher credentialsMatcher){

        MyRealm  myRealm = new MyRealm();

        myRealm.setCredentialsMatcher(credentialsMatcher);

        return myRealm;
    }




    /**
     * @func 配置securityManager需关联realm, cacheManager, sessionManager, cookieRememberMemanager组件；
     * @param ehCacheManager:添加authorizationInfo的缓存管理，否则每次访问资源都会从数据库获取权限，效率很低；
     *
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager  getSecurityManager(@Qualifier("myRealm")MyRealm myRealm,
                                                         @Qualifier("ehcacheManager")EhCacheManager ehCacheManager,
                                                         @Qualifier("sessionManager")DefaultWebSessionManager sessionManager,
                                                         @Qualifier("rememberMeManager")CookieRememberMeManager rememberMeManager
    ){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //添加各个组件；
        securityManager.setRealm(myRealm);
        securityManager.setCacheManager(ehCacheManager);
        securityManager.setSessionManager(sessionManager);
        securityManager.setRememberMeManager(rememberMeManager);


        return  securityManager;
    }


    /**
     * @func 返回shiroFilterFactoryBean,由spring创建，交由servlet容器托管；
     * @param  securityManager,绑定安全管理器到该bean
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(
            @Qualifier("securityManager")DefaultWebSecurityManager securityManager){

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加过滤器链
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        map.put("/toLogin","anon");

        map.put("/login","anon"); //login放行去进行认证，再对/index进行拦截，确认是否通过认证；
        //FormAuthenticationFilter进行处理；


        //不拦截静态资源
        map.put("/css/**","anon");
        map.put("/img/**","anon");
        map.put("/js/**","anon");
        //注销过滤器；
        map.put("/logout","logout");
        //具有rememberMe功能后，被该过滤器处理的请求将能直接访问；
        map.put("/index","user");

        map.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //需要认证的资源去认证时由shiro发出的url请求，前往登陆页面，不配置时默认到 /login.jsp;
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        //shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");


        return  shiroFilterFactoryBean;
    }


    /**
     * @func :启动shiro(如@RequiresRoles,@RequiresPermissions)注解所需要的实现类，需要开启AOP,已在配置文件中设置 ；
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor addAuthAttributeSourceAdvisor(
            @Qualifier("securityManager")SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                                            new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    //创建ehcache组件；
    @Bean("ehcacheManager")
    public EhCacheManager addEhcache(){

        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache/ehcache.xml");
        return ehCacheManager;
    }


    /**
     * @func 添加会话管理组件，管理会话的各个属性；
     *
     * @return
     */
    @Bean("sessionManager")
    public DefaultWebSessionManager addSessionManager(){

        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //设置全局session的有效时间ms；
        sessionManager.setGlobalSessionTimeout(1_800_000);
        //清除无效的会话；
        sessionManager.setDeleteInvalidSessions(true);
        //避免再次登陆时url会携带该JsessionId;
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }


    /**
     * @func: shiro本身自带rememberManager，并设定了cookie的生命周期为1年，这里添加rememberMeManager组件，
     *        该组件将登陆用户信息写入自定义的cookie组件;
     * @param
     * @return
     */
    @Bean("rememberMeManager")
    public CookieRememberMeManager  addRememberMeManager(@Qualifier("rememberMeCookie")SimpleCookie rememberMeCookie
    ){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie);
        return cookieRememberMeManager;
    }


    /**
     * @func:创建cookie存储用户信息；
     *
     * @return
     */
    @Bean("rememberMeCookie")   //cookie类名
    public SimpleCookie addCookie(){
       logger.info("生成rememberMeCookie中*******************************");
        SimpleCookie rememberMeCookie = new SimpleCookie("rememberMe");
        //设置cookie的生命周期（s），1week,
        rememberMeCookie.setMaxAge(604800);
        return  rememberMeCookie;
    }


}
