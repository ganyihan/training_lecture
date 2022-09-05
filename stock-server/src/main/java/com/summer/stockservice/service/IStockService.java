package com.summer.stockservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.summer.stockservice.entity.Stock;
import com.summer.stockservice.pojo.requestBody.StockRequestBody;
import com.summer.stockservice.pojo.responseBody.StockResponseBody;


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
