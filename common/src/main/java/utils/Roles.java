package utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Roles {

  // 普通用户
  COMMON_USER(0, "common_user"),
  // 交易员
  SALESPERSON(1, "salesperson");

  private int authority;
  private String role;
}
