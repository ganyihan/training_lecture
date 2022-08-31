package com.example.business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value = "Trade对象", description = "")
@Data
public class Trade implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("交易id")
    @TableId(type = IdType.AUTO)
    private Integer tradeId;

    @ApiModelProperty("交易时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    @ApiModelProperty("交易方式")
    private String clientSide;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("交易数量")
    private Integer size;

    @ApiModelProperty("股票代码")
    private String ric;

    @ApiModelProperty("股票名称")
    private String ticker;

    @ApiModelProperty("股票价格")
    private Double price;

    @ApiModelProperty("交易的货币id")
    private Integer rateId;

    @ApiModelProperty("交易方式")
    private String htPt;

    @ApiModelProperty("交易员")
    private String salesperson;
}
