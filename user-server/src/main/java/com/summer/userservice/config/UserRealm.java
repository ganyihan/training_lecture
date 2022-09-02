package com.summer.userservice.config;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.summer.userservice.entity.Users;
import com.summer.userservice.mapper.UsersMapper;
import com.summer.userservice.utils.JWTToken;
import com.summer.userservice.utils.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import utils.Roles;

import javax.annotation.Resource;

public class UserRealm extends AuthorizingRealm {

  @Resource
  private UsersMapper usersMapper;



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
    Users user = usersMapper.selectOne(new LambdaQueryWrapper<Users>()
            .eq(Users::getUserName, username));
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

    Users user = usersMapper.selectOne(new LambdaQueryWrapper<Users>()
            .eq(Users::getUserName, username));
    if (user == null) {
      throw new AuthenticationException("User didn't existed!");
    }

//    if (! Token.verify(token, username, userBean.getPassword())) {
//      throw new AuthenticationException("Username or password error");
//    }

    return new SimpleAuthenticationInfo(token, token, "adminRealm");


  }
}
