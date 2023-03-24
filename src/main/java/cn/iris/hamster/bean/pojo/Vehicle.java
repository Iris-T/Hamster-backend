package cn.iris.hamster.bean.pojo;

import cn.iris.hamster.common.bean.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 车辆表
 * @author Iris
 * @TableName vehicle
 */
@TableName(value ="vehicle")
@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = false)
public class Vehicle extends BaseEntity implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = -5507061839136410475L;
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 车牌号
     */
    private String plateNo;

    /**
     * 汽车载重，单位KG
     */
    @TableField(value = "`load`")
    private Double load;

    /**
     * 汽车运载空间大小，单位m³
     */
    private Double space;

    /**
     * 车辆状态,参考枚举VehicleStatusEnum,0-闲置,1-作业,2-检修,3-停用
     */
    @TableField(value = "`status`")
    private String status;

    /**
     * 当前所在仓库
     */
    private Long localWh;

    /**
     * 备注
     */
    @TableField(value = "remark", updateStrategy = FieldStrategy.IGNORED)
    private String remark;
}