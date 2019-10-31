package com.lkq.billmanagesystem.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @func 添加拦截器，将未授权的处理器请求拦截，用户必须先登录。
 *
 *
 */
public class MyIntercepter implements HandlerInterceptor {

    //进行登陆的拦截与控制
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //根据请求查找session是否为空，为空则证明未验证通过，不予以放行，返回登陆界面。否则的话可以放行。

        Object loginUser = request.getSession().getAttribute("loginUser");

        if(loginUser!=null && loginUser != ""){

           return  true;

        }
        else  request.setAttribute("msg","没有权限，请先登录！");

        request.getRequestDispatcher("/index.html").forward(request,response);

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
