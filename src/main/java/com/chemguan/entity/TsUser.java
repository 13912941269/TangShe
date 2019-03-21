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
 * @Title: TsUser
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@Entity
@Data
@Table(name = "ts_user")
@NoArgsConstructor
@AllArgsConstructor
public class TsUser  implements Serializable{

    /** user_id */
    @Id
    @ApiModelProperty(value = "user_id")
    private Integer userId;
    /** openid */
    @ApiModelProperty(value = "openid")
    private String openid;
    /** user_number */
    @ApiModelProperty(value = "user_number")
    private String userNumber;
    /** 头像 */
    @ApiModelProperty(value = "头像")
    private String userImg;
    /** 昵称 */
    @ApiModelProperty(value = "昵称")
    private String nickName;
    /** 姓名 */
    @ApiModelProperty(value = "姓名")
    private String userName;
    /** 密码 */
    @ApiModelProperty(value = "密码")
    private String userPwd;
    /** 账户 */
    @ApiModelProperty(value = "账户")
    private String userAcount;
    /** 性别 1:男 0:女 2:保密 */
    @ApiModelProperty(value = "性别 1:男 0:女 2:保密")
    private Integer userSex;
    /** 手机号 */
    @ApiModelProperty(value = "手机号")
    private String userPhone;
    /** 出生年月 */
    @ApiModelProperty(value = "出生年月")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    /** 省 */
    @ApiModelProperty(value = "省")
    private String province;
    /** 市 */
    @ApiModelProperty(value = "市")
    private String city;
    /** 详细地址 */
    @ApiModelProperty(value = "详细地址")
    private String  address;
    /** 0：未认证 1:个人认证 2:企业认证 3:组织认证 */
    @ApiModelProperty(value = "0：未认证 1:个人认证 2:企业认证 3:组织认证")
    private Integer authType;
    /** 身份证号 */
    @ApiModelProperty(value = "身份证号")
    private String certifyNumebr;
    /** 真实姓名 */
    @ApiModelProperty(value = "真实姓名")
    private String realName;
    /** 身份证正面*/
    @ApiModelProperty(value = "身份证正面 ")
    private String faceImg;
    /** 身份证反面 */
    @ApiModelProperty(value = "身份证反面")
    private String backImg;
    /** 企业全程 */
    @ApiModelProperty(value = "企业全程")
    private String companyName;
    /** 营业执照 */
    @ApiModelProperty(value = "营业执照")
    private String businessNumber;
    /** 法人 */
    @ApiModelProperty(value = "法人")
    private String legalPerson;
    /** 营业执照照片 */
    @ApiModelProperty(value = "营业执照照片")
    private String businessImg;
    /** 组织机构名称 */
    @ApiModelProperty(value = "组织机构名称")
    private String organizeName;
    /** 组织机构代码 */
    @ApiModelProperty(value = "组织机构代码")
    private String organizeNumber;
    /** 负责人名称 */
    @ApiModelProperty(value = "负责人名称")
    private String chargeName;
    /** 组织机构代码证照片 */
    @ApiModelProperty(value = "组织机构代码证照片")
    private String organizeImg;
    /** 主办方图片 */
    @ApiModelProperty(value = "主办方图片")
    private String spomsorImg;
    /** 主办方名称 */
    @ApiModelProperty(value = "主办方名称")
    private String spomsorName;
    /** 主办方简介 */
    @ApiModelProperty(value = "主办方简介")
    private String spomsorDesc;
    /** 添加时间 */
    @ApiModelProperty(value = "添加时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date addtime;


    /** 活动場次 */
    @Transient
    private Integer activityCount;


    /** 报名人數 */
    @Transient
    private Integer signCount;

}

