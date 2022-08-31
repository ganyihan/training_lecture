package com.example.business.entity;

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
 * 
 * </p>
 *
 * @author cyw
 * @since 2022-08-24
 */
@ApiModel(value = "Stock对象", description = "")
@Data
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("股票代码")
    private String ric;

    @ApiModelProperty("股票名称")
    private String ticker;

    @ApiModelProperty("股票价格")
    private Double price;

    @ApiModelProperty("发行机构")
    private String issuerSector;

    @ApiModelProperty("时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    @ApiModelProperty("股票发行数量")
    private Integer size;

    @ApiModelProperty("股票发行币种")
    private String currency;
}