package com.example.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author cyw
 * @since 2022-08-25
 */
@TableName("stock_total_view")
@ApiModel(value = "StockTotalView对象", description = "VIEW")
@Data
public class StockTotalView implements Serializable {
  private static final long serialVersionUID = 1L;

  @ApiModelProperty("股票代码")
  private String ric;

  @ApiModelProperty("股票名称")
  private String ticker;

  @ApiModelProperty("交易时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date date;

  @ApiModelProperty("用户名")
  private String clientName;

  @ApiModelProperty("汇率id")
  private Integer rateId;

  @ApiModelProperty("交易的货币")
  private String currency;

  @ApiModelProperty("发行机构")
  private String issuerSector;

  @ApiModelProperty("股票价格")
  private Double price;

  private Double notionalUsd;

  @ApiModelProperty("交易方式")
  private String clientSide;

  @ApiModelProperty("交易数量")
  private Integer size;

  @ApiModelProperty("用户id")
  private Integer userId;

  @ApiModelProperty("交易员")
  private String salesperson;

  @ApiModelProperty("交易方式")
  private String htPt;
}
