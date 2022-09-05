package com.summer.userservice.controller;




import com.summer.userservice.pojo.requestBody.LoginRequestBody;
import com.summer.userservice.pojo.responseBody.LoginResponseBody;
import com.summer.userservice.service.LoginService;
import exception.PasswordErrorException;
import exception.SignTokenException;
import exception.UserNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import utils.Result;
import utils.ResultEnum;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/user-service/user")
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
