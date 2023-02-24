package cn.iris.hamster.bean.pojo;

import cn.iris.hamster.bean.entity.BaseEntity;
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
     * 合作伙伴名
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
     * 备注
     */
    private String remark;
}