package com.summer.userservice.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TokenConfig {
  private long expireDate;
  private String secret;
}

