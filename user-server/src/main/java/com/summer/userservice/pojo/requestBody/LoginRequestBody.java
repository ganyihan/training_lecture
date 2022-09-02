package com.summer.userservice.pojo.requestBody;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class LoginRequestBody {
  @NotEmpty
  private String userName;
  @NotEmpty
  private String password;
}
