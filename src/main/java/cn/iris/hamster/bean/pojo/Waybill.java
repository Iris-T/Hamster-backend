package cn.iris.hamster.bean.pojo;

import cn.iris.hamster.bean.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 运单表
 * @author Iris
 * @TableName waybill
 */
@TableName(value ="waybill")
@Data
@EqualsAndHashCode(callSuper = false)
public class Waybill extends BaseEntity implements Serializable {
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
     * 发货核对人ID
     */
    private Long startKeeperId;

    /**
     * 收货核对人ID
     */
    private Long endKeeperId;

    /**
     * 发货时间
     */
    private Date startTime;

    /**
     * 收货时间
     */
    private Date endTime;
}