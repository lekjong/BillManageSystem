package com.lkq.billmanagesystem.configurations;



/*
 * @arthur:lkq
 * @func:配置druid的后台管理系统及数据源
 *  1.数据源的配置，将指定的参数绑定到数据源上；
 *  2.配置druid管理servlet;
 *  3.配置对应的filter拦截器；
 *
 */

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    private Map<String, String> finitMap;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource  druid(){

       return new DruidDataSource();

    }

    @Bean
    public ServletRegistrationBean  druidServlet(){

        //druid的指定servlet及其访问url;
        ServletRegistrationBean<StatViewServlet> servletServletRegistrationBean =
                new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        Map<String,String>  initMap  = new HashMap<>();
        initMap.put(StatViewServlet.PARAM_NAME_USERNAME,"root");
        initMap.put(StatViewServlet.PARAM_NAME_PASSWORD,"root123");
        //设置主机的可/不可访问IP
        initMap.put(StatViewServlet.PARAM_NAME_ALLOW,"");
        initMap.put(StatViewServlet.PARAM_NAME_DENY,"192.168.2.10");

        servletServletRegistrationBean.setInitParameters(initMap);
        return  servletServletRegistrationBean;

    }


    @Bean
    public FilterRegistrationBean  druidFilter(){

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //先拦截所有的请求；
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        //排除对应的请求；
        HashMap<String, String> finitMap = new HashMap<>();

        finitMap.put(WebStatFilter.PARAM_NAME_EXCLUSIONS,"*.css,*.js,/druid/*");

        filterRegistrationBean.setInitParameters(finitMap);

        return filterRegistrationBean;





    }





}
