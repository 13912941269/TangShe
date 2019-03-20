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
 * @Title: SignTag
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Entity
@Data
@Table(name = "sign_tag")
@NoArgsConstructor
@AllArgsConstructor
public class SignTag  implements Serializable{

    /** tag_id */
    @Id
    @ApiModelProperty(value = "tag_id")
    private Integer tagId;
    /** 标签名称 */
    @ApiModelProperty(value = "标签名称")
    private String tagName;
    /** 报名设置id */
    @ApiModelProperty(value = "报名设置id")
    private Integer signId;

}

