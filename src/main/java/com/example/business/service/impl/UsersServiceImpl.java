package com.example.business.service.impl;

import com.example.business.entity.Users;
import com.example.business.mapper.UsersMapper;
import com.example.business.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cyw
 * @since 2022-08-24
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
