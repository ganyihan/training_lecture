package com.summer.stockservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author cyw
 * @since 2022-08-30
 */
@TableName("user_stock_view")
@ApiModel(value = "UserStockView对象", description = "VIEW")
@Data
public class UserStockView implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("股票代码")
    private String ric;

    @ApiModelProperty("股票名称")
    private String ticker;

    private Integer size;

}
