package com.chemguan.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Title: TsCommodity
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Entity
@Data
@Table(name = "ts_commodity")
@NoArgsConstructor
@AllArgsConstructor
public class TsCommodity implements Serializable{

    /** commodity_id */
    @Id
    @ApiModelProperty(value = "commodity_id")
    private Integer commodityId;
    /** commodity_img */
    @ApiModelProperty(value = "商品图")
    private String commodityImg;
    /** commodity_desc */
    @ApiModelProperty(value = "商品描述")
    private String commodityDesc;
    /** old_price */
    @ApiModelProperty(value = "原价")
    private Double oldPrice;
    /** new_price */
    @ApiModelProperty(value = "优惠价")
    private Double newPrice;
    /** commodity_type */
    @ApiModelProperty(value = "0:爆款1：拼团")
    private Integer commodityType;


}

