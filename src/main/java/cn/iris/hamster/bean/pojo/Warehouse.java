package cn.iris.hamster.bean.pojo;

import cn.iris.hamster.common.bean.entity.BaseEntity;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 仓库表
 * @author Iris
 * @TableName warehouse
 */
@Accessors(chain = true)
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
    @ExcelIgnore
    @TableId
    private Long id;

    /**
     * 仓库名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 仓库空间
     */
    private Double space;

    /**
     * 仓库地址
     */
    private String address;

    /**
     * 城市代码
     */
    private String cityCode;

    /**
     * 经度
     */
    private BigDecimal lon;

    /**
     * 纬度
     */
    private BigDecimal lat;

    /**
     * 仓库使用状态-0为正常,1为停用
     */
    @TableField(value = "`status`")
    private String status;

    /**
     * 备注
     */
    @TableField(value = "remark", updateStrategy = FieldStrategy.IGNORED)
    private String remark;
}