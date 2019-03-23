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
import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Title: TsSign
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@Entity
@Data
@Table(name = "ts_sign")
@NoArgsConstructor
@AllArgsConstructor
public class TsSign  implements Serializable{

    /** sign_id */
    @Id
    @ApiModelProperty(value = "sign_id")
    private Integer signId;
    /** 费用类型 */
    @ApiModelProperty(value = "费用类型")
    private Integer costId;
    /** 支付方式 1:微信支付 2:线下支付 */
    @ApiModelProperty(value = "支付方式 1:微信支付 2:线下支付")
    private Integer payMethod;
    /** 添加时间 */
    @ApiModelProperty(value = "添加时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date addtime;
    /** 1:已付款 0:待付款 */
    @ApiModelProperty(value = "1:已付款 0:待付款")
    private Integer payType;
    /** 售票人 */
    @ApiModelProperty(value = "售票人")
    private Integer teamId;
    /** 推荐人 */
    @ApiModelProperty(value = "推荐人")
    private Integer recommendUid;
    /** 姓名 */
    @ApiModelProperty(value = "姓名")
    private String cusName;
    /** 手机号 */
    @ApiModelProperty(value = "手机号")
    private String cusPhone;
    /** 活动id */
    @ApiModelProperty(value = "活动id")
    private Integer activityId;
    /** 需求 */
    @ApiModelProperty(value = "需求")
    private String  needDesc;
    /** 标签值 */
    @ApiModelProperty(value = "标签值")
    private String signNum;
    /** 电子票编号 */
    @ApiModelProperty(value = "电子票编号")
    private String signCode;
    /** 验票状态1:已验票 0:未验票  */
    @ApiModelProperty(value = "验票状态1:已验票 0:未验票 ")
    private Integer codeType;
    /** 1:正常 2:关闭 */
    @ApiModelProperty(value = "1:正常 2:关闭")
    private Integer signState;

    /** 活动报名费用 */
    @Transient
    private ActivityCost activityCost;

    /** 活动 */
    @Transient
    private TsActivity tsActivity;

    /** 订单 */
    @Transient
    private TsOrder tsOrder;

    /** 报名用户 */
    @Transient
    private TsUser tsUser;
}

