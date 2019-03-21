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
 * @Title: ActivityScore
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Entity
@Data
@Table(name = "activity_score")
@NoArgsConstructor
@AllArgsConstructor
public class ActivityScore implements Serializable{

    /** score_id */
    @Id
    @ApiModelProperty(value = "score_id")
    private Integer scoreId;

    /** activity_id */
    @ApiModelProperty(value = "activity_id")
    private Integer activityId;

    /** user_id */
    @ApiModelProperty(value = "user_id")
    private Integer userId;

    /** 积分数 */
    @ApiModelProperty(value = "score_number")
    private Integer scoreNumber;

    /** 积分说明 */
    @ApiModelProperty(value = "score_desc")
    private String scoreDesc;

    /** 邀请人 */
    @ApiModelProperty(value = "invite_id")
    private Integer inviteId;

    /** 被邀请人 */
    @ApiModelProperty(value = "invitees_id")
    private Integer inviteesId;

    /** 积分类型：0一级分销1：二级分销2：点赞3：阅读4：逛店5.下单6：推荐签单7验票 */
    @ApiModelProperty(value = "score_type")
    private Integer scoreType;

    /** 添加时间 */
    @ApiModelProperty(value = "添加时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date addtime;

}

