package com.summer.stockservice.pojo.responseBody;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import utils.Double2Serializer;

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
    @JsonSerialize(using = Double2Serializer.class)
    private Double price;
    @JsonSerialize(using = Double2Serializer.class)
    private Double notionalUsd;
    private String currency;
    private String issuerSector;
    private String salesperson;
    private String htPt;
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
    @JsonSerialize(using = Double2Serializer.class)
    private Double totalBuyNotional;
    @JsonSerialize(using = Double2Serializer.class)
    private Double totalSellNotional;
    @JsonSerialize(using = Double2Serializer.class)
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
