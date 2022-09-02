package com.example.business.pojo.requestBody;

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
