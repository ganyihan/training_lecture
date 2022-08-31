package com.example.business.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.business.entity.Users;
import com.example.business.exception.PasswordErrorException;
import com.example.business.exception.SignTokenException;
import com.example.business.exception.UserNotFoundException;
import com.example.business.mapper.UsersMapper;
import com.example.business.pojo.requestBody.LoginRequestBody;
import com.example.business.pojo.responseBody.LoginResponseBody;
import com.example.business.service.LoginService;
import com.example.business.utils.PasswordMD5;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

  @Resource
  private UsersMapper userMapper;

  @Resource
  private com.example.business.utils.JWTUtil JWTUtil;

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
