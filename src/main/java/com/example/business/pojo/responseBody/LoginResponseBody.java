package com.example.business.pojo.responseBody;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseBody {
  private Integer userId;
  private String userName;
  private Integer authority;
  private String token;
}
