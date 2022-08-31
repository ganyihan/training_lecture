package com.example.business.service.impl;

import com.example.business.entity.Stock;
import com.example.business.mapper.StockMapper;
import com.example.business.service.IStockService;
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
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements IStockService {

}
