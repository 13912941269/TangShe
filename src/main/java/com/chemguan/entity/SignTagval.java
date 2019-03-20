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
 * @Title: SignTagval
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Entity
@Data
@Table(name = "sign_tagval")
@NoArgsConstructor
@AllArgsConstructor
public class SignTagval  implements Serializable{

    /** tagval_id */
    @Id
    @ApiModelProperty(value = "tagval_id")
    private Integer tagvalId;
    /** 标签id */
    @ApiModelProperty(value = "标签id")
    private Integer tagId;
    /** 标签值 */
    @ApiModelProperty(value = "标签值")
    private String tagvalVal;
    /** 报名记录id */
    @ApiModelProperty(value = "报名记录id")
    private Integer signId;

}

