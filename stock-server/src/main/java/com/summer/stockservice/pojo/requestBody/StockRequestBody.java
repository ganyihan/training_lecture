package com.summer.stockservice.pojo.requestBody;

import lombok.Data;

@Data
public class StockRequestBody {
  @Data
  public static class UserStockReq{
    private Integer userId;
    private long currentPage;
    private long pageSize;
  }

}
