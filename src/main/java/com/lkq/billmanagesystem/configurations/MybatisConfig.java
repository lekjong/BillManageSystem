package com.lkq.billmanagesystem.configurations;


import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @arthur: lkq
 * @func: 用于自定义一些mybatis的配置；
 *
 */

@Configuration
public class MybatisConfig {

//定义数据库表的字段与实体类名的驼峰与下划线格式的映射,也可在配置文件中指定属性。
  @Bean
  public ConfigurationCustomizer  addConfigurationCustomizer(){

     return  new ConfigurationCustomizer() {
         @Override
         public void customize(org.apache.ibatis.session.Configuration configuration) {
             configuration.setMapUnderscoreToCamelCase(true);
         }
     };

  }


}
