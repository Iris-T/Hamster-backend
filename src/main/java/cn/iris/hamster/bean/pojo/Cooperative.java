package cn.iris.hamster.bean.pojo;

import cn.iris.hamster.common.bean.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 合作企业
 * @author Iris
 * @TableName cooperative
 */
@TableName(value ="cooperative")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Cooperative extends BaseEntity implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = -5215665337831415745L;
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 月结客户（企业）名称
     */
    @TableField(value = "`name`")
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
}