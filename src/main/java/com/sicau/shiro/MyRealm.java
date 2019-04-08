package com.sicau.shiro;

import com.sicau.entity.dto.Authority;
import com.sicau.entity.dto.Identity;
import com.sicau.entity.dto.User;
import com.sicau.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: software-market
 * @description:
 * @author: Lee
 * @create: 2019-02-20 20:17
 **/
public class MyRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Override
//    权限验证
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user=(User) principals.getPrimaryPrincipal();
        String userId=user.getUserId();
        if(userId!=null){
            Identity identity=userService.getIdentity(userId);
            String identityName=identity.getIdentityName();
            String identityId=identity.getIdentityId();
            List<Authority> authorityList=userService.getAuthority(identityId);
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
            for(Authority authority:authorityList){
            info.addStringPermission(identityName+":"+authority.getAauthorityName());
            }
            return info;
        }
        return null;
    }
    //登陆验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}