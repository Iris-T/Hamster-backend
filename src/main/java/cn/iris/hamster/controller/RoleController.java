package cn.iris.hamster.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.Permission;
import cn.iris.hamster.bean.pojo.Role;
import cn.iris.hamster.common.constants.CommonConstants;
import cn.iris.hamster.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理接口
 *
 * @author Iris
 * @ClassName RoleController
 * @date 2023/1/3 13:58
 */

@RestController
@RequestMapping("/role")
@PreAuthorize("hasRole('admin')")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("list")
    public ResultEntity list(Role role) {
        return ResultEntity.success(roleService.list());
    }

    @PostMapping("isKeyExist")
    public ResultEntity isKeyExist(String key) {
        if (StringUtils.isBlank(key)) {
            return ResultEntity.error("数据为空");
        }

        return roleService.isKeyExist(key) ? ResultEntity.error("关键字重复") : ResultEntity.success("关键字可使用");
    }
}