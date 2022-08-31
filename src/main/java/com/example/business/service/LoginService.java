package com.example.business.service;


import com.example.business.pojo.requestBody.LoginRequestBody;
import com.example.business.pojo.responseBody.LoginResponseBody;

public interface LoginService {
  LoginResponseBody login(LoginRequestBody loginRequestBody);
}
