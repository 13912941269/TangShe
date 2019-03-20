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
 * @Title: TsRead
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@Entity
@Data
@Table(name = "ts_read")
@NoArgsConstructor
@AllArgsConstructor
public class TsRead  implements Serializable{

    /** read_id */
    @Id
    @ApiModelProperty(value = "read_id")
    private Integer readId;
    /** 用户id */
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    /** 活动id */
    @ApiModelProperty(value = "活动id")
    private Integer activityId;
    /** 添加时间 */
    @ApiModelProperty(value = "添加时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date addtime;

}

