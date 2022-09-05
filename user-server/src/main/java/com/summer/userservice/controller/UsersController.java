package com.summer.userservice.controller;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.summer.userservice.entity.Users;
import com.summer.userservice.service.IUsersService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cyw
 * @since 2022-08-24
 */
@RestController
@CrossOrigin
@RequestMapping("/user-service/user")
public class UsersController {

  @Resource
  private IUsersService usersService;

  // 根据用户名查询用户信息
  @GetMapping("getUser")
  public Users getUser(@RequestParam(value = "userName") String userName) {
    System.out.println(userName);
    return usersService.getOne(new LambdaQueryWrapper<Users>()
            .eq(Users::getUserName, userName));
  }

}

