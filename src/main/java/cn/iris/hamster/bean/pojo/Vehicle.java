package cn.iris.hamster.bean.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车辆表
 * @TableName vehicle
 */
@TableName(value ="vehicle")
@Data
@EqualsAndHashCode
public class Vehicle implements Serializable {
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
    private Double load;

    /**
     * 车辆状态,参考枚举VehicleStatusEnum,0-闲置,1-作业,2-检修,3-停用
     */
    private String status;

    /**
     * 当前所在仓库
     */
    private Long localWh;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
     *
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     *
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     *
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;
}