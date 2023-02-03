package cn.iris.hamster.bean.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统字段表
 * @author Iris
 * @TableName system_field
 */
@TableName(value ="system_field")
@Data
@EqualsAndHashCode
public class SystemField implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = -4478274797228834246L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段关键字
     */
    private String key;

    /**
     * 字段类型,0为字符串,1为整数,2为浮点数,3为货币,4为布尔值
     */
    private String type;

    /**
     * 字段值字符串
     */
    private String value;

    /**
     * 字段启用状态,0为启用,1为停用
     */
    private String status;

    /**
     * 创建人ID
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者ID
     */
    private Long updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 字段备注
     */
    private String remark;
}