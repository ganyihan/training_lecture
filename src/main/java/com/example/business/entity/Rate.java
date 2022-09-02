package com.example.business.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author cyw
 * @since 2022-08-24
 */
@ApiModel(value = "Rate对象", description = "")
@Data
public class Rate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("汇率id")
    @TableId(type = IdType.AUTO)
    private Integer rateId;

    @ApiModelProperty("汇率")
    private Double rate;

    @ApiModelProperty("交易的货币")
    private String currency;

}
