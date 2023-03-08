package cn.iris.hamster.bean.entity;

import cn.iris.hamster.common.bean.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 货物表
 * @TableName cargo
 */
@TableName(value ="cargo")
@Data
@EqualsAndHashCode(callSuper = false)
public class Cargo extends BaseEntity implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = -8385602481561018152L;

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 货物名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 货物类型，参考枚举CargoTypeEnum
     */
    private String type;

    /**
     * 货物重量，单位KG
     */
    private Double weight;

    /**
     * 委托企业ID
     */
    private Long cooperativeId;

    /**
     * 验存点/发货仓
     */
    private Long startWh;

    /**
     * 收货仓
     */
    private Long endWh;

    /**
     * 运输状态，参考枚举CargoStatusEnum
     */
    @TableField(value = "`status`")
    private String status;

    /**
     * 备注
     */
    @TableField(value = "remark", updateStrategy = FieldStrategy.IGNORED)
    private String remark;
}