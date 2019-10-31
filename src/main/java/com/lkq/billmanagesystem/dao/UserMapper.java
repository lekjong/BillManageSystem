package com.lkq.billmanagesystem.dao;

import com.lkq.billmanagesystem.entities.User;

import java.util.List;

public interface UserMapper {

    List<User>  getUsers(User user);

    User findUserById(Integer id);

    Integer addUser(User user);

    Integer updateUser(User user);

    Integer deleteUserById(Integer id);

    User findUserByUserName(String username);

}
