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
 * @Title: TsPraise
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@Entity
@Data
@Table(name = "ts_praise")
@NoArgsConstructor
@AllArgsConstructor
public class TsPraise  implements Serializable{

    /** praise_id */
    @Id
    @ApiModelProperty(value = "praise_id")
    private Integer praiseId;
    /** 用户id */
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    /** activity_id */
    @ApiModelProperty(value = "activity_id")
    private Integer activityId;
    /** 添加时间 */
    @ApiModelProperty(value = "添加时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date addtime;

}

