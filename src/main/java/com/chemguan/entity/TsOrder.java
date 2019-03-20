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
 * @Title: TsOrder
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@Entity
@Data
@Table(name = "ts_order")
@NoArgsConstructor
@AllArgsConstructor
public class TsOrder  implements Serializable{

    /** order_id */
    @Id
    @ApiModelProperty(value = "order_id")
    private Integer orderId;
    /** 客户信息（关联sign_id） */
    @ApiModelProperty(value = "客户信息（关联sign_id）")
    private Integer signUid;
    /** 定金金额 */
    @ApiModelProperty(value = "定金金额")
    private Double orderMoney;
    /** 销售总额 */
    @ApiModelProperty(value = "销售总额")
    private Double saleMoney;
    /** 品牌id */
    @ApiModelProperty(value = "品牌id")
    private Integer brandId;
    /** 签单人(关联team_id) */
    @ApiModelProperty(value = "签单人(关联team_id)")
    private Integer teamUid;
    /** 带单人(关联team_id，不能是当前活动的品牌 */
    @ApiModelProperty(value = "带单人(关联team_id，不能是当前活动的品牌")
    private Integer signDid;
    /** 收据编号 */
    @ApiModelProperty(value = "收据编号")
    private String receiptNumber;
    /** 备注信息 */
    @ApiModelProperty(value = "备注信息")
    private  orderDesc;
    /** 添加时间 */
    @ApiModelProperty(value = "添加时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date addtime;
    /** 1:正常 2:关闭 */
    @ApiModelProperty(value = "1:正常 2:关闭")
    private Integer orderState;

}

