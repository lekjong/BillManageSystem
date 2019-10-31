package com.lkq.billmanagesystem.controllers;


import com.lkq.billmanagesystem.entities.User;
import com.lkq.billmanagesystem.services.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @func  对用户的登陆与退出做处理。
 *
 *
 *
 */

@Controller
public class LoginController {

    /**
     * @param httpServletRequest  获取登陆表信息
     * @param map  登陆失败时发送错误信息
     * @return
     */
    @Autowired
    UserService userService;

    //多个请求时必须指定value
    @GetMapping(value = {"/toLogin"})
    public  String  toLogin(){
        return "main/login";
    }


    @PostMapping(value = {"/login"})
    public String  toLogin(String username,String password,Boolean rememberMe,HttpServletRequest request,Map<String,String> map){

        System.out.println("******login*********");

        //这里直接接收FormAutnenticationFilter存放于request中的异常名称shiroLoginFailure即可；

//        String shiroLoginFailure = (String)request.getAttribute("shiroLoginFailure");
//
//        if (shiroLoginFailure!=null) {
//
//            if (shiroLoginFailure.equals(CredentialsException.class.getName())) {
//                map.put("msg", "用户名或密码错误！！");
//                return "main/login";
//            } else if (shiroLoginFailure.equals(AccountException.class.getName())) {
//                map.put("msg", "账户异常！！");
//                return "main/login";
//            } else
//                map.put("msg", "系统异常！！");
//                return "main/login";
//            }
//
//        return "redirect:/index";



        /*********************************************
         *                                           *
         *       自定义的shiro认证过程                 *
         *                                           *
         *                                           *
         *********************************************/
        Subject subject = SecurityUtils.getSubject();

        //传入所有表格属性字段,必须传入rememberMe否则shiro自带的rememberManager与自定义的rememberManager都不会生效；
        if(rememberMe==null){rememberMe=false;}
        UsernamePasswordToken token = new UsernamePasswordToken(username, password,rememberMe);

        try{
            subject.login(token);

            subject.getSession().setAttribute("loginUser",userService.findUserByUserName(username));

        }catch (CredentialsException e){

            map.put("msg", "用户名或密码错误！！");
            //认证失败返回到登陆页面；
            return  "main/login";
        }catch (AccountException e){

            map.put("msg", "账户异常！！");
            return  "main/login";
        }catch (AuthenticationException e){

            map.put("msg", "系统异常！！");
            return  "main/login";
        }
        return "redirect:/index";
    }


    /**
     * 登陆成功的请求需要将用户添加到session
     *
     *
     * @return
     */
    @GetMapping(value = {"/","/index"})
    public  String  index(){

        Subject subject = SecurityUtils.getSubject();

        User loginUser = (User) subject.getPrincipal();

        subject.getSession().setAttribute("loginUser",loginUser);

        return "main/index";
    }






}
