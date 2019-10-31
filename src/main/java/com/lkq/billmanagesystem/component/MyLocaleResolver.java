package com.lkq.billmanagesystem.component;


import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;


/**
 * class: 自定义的区域信息解析器。如果请求中给定了Locale参数，则用请求给定的，否则使用系统默认的Locale
 *
 *
 */

public class MyLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {

        Locale locale;

        //获取请求头传入的scale参数信息，为String格式；exp: zh_CN
        String locale_Str = httpServletRequest.getParameter("lc");

        if (!StringUtils.isEmpty(locale_Str)){

            String[]  ls = locale_Str.split("_");

            locale = new Locale(ls[0],ls[1]);

        }

           else  locale = httpServletRequest.getLocale(); //获取浏览器指定区域信息；
    //     else  locale  = Locale.getDefault(); 获取系统默认的区域信息，在lnux上为英文；

         return  locale;

    }



    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
