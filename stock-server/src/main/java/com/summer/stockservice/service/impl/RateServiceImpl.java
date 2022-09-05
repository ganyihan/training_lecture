package com.summer.stockservice.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.summer.stockservice.entity.Rate;
import com.summer.stockservice.mapper.RateMapper;
import com.summer.stockservice.service.IRateService;
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
public class RateServiceImpl extends ServiceImpl<RateMapper, Rate> implements IRateService {

}
