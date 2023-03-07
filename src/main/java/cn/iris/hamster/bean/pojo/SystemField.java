package cn.iris.hamster.bean.pojo;

import cn.iris.hamster.bean.entity.BaseEntity;
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
@EqualsAndHashCode(callSuper = false)
public class SystemField extends BaseEntity implements Serializable {
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
    private String status;

    /**
     * 字段备注
     */
    private String remark;
}