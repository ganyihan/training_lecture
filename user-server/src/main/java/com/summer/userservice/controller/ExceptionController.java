package com.summer.userservice.controller;


import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import utils.Result;
import utils.ResultEnum;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionController {

  // 捕捉shiro的异常
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(ShiroException.class)
  public Result<Void> handle401(ShiroException e) {
    return Result.error(ResultEnum.AUTH_ERROR);
  }

  // 捕捉UnauthorizedException
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(UnauthorizedException.class)
  public Result<Void> handle401() {
    return Result.error(ResultEnum.AUTH_ERROR);
  }

  // 捕捉其他所有异常
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Result<Void> globalException(HttpServletRequest request, Throwable ex) {
    return Result.error(getStatus(request).value(), ex.getMessage());
  }

  private HttpStatus getStatus(HttpServletRequest request) {
    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    if (statusCode == null) {
      return HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return HttpStatus.valueOf(statusCode);
  }
}
