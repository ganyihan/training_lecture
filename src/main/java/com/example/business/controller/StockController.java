package com.example.business.controller;


import com.example.business.pojo.requestBody.StockRequestBody;
import com.example.business.pojo.responseBody.StockResponseBody;
import com.example.business.service.IStockService;
import com.example.business.utils.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/stock")
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

