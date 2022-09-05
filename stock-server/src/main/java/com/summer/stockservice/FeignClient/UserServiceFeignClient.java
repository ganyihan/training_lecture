package com.summer.stockservice.FeignClient;

import com.summer.userservice.entity.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", url = "http://127.0.0.1:8082")
@Component
public interface UserServiceFeignClient {

  @GetMapping("/user-service/user/getUser")
  Users getUser(@RequestParam(value = "userName") String userName);
}
