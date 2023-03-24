package cn.iris.hamster.bean.pojo;

import cn.iris.hamster.common.bean.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 权限功能表
 * @author Iris
 * @TableName permission
 */
@TableName(value ="permission")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Permission extends BaseEntity implements Serializable {

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = -1165607605046597392L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 权限功能名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 权限功能标识
     */
    @TableField(value = "`key`")
    private String key;

    /**
     * 是否展示为菜单组件,0为是,1为否
     */
    private String isMenu;

    /**
     * icon图标值
     */
    @TableField(value = "icon", updateStrategy = FieldStrategy.IGNORED)
    private String icon;

    /**
     * 路由路径
     */
    private String path;

    /**
     * 父ID，第一级为0
     */
    @TableField(value = "parent_id", updateStrategy = FieldStrategy.IGNORED)
    private Long parentId;

    /**
     * 权限状态-0为正常,1为停用
     */
    @TableField(value = "`status`")
    private String status;

    /**
     * 备注
     */
    @TableField(value = "remark", updateStrategy = FieldStrategy.IGNORED)
    private String remark;

    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<Permission> children;
}