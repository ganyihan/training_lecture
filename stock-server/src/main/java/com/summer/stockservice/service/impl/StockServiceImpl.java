package com.summer.stockservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.summer.stockservice.entity.Stock;
import com.summer.stockservice.entity.UserStockView;
import com.summer.stockservice.mapper.StockMapper;
import com.summer.stockservice.mapper.UserStockViewMapper;
import com.summer.stockservice.pojo.requestBody.StockRequestBody;
import com.summer.stockservice.pojo.responseBody.StockResponseBody;
import com.summer.stockservice.service.IStockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

  @Resource
  private UserStockViewMapper userStockViewMapper;

  @Override
  public StockResponseBody.UserStockRes getStockInfo(StockRequestBody.UserStockReq req) {
    Page<UserStockView> pageHelper = new Page<>(req.getCurrentPage(), req.getPageSize());
    Page<UserStockView> userStockViewPage = userStockViewMapper.selectPage(pageHelper, new LambdaQueryWrapper<UserStockView>()
            .eq(UserStockView::getUserId, req.getUserId()));
    return StockResponseBody.UserStockRes.builder()
            .userStockList(userStockViewPage.getRecords())
            .total(userStockViewPage.getTotal())
            .build();
  }
}
