package com.lkq.billmanagesystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @func  处理shiro发出的请求，响应到指定的页面；
 *
 */
@Controller
public class ShiroController {

    @GetMapping("/unauthorized")
    public String unAuthorized(){

        return  "main/unauthorized";

    }


}
