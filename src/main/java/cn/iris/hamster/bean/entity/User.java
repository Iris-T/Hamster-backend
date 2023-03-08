package cn.iris.hamster.bean.entity;

import cn.iris.hamster.common.bean.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户表
 * @author Iris
 * @TableName user
 */
@TableName(value ="user")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity implements Serializable {
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
    @TableField(value = "`name`")
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
    @TableField(value = "`status`")
    private String status;

    @TableField(exist = false)
    private Long rid;
}