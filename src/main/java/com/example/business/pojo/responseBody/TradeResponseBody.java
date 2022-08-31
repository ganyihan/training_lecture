package com.example.business.pojo.responseBody;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

public class TradeResponseBody {

  @Data
  @Builder
  public static class TradeRecord {
    private Date date;
    private String clientName;
    private String clientSide;
    private String ticker;
    private String ric;
    private Integer size;
    private Double price;
    private Double notionalUsd;
    private String currency;
    private String issuerSector;
    private String salesperson;
  }

  @Data
  @Builder
  public static class TradeRecords {
    private List<TradeRecord> tradeRecordList;
    private long total;
  }

  @Data
  @Builder
  public static class StatisticRecord {
    private int totalBuy;
    private int totalSell;
    private int quantity;
    private Double totalBuyNotional;
    private Double totalSellNotional;
    private Double quantityNotional;
  }

  @Data
  @Builder
  public static class ChartData{
    private Date date;
    private int buy;
    private int sell;

  }
}
