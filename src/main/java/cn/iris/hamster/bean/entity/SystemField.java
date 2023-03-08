package cn.iris.hamster.bean.entity;

import cn.iris.hamster.common.bean.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 系统字段表
 * @author Iris
 * @TableName system_field
 */
@TableName(value ="system_field")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SystemField extends BaseEntity implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = -4478274797228834246L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 字段名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 字段关键字
     */
    @TableField(value = "`key`")
    private String key;

    /**
     * 字段类型,0为字符串,1为数值
     */
    private String type;

    /**
     * 字符串值
     */
    private String str;

    /**
     * 数值
     */
    private String value;

    /**
     * 字段启用状态,0为启用,1为停用
     */
    @TableField(value = "`status`")
    private String status;

    /**
     * 字段备注
     */
    @TableField(value = "remark", updateStrategy = FieldStrategy.IGNORED)
    private String remark;
}