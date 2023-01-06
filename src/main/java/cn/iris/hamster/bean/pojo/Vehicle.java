package cn.iris.hamster.bean.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 车辆表
 * @TableName vehicle
 */
@TableName(value ="vehicle")
@Data
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
     * 所属企业ID
     */
    private Long cooperative;

    /**
     * 当前所在仓库
     */
    private Long localWh;

    /**
     * 
     */
    private Long createBy;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Long updateBy;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;


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
        Vehicle other = (Vehicle) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPlateNo() == null ? other.getPlateNo() == null : this.getPlateNo().equals(other.getPlateNo()))
            && (this.getLoad() == null ? other.getLoad() == null : this.getLoad().equals(other.getLoad()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCooperative() == null ? other.getCooperative() == null : this.getCooperative().equals(other.getCooperative()))
            && (this.getLocalWh() == null ? other.getLocalWh() == null : this.getLocalWh().equals(other.getLocalWh()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPlateNo() == null) ? 0 : getPlateNo().hashCode());
        result = prime * result + ((getLoad() == null) ? 0 : getLoad().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCooperative() == null) ? 0 : getCooperative().hashCode());
        result = prime * result + ((getLocalWh() == null) ? 0 : getLocalWh().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", plateNo=").append(plateNo);
        sb.append(", load=").append(load);
        sb.append(", status=").append(status);
        sb.append(", cooperative=").append(cooperative);
        sb.append(", localWh=").append(localWh);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}