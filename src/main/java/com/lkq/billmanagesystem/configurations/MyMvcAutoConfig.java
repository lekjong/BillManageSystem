package com.lkq.billmanagesystem.configurations;


import com.lkq.billmanagesystem.component.MyIntercepter;
import com.lkq.billmanagesystem.component.MyLocaleResolver;
import com.lkq.billmanagesystem.component.MyUnauthorizedExceptionResolver;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;


/**
 * @func  用于自定义的MVC组件的配置
 *
 *
 *
 *
 */
@Configuration
public class MyMvcAutoConfig {


  //添加webMvcConfig的各种组件到容器
   @Bean
   public WebMvcConfigurer  registryViewController(){

       return new  WebMvcConfigurer(){

           //绑定请求路径到视图的控制映射；
           @Override
           public void addViewControllers(ViewControllerRegistry registry) {
               //视图名自动拼接staticPath+...+.html
              // registry.addViewController("/").setViewName("main/login");
              // registry.addViewController("/login").setViewName("main/login");
               registry.addViewController("/index.html").setViewName("main/index");
               //账单的添加页面映射需要携带供应商信息，不能直接映射
              // registry.addViewController("/addSupplier.html").setViewName("supplier/add");
               registry.addViewController("/addUser.html").setViewName("user/add");
               registry.addViewController("/password.html").setViewName("main/password");
           }


           //拦截请求，没有权限的请求不予以同过,已用过滤器替代；
//           @Override
//           public void addInterceptors(InterceptorRegistry registry) {
//
//               registry.addInterceptor(new MyIntercepter()).addPathPatterns("/**")
//                       .excludePathPatterns("/", "/login","/index")
//                       .excludePathPatterns("/css/*", "/js/*", "/img/*");
//           }

       };


   }



   //配置自定义的LocaleResolver组件到配置文件中
   @Bean   //注意此时组件中的Bean名必须一致，否则无法替换默认的组件！！！
   public LocaleResolver localeResolver(){

       return  new MyLocaleResolver();

   }

  //注册自定义的未授权异常解析器；
   @Bean("userUnanthorizedExceptionResolver")
    public MyUnauthorizedExceptionResolver  addUnauthorizedExceptionResolver(){
       return  new MyUnauthorizedExceptionResolver();
   }



}
