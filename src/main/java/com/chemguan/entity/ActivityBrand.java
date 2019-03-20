package com.chemguan.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import io.swagger.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Title: ActivityBrand
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Entity
@Data
@Table(name = "activity_brand")
@NoArgsConstructor
@AllArgsConstructor
public class ActivityBrand  implements Serializable{

    /** brand_id */
    @Id
    @ApiModelProperty(value = "brand_id")
    private Integer brandId;
    /** 品牌名称 */
    @ApiModelProperty(value = "品牌名称")
    private String brandName;
    /** activity_id */
    @ApiModelProperty(value = "activity_id")
    private Integer activityId;

}

