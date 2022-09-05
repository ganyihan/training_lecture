package com.summer.userservice.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.summer.userservice.entity.Users;
import com.summer.userservice.mapper.UsersMapper;
import com.summer.userservice.pojo.requestBody.LoginRequestBody;
import com.summer.userservice.pojo.responseBody.LoginResponseBody;
import com.summer.userservice.service.LoginService;
import exception.PasswordErrorException;
import exception.SignTokenException;
import exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import utils.PasswordMD5;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

  @Resource
  private UsersMapper userMapper;

  @Resource
  private com.summer.userservice.utils.JWTUtil JWTUtil;

  @Override
  public LoginResponseBody login(LoginRequestBody req) {
    // 查询用户
    Users user = userMapper.selectOne(new LambdaQueryWrapper<Users>()
            .eq(Users::getUserName, req.getUserName()));
    if (user == null) {
      throw new UserNotFoundException();
    }

    if (!user.getPassword().equals(PasswordMD5.getPasswordMD5(req.getPassword()))) {
      throw new PasswordErrorException();
    }

    String token;
    try {
      token = this.JWTUtil.sign(user.getUserName());
    } catch (Exception e) {
      e.printStackTrace();
      throw new SignTokenException();
    }

    return LoginResponseBody.builder()
            .userId(user.getUserId())
            .userName(user.getUserName())
            .authority(user.getAuthority())
            .token(token)
            .build();
  }
}
