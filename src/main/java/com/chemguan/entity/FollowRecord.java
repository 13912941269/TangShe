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
 * @Title: FollowRecord
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Entity
@Data
@Table(name = "follow_record")
@NoArgsConstructor
@AllArgsConstructor
public class FollowRecord  implements Serializable{

    /** record_id */
    @Id
    @ApiModelProperty(value = "record_id")
    private Integer recordId;
    /** 用户id */
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    /** 被关注人id */
    @ApiModelProperty(value = "被关注人id")
    private Integer followUid;
    /** 添加时间 */
    @ApiModelProperty(value = "添加时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date addtime;

}

