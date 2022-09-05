package com.summer.stockservice.config;


import com.summer.stockservice.FeignClient.UserServiceFeignClient;
import com.summer.userservice.entity.Users;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import com.summer.stockservice.utils.JWTToken;
import com.summer.stockservice.utils.JWTUtil;
import utils.Roles;

import javax.annotation.Resource;

public class UserRealm extends AuthorizingRealm {

  @Resource
  private UserServiceFeignClient userServiceFeignClient;



  /**
   * 重写此方法，不然Shiro会报错
   */
  @Override
  public boolean supports(AuthenticationToken token) {
    return token instanceof JWTToken;
  }


  // 授权
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    String username = JWTUtil.getUsername(principals.toString());
    Users user = userServiceFeignClient.getUser(username);
    SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
    if (user!= null) {
      if (user.getAuthority() == 0) {
        simpleAuthorizationInfo.addRole(Roles.COMMON_USER.getRole());
      } else {
        simpleAuthorizationInfo.addRole(Roles.SALESPERSON.getRole());
      }
    }
    return simpleAuthorizationInfo;
  }



  // 验证
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
    String token = (String) auth.getCredentials();
    // 解密获得username，用于和数据库进行对比
    String username = JWTUtil.getUsername(token);
    if (username == null) {
      throw new AuthenticationException("token invalid");
    }

    Users user = userServiceFeignClient.getUser(username);
    if (user == null) {
      throw new AuthenticationException("User didn't existed!");
    }

//    if (! Token.verify(token, username, userBean.getPassword())) {
//      throw new AuthenticationException("Username or password error");
//    }

    return new SimpleAuthenticationInfo(token, token, "adminRealm");


  }
}
