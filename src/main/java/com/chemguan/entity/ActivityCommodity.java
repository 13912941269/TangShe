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
 * @Title: ActivityCommodity
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Entity
@Data
@Table(name = "activity_commodity")
@NoArgsConstructor
@AllArgsConstructor
public class ActivityCommodity implements Serializable{

    /** ac_id */
    @Id
    @ApiModelProperty(value = "ac_id")
    private Integer acId;

    /** commodity_id */
    @ApiModelProperty(value = "commodity_id")
    private Integer commodityId;

    /** activity_id */
    @ApiModelProperty(value = "activity_id")
    private Integer activityId;

    /** 0:爆款1：拼团 */
    @ApiModelProperty(value = "ac_type")
    private Integer acType;


}

