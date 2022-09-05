package com.summer.stockservice.pojo.responseBody;


import com.summer.stockservice.entity.UserStockView;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class StockResponseBody {

  @Data
  @Builder
  public static class UserStockRes {
    private List<UserStockView> userStockList;
    private long total;
  }
}
