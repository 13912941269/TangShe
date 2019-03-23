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
 * @Title: ActivityTeam
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Entity
@Data
@Table(name = "activity_team")
@NoArgsConstructor
@AllArgsConstructor
public class ActivityTeam  implements Serializable{

    /** team_id */
    @Id
    @ApiModelProperty(value = "team_id")
    private Integer teamId;
    /** 活动id */
    @ApiModelProperty(value = "活动id")
    private Integer activityId;
    /** 成员id */
    @ApiModelProperty(value = "成员id")
    private Integer userId;
    /** 品牌id */
    @ApiModelProperty(value = "品牌id")
    private Integer brandId;
    /** 角色 1:创建者 2:管理者 3:观察者 4：主办方成员 5:普通用户*/
    @ApiModelProperty(value = "角色 1:创建者 2:管理者 3:观察者 4：主办方成员 5:普通用户")
    private Integer userRole;

    /** 推荐人 */
    @ApiModelProperty(value = "推荐人")
    private Integer recommendUid;

    /** 邀请函二维码地址 */
    @ApiModelProperty(value = "邀请函二维码地址")
    private String invitImg;
    /** 添加时间 */
    @ApiModelProperty(value = "添加时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date addtime;

}

