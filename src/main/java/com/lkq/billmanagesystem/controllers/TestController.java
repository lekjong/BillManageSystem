package com.lkq.billmanagesystem.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class TestController {

    @GetMapping("/test")
    public  String  dotest(Map<String,String> sMap){

        sMap.put("name","abc");

        return  "test/test.html";
    }



}
