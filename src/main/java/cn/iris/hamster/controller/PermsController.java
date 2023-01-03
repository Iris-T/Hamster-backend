package cn.iris.hamster.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.Permission;
import cn.iris.hamster.common.constants.CommonConstants;
import cn.iris.hamster.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api("权限操作接口")
@PreAuthorize("hasRole('admin')")
@RestController
@RequestMapping("/perm")
public class PermsController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation("保存权限信息")
    @PostMapping("/save")
    public ResultEntity save(Permission perm) {
        boolean res = permissionService.saveOrUpdate(perm);
        return res ? ResultEntity.success("更新成功")
                : ResultEntity.error("更新失败");
    }

    @ApiOperation("获取所有权限信息")
    @GetMapping("/list")
    public ResultEntity list() {
        return ResultEntity.success(permissionService.list());
    }

    @ApiOperation("获取指定ID权限信息")
    @GetMapping("/query/{pid}")
    public ResultEntity query(@PathVariable Long pid) {
        Permission perm = permissionService.getById(pid);
        if (ObjectUtil.isEmpty(perm)) {
            return ResultEntity.error("数据不存在");
        }
        if (CommonConstants.STATUS_DISABLE.equals(perm.getStatus())) {
            return ResultEntity.error("当前权限被停用");
        }
        return ResultEntity.success(perm);
    }

    @ApiOperation("修改权限启用状态")
    @PostMapping("/changeStatus/{pid}/{type}")
    public ResultEntity changeStatus(@PathVariable Long pid, @PathVariable Integer type) {
        Permission perm = permissionService.getById(pid);
        if (ObjectUtil.isEmpty(perm)) {
            ResultEntity.error("数据不存在");
        }
        return permissionService.changeStatus(perm, type);
    }

}
