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
 * @ClassName PermController
 * @date 2023/1/3 9:18
 */

@PreAuthorize("hasRole('admin')")
@RestController
@RequestMapping("/perm")
public class PermController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/list")
    public ResultEntity list() {
        return ResultEntity.success(permissionService.list());
    }

    @PostMapping("/isKeyExist")
    public ResultEntity isKeyExist(String key) {
        if (StringUtils.isBlank(key)) {
            return ResultEntity.error("数据为空");
        }
        return permissionService.isKeyExist(key) ? ResultEntity.error("关键字重复") : ResultEntity.success("关键字可使用");
    }
}
