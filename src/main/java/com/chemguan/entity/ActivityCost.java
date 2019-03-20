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
 * @Title: ActivityCost
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Entity
@Data
@Table(name = "activity_cost")
@NoArgsConstructor
@AllArgsConstructor
public class ActivityCost  implements Serializable{

    /** cost_id */
    @Id
    @ApiModelProperty(value = "cost_id")
    private Integer costId;
    /** 门票名称 */
    @ApiModelProperty(value = "门票名称")
    private String costName;
    /** 报名费用 */
    @ApiModelProperty(value = "报名费用")
    private Double costMoney;
    /** 人数限制 */
    @ApiModelProperty(value = "人数限制")
    private Integer costNum;
    /** 活动id */
    @ApiModelProperty(value = "活动id")
    private Integer activityId;

}

