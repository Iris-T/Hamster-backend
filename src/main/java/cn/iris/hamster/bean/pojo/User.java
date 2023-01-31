package cn.iris.hamster.bean.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户表
 * @author Iris
 * @TableName user
 */
@TableName(value ="user")
@Data
@EqualsAndHashCode
public class User implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 5295888814991568084L;
    /**
     * 用户ID
     */
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * 登录用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 性别-0为女,1为男
     */
    private String gender;

    /**
     * 身份证号
     */
    private String idNo;

    /**
     * 手机/座机号
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 用户状态-0为正常,1为停用
     */
    private String status;

    /**
     * 创建人ID
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新者ID
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}