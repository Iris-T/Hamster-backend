package cn.iris.hamster.bean.pojo;

import cn.iris.hamster.bean.entity.BaseEntity;
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
    private String name;

    /**
     * 权限功能标识
     */
    private String pKey;

    /**
     * 是否展示为菜单组件,0为是,1为否
     */
    private String isMenu;

    /**
     * icon图标值
     */
    private String icon;

    /**
     * 路由路径
     */
    private String path;

    /**
     * 父ID，第一级为0
     */
    private Long parentId;

    /**
     * 权限状态-0为正常,1为停用
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<Permission> children;
}