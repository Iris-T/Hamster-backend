package cn.iris.hamster.bean.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 合作企业
 * @TableName cooperative
 */
@TableName(value ="cooperative")
@Data
@EqualsAndHashCode
public class Cooperative implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = -5215665337831415745L;
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 企业名称
     */
    private String name;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 统一信用代码
     */
    private String usci;

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
}