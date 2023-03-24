package cn.iris.hamster.bean.pojo;

import cn.iris.hamster.common.bean.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 货物表
 * @author Iris
 * @TableName cargo
 */
@Accessors(chain = true)
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
     * 货物空间大小,单位-立方米
     */
    private Double space;

    /**
     * 货物重量，单位KG
     */
    private Double weight;

    /**
     * 委托企业ID
     */
    private Long cooperativeId;

    /**
     * 目标地-城市编码
     */
    private String dest;

    /**
     * 目的地-全部地址
     */
    private String sup;

    /**
     * 验存点/发货仓
     */
    private Long startWh;

    /**
     * 当前所在仓/收货仓
     */
    private Long localWh;

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