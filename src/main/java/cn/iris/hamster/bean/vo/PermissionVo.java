package cn.iris.hamster.bean.vo;

import cn.iris.hamster.bean.pojo.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 权限功能视图类
 *
 * @author Iris
 * @ClassName PermissionVo
 * @date 2023/3/1 16:38
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionVo {

    private Long id;
    private String name;
    private String pKey;
    private String isMenu;
    private String icon;
    private String path;
    private Long parentId;
    private String status;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String remark;
    private List<Permission> children;
}
