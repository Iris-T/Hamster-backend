package cn.iris.hamster.bean.vo;

import cn.iris.hamster.bean.pojo.Permission;
import cn.iris.hamster.common.bean.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 权限功能视图类
 *
 * @author Iris
 * @ClassName PermissionVo
 * @date 2023/3/1 16:38
 */

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionVo extends BaseVo {

    private String name;
    private String key;
    private String isMenu;
    private String icon;
    private String path;
    private Long parentId;
    private List<Permission> children;
}
