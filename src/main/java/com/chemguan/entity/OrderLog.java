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
 * @Title: OrderLog
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Entity
@Data
@Table(name = "order_log")
@NoArgsConstructor
@AllArgsConstructor
public class OrderLog  implements Serializable{

    /** log_id */
    @Id
    @ApiModelProperty(value = "log_id")
    private Integer logId;
    /** 操作者 */
    @ApiModelProperty(value = "操作者")
    private Integer userId;
    /** 当前金额 */
    @ApiModelProperty(value = "当前金额")
    private Double nowMoney;
    /** 历史金额 */
    @ApiModelProperty(value = "历史金额")
    private Double oldMoney;
    /** 添加时间 */
    @ApiModelProperty(value = "添加时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date addtime;
    /** 订单id */
    @ApiModelProperty(value = "订单id")
    private Integer orderId;

}

