package com.lkq.billmanagesystem.controllers;

import com.lkq.billmanagesystem.entities.User;
import com.lkq.billmanagesystem.services.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequiresPermissions("role:userManager")  //shiro用户权限管理；
    @GetMapping("/users")
    public String  getUsers(User user, Map<String,Object> map){

        List<User> users = userService.getUsers(user);

        map.put("users",users);
        map.put("username",user.getUsername());

        return "user/list";
    }

    @RequiresPermissions("role:readUser")
    @GetMapping("/user/{id}")
     public  String findUserById(@PathVariable(value ="id")Integer id,Map<String,Object> map,
                                 @RequestParam(value = "type",defaultValue ="view")String type ){

        map.put("user",userService.findUserById(id));
        //map.put("uid",id);

        return "user/"+type;
     }


    @RequiresPermissions("role:addUser")
    @PostMapping("/user")
    public String addUser(User user){

        userService.addUser(user);

        return "redirect:/users";
    }

    @RequiresPermissions("role:updateUser")
    @PutMapping("/user")
    public String updateUser(User user,Map<String,Object> map){

    userService.updateUser(user);

    return "redirect:/users";

    }

    @RequiresPermissions("role:deleteUser")
    @DeleteMapping("/user/{id}")
    public String   deleteUser(@PathVariable(value ="id")Integer id){

        userService.deleteUserById(id);

        return  "redirect:/users";
   }



   //异步校验原密码
    @ResponseBody
    @GetMapping("/user/pwd/{oldPassword}")
    public boolean checkPwd(@PathVariable(value = "oldPassword") String oldPassword, HttpSession session){

        User user = (User) session.getAttribute("loginUser");

        if (user!=null && oldPassword.equals(user.getPassword())){

            return  true;

        }

        else return  false;

    }


    //更新新密码
    @PostMapping("/user/pwd")
    public String updatePwd(String password,HttpSession session){

        User loginUser = (User) session.getAttribute("loginUser");

        loginUser.setPassword(password);

        userService.updateUser(loginUser);

        return "redirect:/users";
    }


}
