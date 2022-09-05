package com.summer.stockservice.controller;


import com.summer.stockservice.pojo.requestBody.StockRequestBody;
import com.summer.stockservice.pojo.responseBody.StockResponseBody;
import com.summer.stockservice.service.IStockService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.Result;

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
@RequestMapping("/stock-service")
public class StockController {

  @Resource
  private IStockService stockService;

  @GetMapping("getUserStock")
  public Result<StockResponseBody.UserStockRes> getUserStock(StockRequestBody.UserStockReq req) {
    try {
      return Result.success(stockService.getStockInfo(req));
    } catch (Exception e) {
      return Result.error();
    }
  }
}

