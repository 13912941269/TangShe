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
 * @Title: ActivitySign
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Entity
@Data
@Table(name = "activity_sign")
@NoArgsConstructor
@AllArgsConstructor
public class ActivitySign  implements Serializable{

    /** sign_id */
    @Id
    @ApiModelProperty(value = "sign_id")
    private Integer signId;
    /** 手机/座机号（区号-座机号） */
    @ApiModelProperty(value = "手机/座机号（区号-座机号）")
    private String signTel;
    /** 是否展示报名人数1:是 0:否 */
    @ApiModelProperty(value = "是否展示报名人数1:是 0:否")
    private Integer peopleType;
    /** 是否允许主办成员报名1:是 0:否 */
    @ApiModelProperty(value = "是否允许主办成员报名1:是 0:否")
    private Integer mainType;
    /** 是否手动售票1:是 0:否 */
    @ApiModelProperty(value = "是否手动售票1:是 0:否")
    private Integer tickketAuto;
    /** 是否允许线下支付1:是 0:否 */
    @ApiModelProperty(value = "是否允许线下支付1:是 0:否")
    private Integer payType;
    /** activity_id */
    @ApiModelProperty(value = "activity_id")
    private Integer activityId;

}

