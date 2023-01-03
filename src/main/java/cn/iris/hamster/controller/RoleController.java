package cn.iris.hamster.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.Permission;
import cn.iris.hamster.bean.pojo.Role;
import cn.iris.hamster.common.constants.CommonConstants;
import cn.iris.hamster.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 角色管理接口
 *
 * @author Iris
 * @ClassName RoleController
 * @date 2023/1/3 13:58
 */

@Api("角色操作接口")
@RestController
@RequestMapping("/role")
@PreAuthorize("hasRole('admin')")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("获取所有角色")
    @GetMapping("list")
    public ResultEntity list(Role role) {
        return ResultEntity.success(roleService.list());
    }

    @ApiOperation("获取指定ID角色")
    @GetMapping("query/{rid}")
    public ResultEntity query(@PathVariable Long rid) {
        Role role = roleService.getById(rid);
        if (ObjectUtil.isEmpty(role)) {
            return ResultEntity.error("数据不存在");
        }
        if (CommonConstants.STATUS_DISABLE.equals(role.getStatus())) {
            return ResultEntity.error("当前角色被停用");
        }
        return ResultEntity.success(role);
    }

    @ApiOperation("保存角色信息")
    @PostMapping("save")
    public ResultEntity save(Role role) {
        boolean res = roleService.saveOrUpdate(role);
        return res ? ResultEntity.success("更新成功") : ResultEntity.error("更新失败");
    }

    @ApiOperation("修改角色启用状态")
    @PostMapping("/changeStatus/{rid}/{type}")
    public ResultEntity changeStatus(@PathVariable Long rid, @PathVariable Integer type) {
        Role role = roleService.getById(rid);
        if (ObjectUtil.isEmpty(role)) {
            ResultEntity.error("数据不存在");
        }
        return roleService.changeStatus(role, type);
    }
}
