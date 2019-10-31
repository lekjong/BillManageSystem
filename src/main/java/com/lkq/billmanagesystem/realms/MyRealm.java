package com.lkq.billmanagesystem.realms;

import com.lkq.billmanagesystem.dao.PermissionMapper;
import com.lkq.billmanagesystem.entities.Permission;
import com.lkq.billmanagesystem.entities.User;
import com.lkq.billmanagesystem.services.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public void setName(String myRealm) {
        super.setName(myRealm);
    }

    //认证，
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       //获取用户名，查数据库获取密码；

        System.out.println("*****************认证*******************************");

        String username = (String)authenticationToken.getPrincipal();

        User user = userService.findUserByUserName(username);

        //向AuthenticationInfo中添加数据，由认证器进行处理；

        if (user!=null){
           return new SimpleAuthenticationInfo(user,user.getPassword(), ByteSource.Util.bytes("lkq"),this.getName());
        }

        else return null;
    }




    //授权，获取用户所具有的权限信息，并添加信息到容器；
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("=======授权=================");

        User currentUser = (User) principalCollection.getPrimaryPrincipal();

        List<Permission> permissions= permissionMapper.findPermissionByUserId(currentUser.getId());

        if(permissions==null || permissions.size()==0)  {return null;}

        SimpleAuthorizationInfo simpleAuthorizationInfo = new  SimpleAuthorizationInfo();

        //获取所有的权限信息，添加到指定的容器；
        for(Permission p :permissions){

         simpleAuthorizationInfo.addStringPermission(p.getPermcode());

        }

        return simpleAuthorizationInfo;
    }

    //当数据库的权限信息发生改动时，清除缓存中的权限信息待重新写入缓存；Cache<Object, AuthorizationInfo>缓存的是AuthorizationInfo;
    //Object key = this.getAuthorizationCacheKey(principals);   cache.remove(key);
    @Override
    protected void doClearCache(PrincipalCollection principals) {
       // super.doClearCache(principals);
        Subject subject = SecurityUtils.getSubject();
        super.doClearCache(subject.getPrincipals());
    }
}
