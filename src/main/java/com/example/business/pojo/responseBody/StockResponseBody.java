package com.example.business.pojo.responseBody;

import com.example.business.entity.UserStockView;
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
