package cn.iris.hamster.bean.pojo;

import cn.hutool.core.util.ObjUtil;
import cn.iris.hamster.common.bean.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 运单表
 * @author Iris
 * @TableName trans
 */
@TableName(value ="trans")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Trans extends BaseEntity implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 6379071167421687287L;
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 驾驶员ID
     */
    private Long driverId;

    /**
     * 车辆ID
     */
    private Long vehicleId;

    /**
     * 发货仓ID
     */
    private Long startWhId;

    /**
     * 收货仓ID
     */
    private Long endWhId;

    /**
     * 运单状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;
}