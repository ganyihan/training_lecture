package com.example.business.service;

import com.example.business.entity.Stock;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.business.pojo.requestBody.StockRequestBody;
import com.example.business.pojo.responseBody.StockResponseBody;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cyw
 * @since 2022-08-24
 */
public interface IStockService extends IService<Stock> {

  StockResponseBody.UserStockRes getStockInfo(StockRequestBody.UserStockReq req);
}
