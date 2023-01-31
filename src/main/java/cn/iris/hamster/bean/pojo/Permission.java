package cn.iris.hamster.bean.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限功能表
 * @TableName permission
 */
@TableName(value ="permission")
@Data
@EqualsAndHashCode
public class Permission implements Serializable {

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
     * 父ID，第一级为0
     */
    private Long parentId;

    /**
     * 是否展示为菜单组件,0为是,1为否
     */
    private String isMenu;

    /**
     * icon图标值
     */
    private String icon;

    /**
     * 权限状态-0为正常,1为停用
     */
    private String status;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<Permission> child;
}