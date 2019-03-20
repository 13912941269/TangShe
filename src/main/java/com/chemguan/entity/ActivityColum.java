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
 * @Title: ActivityColum
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Entity
@Data
@Table(name = "activity_colum")
@NoArgsConstructor
@AllArgsConstructor
public class ActivityColum  implements Serializable{

    /** colum_id */
    @Id
    @ApiModelProperty(value = "colum_id")
    private Integer columId;
    /** 类别名称 */
    @ApiModelProperty(value = "类别名称")
    private String columName;

}

