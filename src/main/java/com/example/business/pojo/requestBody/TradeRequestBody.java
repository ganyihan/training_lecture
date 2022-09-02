package com.example.business.pojo.requestBody;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class TradeRequestBody {
  public static class TraditionalTrade {
    private Date date;
    private String clientSide;
    private Integer userId;
    private Integer size;
    private String ric;
    private String ticker;
    private Double price;
    private Integer rateId;
    private String ht_pt;
    private String salesperson;
  }

  @Data
  public static class QueryTradeRecord {
    private String frequency;
    private String ht_pt;
    private String clientSide;
    private long currentPage;
    private long pageSize;
    private int dateSort;
    private int clientNameSort;
    private int tickerSort;
    private int ricSort;
    private int sizeSort;
    private int priceSort;
    private int notionalUsdSort;
    private int currencySort;
    private int issuerSectorSort;
    private int salesPersonSort;
  }

  @Data
  public static class TraTrade {
    private String clientName;
    private String ticker;
    private String ric;
    private Integer size;
    private Double price;
    private String currency;
    private String issueSector;
    private String salesperson;
    private String ht_pt;
    private String clientSide;
  }

  @Data
  public static class StatisticReq {
    private String frequency;
    private String clientSide;
    private String ht_pt;
  }

  @Data
  public static class NlpTrade{
    private String clientName;
    private String ticker;
    private Integer size;
    private String salesperson;
    private String ht_pt;
    private String clientSide;
  }
}
