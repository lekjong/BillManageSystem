package com.lkq.billmanagesystem.component;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @func   shiorFactoryBean.setUnauthorizedUrl设置不起作用，自定义异常处理器捕获UnauthorizedException
 *
 *
 */
public class MyUnauthorizedExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
      //捕获没有权限的异常，前往指定的页面
      if(e instanceof UnauthorizedException){
          ModelAndView mv = new ModelAndView();
          mv.setViewName("main/unauthorized");
         return mv;
      }
      //其他异常由各自指定的异常解析器处理，这里不做处理；
        return null;
    }


}
