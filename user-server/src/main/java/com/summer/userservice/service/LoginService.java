package com.summer.userservice.service;

import com.summer.userservice.pojo.requestBody.LoginRequestBody;
import com.summer.userservice.pojo.responseBody.LoginResponseBody;

public interface LoginService {
  LoginResponseBody login(LoginRequestBody loginRequestBody);
}
