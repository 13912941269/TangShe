package com.chemguan.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Title: ActivityTeam
 * @ProjectName
 * @Description: TODO
 * @author
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Entity
@Data
@Table(name = "back_phone")
@NoArgsConstructor
@AllArgsConstructor
public class BackPhone implements Serializable {

    /** phone_id */
    @Id
    @ApiModelProperty(value = "phone_id")
    private Integer phoneId;


    @ApiModelProperty(value = "手机号码")
    private String phoneName;
}
