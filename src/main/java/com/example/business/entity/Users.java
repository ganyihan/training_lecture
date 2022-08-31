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
@ApiModel(value = "Users对象", description = "")
@Data
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @TableId(type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("角色 0 为用户，1为交易员")
    private Integer authority;

}
