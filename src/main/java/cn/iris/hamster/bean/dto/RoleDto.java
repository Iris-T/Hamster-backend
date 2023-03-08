package cn.iris.hamster.bean.dto;

import cn.iris.hamster.bean.entity.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 角色更新参数类
 *
 * @author Iris
 * @ClassName RoleDto
 * @date 2023/2/24 9:09
 */

@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private String name;
    private String key;
    private String remark;
    /**
     * 菜单、功能id列表
     */
    private List<Permission> perms;
}
