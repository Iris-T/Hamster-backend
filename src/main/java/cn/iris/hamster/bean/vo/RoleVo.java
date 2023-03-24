package cn.iris.hamster.bean.vo;

import cn.iris.hamster.bean.pojo.Permission;
import cn.iris.hamster.common.bean.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 权限角色视图类
 *
 * @author Iris
 * @ClassName RoleVo
 * @date 2023/2/23 9:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleVo extends BaseVo {

    private String name;
    private String key;
    private List<Permission> perms;
}
