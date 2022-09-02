package com.example.business.controller;

import com.example.business.exception.PasswordErrorException;
import com.example.business.exception.SignTokenException;
import com.example.business.exception.UserNotFoundException;
import com.example.business.pojo.requestBody.LoginRequestBody;
import com.example.business.pojo.responseBody.LoginResponseBody;
import com.example.business.service.LoginService;
import com.example.business.utils.Result;
import com.example.business.utils.ResultEnum;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin

public class LoginController {
  @Resource
  private LoginService loginService;

  Logger logger = Logger.getLogger(LoginController.class);

  @PostMapping("/login")
  public Result<?> login(@RequestBody LoginRequestBody req) {
    System.out.println(req);
    try {
      LoginResponseBody res = loginService.login(req);
      return Result.success(res);
    } catch (PasswordErrorException e) {
      logger.warn("login: passwordError!");
      return Result.error(ResultEnum.PASSWORD_ERROR);
    } catch (UserNotFoundException e) {
      logger.warn("login: userNotFound!");
      return Result.error();
    } catch (SignTokenException e) {
      logger.warn("login: signTokenError!");
      return Result.error(ResultEnum.SIGN_TOKEN_ERROR);
    } catch (Exception e) {
      logger.error("login：" + e.getMessage());
      return Result.error();
    }
  }
}
