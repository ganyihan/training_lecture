package com.example.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.business.entity.Stock;
import com.example.business.entity.UserStockView;
import com.example.business.mapper.StockMapper;
import com.example.business.mapper.UserStockViewMapper;
import com.example.business.pojo.requestBody.StockRequestBody;
import com.example.business.pojo.responseBody.StockResponseBody;
import com.example.business.service.IStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
