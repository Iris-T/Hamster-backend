package cn.iris.hamster.bean.pojo;

import cn.iris.hamster.bean.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 仓库表
 * @author Iris
 * @TableName warehouse
 */
@TableName(value ="warehouse")
@Data
@EqualsAndHashCode(callSuper = false)
public class Warehouse extends BaseEntity implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1934119690183387206L;
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 仓库名称
     */
    private String name;

    /**
     * 仓库地址
     */
    private String address;

    /**
     * 仓库使用状态-0为正常,1为停用
     */
    private String status;

    /**
     * 备注
     */
    private String remark;
}