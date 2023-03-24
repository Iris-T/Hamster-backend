package cn.iris.hamster.bean.pojo;

import cn.iris.hamster.common.bean.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 系统字段-货物类型表
 * @author Iris
 * @TableName cargo_type
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="cargo_type")
@Data
public class CargoType extends BaseEntity implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 中文名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 类型识别关键字
     */
    @TableField(value = "`key`")
    private String key;

    /**
     * 能否被修改，0-可以，1-不可以
     */
    private Integer canUp;

    /**
     * 权限状态-0为正常,1为停用
     */
    private String status;

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

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = -7217662189837860995L;
}