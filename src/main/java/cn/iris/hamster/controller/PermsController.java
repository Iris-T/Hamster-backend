package cn.iris.hamster.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.Permission;
import cn.iris.hamster.common.constants.CommonConstants;
import cn.iris.hamster.service.PermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 权限操作接口类
 *
 * @author Iris
 * @ClassName PermsController
 * @date 2023/1/3 9:18
 */

@PreAuthorize("hasRole('admin')")
@RestController
@RequestMapping("/perm")
public class PermsController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/save")
    public ResultEntity save(Permission perm) {
        return permissionService.savePerm(perm);
    }

    @GetMapping("/list")
    public ResultEntity list() {
        return ResultEntity.success(permissionService.list());
    }

    @GetMapping("/query")
    public ResultEntity query(Long pid) {
        Permission perm = permissionService.getById(pid);
        if (ObjectUtil.isEmpty(perm)) {
            return ResultEntity.error("数据不存在");
        }
        if (CommonConstants.STATUS_DISABLE.equals(perm.getStatus())) {
            return ResultEntity.error("当前权限已被停用");
        }
        return ResultEntity.success(perm);
    }

    @PostMapping("/changeStatus")
    public ResultEntity changeStatus(Long pid, Integer type) {
        Permission perm = permissionService.getById(pid);
        if (ObjectUtil.isEmpty(perm)) {
            return ResultEntity.error("数据不存在");
        }
        return permissionService.changeStatus(perm, type);
    }

    @PostMapping("/isKeyExist")
    public ResultEntity isKeyExist(String key) {
        if (StringUtils.isBlank(key)) {
            return ResultEntity.error("数据为空");
        }
        return permissionService.isKeyExist(key) ? ResultEntity.error("关键字重复") : ResultEntity.success("关键字可使用");
    }

}
