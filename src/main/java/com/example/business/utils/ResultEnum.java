package com.example.business.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {
  SUCCESS(200, "请求成功"),
  DEFAULT_ERROR(500, "请求失败"),
  ARGUMENT_ERROR(1002, "参数错误"),
  SIGN_TOKEN_ERROR(1003, "生成签名失败"),
  PASSWORD_ERROR(2001, "密码错误"),
  USER_NOT_FOUND(2002, "用户未找到"),
  AUTH_ERROR(2003, "权限不满足");


  private int code;
  private String msg;
}
