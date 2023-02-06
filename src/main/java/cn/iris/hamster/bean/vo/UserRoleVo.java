package cn.iris.hamster.bean.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户角色视图类
 *
 * @author Iris
 * @ClassName UserRoleVo
 * @date 2023/2/6 14:42
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleVo {

    private Long id;
    private String username;
    private String role;
    private String name;
    private String gender;
    private String idNo;
    private String phone;
    private String address;
    private String status;
    private Long createBy;
    private Date createTime;
    private Long updateBy;
    private Date updateTime;
}
