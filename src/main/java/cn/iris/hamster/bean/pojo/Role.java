package cn.iris.hamster.bean.pojo;

import cn.iris.hamster.bean.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 角色表
 * @author Iris
 * @TableName role
 */
@TableName(value ="role")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseEntity implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = -4221487243095473061L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色权限字符
     */
    private String rKey;

    /**
     * 权限状态-0为正常,1为停用
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    @TableField(exist = false)
    private List<Permission> perms;
}