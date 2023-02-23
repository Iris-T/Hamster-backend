package cn.iris.hamster.bean.vo;

import cn.iris.hamster.bean.pojo.Permission;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 权限角色视图类
 *
 * @author Iris
 * @ClassName RoleVo
 * @date 2023/2/23 9:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleVo {

    private Long id;
    private String name;
    private String rKey;
    private String status;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String remark;
    private List<Permission> perms;
}
