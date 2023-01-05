package cn.iris.hamster.bean.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 运单表
 * @TableName waybill
 */
@TableName(value ="waybill")
@Data
public class Waybill implements Serializable {
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


    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Waybill other = (Waybill) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDriverId() == null ? other.getDriverId() == null : this.getDriverId().equals(other.getDriverId()))
            && (this.getVehicleId() == null ? other.getVehicleId() == null : this.getVehicleId().equals(other.getVehicleId()))
            && (this.getStartWhId() == null ? other.getStartWhId() == null : this.getStartWhId().equals(other.getStartWhId()))
            && (this.getEndWhId() == null ? other.getEndWhId() == null : this.getEndWhId().equals(other.getEndWhId()))
            && (this.getStartKeeperId() == null ? other.getStartKeeperId() == null : this.getStartKeeperId().equals(other.getStartKeeperId()))
            && (this.getEndKeeperId() == null ? other.getEndKeeperId() == null : this.getEndKeeperId().equals(other.getEndKeeperId()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDriverId() == null) ? 0 : getDriverId().hashCode());
        result = prime * result + ((getVehicleId() == null) ? 0 : getVehicleId().hashCode());
        result = prime * result + ((getStartWhId() == null) ? 0 : getStartWhId().hashCode());
        result = prime * result + ((getEndWhId() == null) ? 0 : getEndWhId().hashCode());
        result = prime * result + ((getStartKeeperId() == null) ? 0 : getStartKeeperId().hashCode());
        result = prime * result + ((getEndKeeperId() == null) ? 0 : getEndKeeperId().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", driverId=").append(driverId);
        sb.append(", vehicleId=").append(vehicleId);
        sb.append(", startWhId=").append(startWhId);
        sb.append(", endWhId=").append(endWhId);
        sb.append(", startKeeperId=").append(startKeeperId);
        sb.append(", endKeeperId=").append(endKeeperId);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}