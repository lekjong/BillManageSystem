package com.lkq.billmanagesystem.dao;

import com.lkq.billmanagesystem.entities.Permission;

import java.util.List;

public interface PermissionMapper {

    List<Permission> findPermissionByUserId(int uid);

}
