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
 * @Title: TsActivity
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Entity
@Data
@Table(name = "ts_activity")
@NoArgsConstructor
@AllArgsConstructor
public class TsActivity  implements Serializable{

    /** activity_id */
    @Id
    @ApiModelProperty(value = "activity_id")
    private Integer activityId;
    /** 活动主题 */
    @ApiModelProperty(value = "活动主题")
    private String activityTitle;
    /** 活动地点 */
    @ApiModelProperty(value = "活动地点")
    private String activityAddress;
    /** 经度 */
    @ApiModelProperty(value = "经度")
    private String activityLng;
    /** 纬度 */
    @ApiModelProperty(value = "纬度")
    private String activityLat;
    /** 开始时间 */
    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /** 截止报名 */
    @ApiModelProperty(value = "截止报名")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date signTime;
    /** 结束时间 */
    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /** 活动类别 */
    @ApiModelProperty(value = "活动类别")
    private String columName;
    /** 活动封面（多张以,隔开） */
    @ApiModelProperty(value = "活动封面（多张以,隔开）")
    private String titleImg;
    /** 活动内容（多张以,隔开） */
    @ApiModelProperty(value = "活动内容（多张以,隔开）")
    private String contentImg;
    /** 用户评论 1:可评论 0:不可评论 */
    @ApiModelProperty(value = "用户评论 1:可评论 0:不可评论")
    private Integer commentType;
    /** 主办方 */
    @ApiModelProperty(value = "主办方")
    private Integer userId;
    /** 成员加入活动二维码地址 */
    @ApiModelProperty(value = "成员加入活动二维码地址")
    private String qrCode;
    /** 添加时间 */
    @ApiModelProperty(value = "添加时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date addtime;
    /** 负责人 */
    @ApiModelProperty(value = "负责人")
    private Integer principalId;
    /** 积分开关 */
    @ApiModelProperty(value = "积分开关")
    private Integer scoreSwitch;
    /** 爆款开关 */
    @ApiModelProperty(value = "爆款开关")
    private Integer explosionSwitch;
    /** 拼团开关 */
    @ApiModelProperty(value = "拼团开关")
    private Integer groupSwitch;

    /** 活动状态 */
    @Transient
    private String activeType;

    /** 用户参与活动状态 */
    @Transient
    private String activeUserType;

    /** 活动报名费用 */
    @Transient
    private ActivityCost activityCost;

    /** 活动报名人数 */
    @Transient
    private Integer ActivitySignCount;


}

