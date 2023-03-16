package cn.iris.hamster.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.iris.hamster.bean.dto.PermissionDto;
import cn.iris.hamster.bean.entity.Permission;
import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.common.exception.BaseException;
import cn.iris.hamster.common.utils.CommonUtils;
import cn.iris.hamster.service.PermissionService;
import cn.iris.hamster.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 权限操作接口类
 *
 * @author Iris
 * @ClassName PermissionController
 * @date 2023/1/3 9:18
 */

@PreAuthorize("hasRole('admin')")
@RestController
@RequestMapping("/perm")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public ResultEntity permList(Permission query) {
        CommonUtils.setPageParam(query);
        HashMap<String, Object> data = new HashMap<>();
        data.put("perms", permissionService.listByLimit(query));
        data.put("total", permissionService.getCountByLimit(query));
        data.put("size", query.getSize());
        return ResultEntity.success(data);
    }

    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/{id}/changeStatus")
    public ResultEntity changeStatus(@PathVariable Long id, @RequestBody String status) {
        if (!CommonUtils.isRightStatus(status)) {
            throw new BaseException("参数错误");
        }
        Permission perm = permissionService.getById(id);
        if (perm.getStatus().equals(status)) {
            throw new BaseException("参数错误");
        }
        perm.setStatus(status);
        permissionService.updateById(perm);
        // 刷新角色权限表内容
        permissionService.changeStatus(id, status);
        return ResultEntity.success("更新状态成功");
    }


    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/add")
    public ResultEntity addPermission(@RequestBody PermissionDto perm) {
        // 核查信息
        long cnt = permissionService.count(new QueryWrapper<Permission>().eq("`key`", perm.getKey()));
        if (cnt > 0) {
            throw new BaseException("信息重复或有误，请核对重试或联系管理员");
        }
        Permission add = new Permission().setStatus(CommonUtils.checkStatus(perm.getStatus()));
        BeanUtil.copyProperties(perm, add);
        permissionService.save(add);
        // 为系统管理员添加权限
        roleService.updateAdminPerms(add.getId(), perm.getStatus());
        return ResultEntity.success("新增权限成功");
    }

    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/{pid}/update")
    public ResultEntity updatePermission(@PathVariable Long pid, @RequestBody PermissionDto perm) {
        Permission update = new Permission().setId(pid);
        BeanUtil.copyProperties(perm, update);
        permissionService.updateById(update);
        return ResultEntity.success("更新权限成功");
    }

    @PostMapping("/isKeyExist")
    public ResultEntity isKeyExist(String key) {
        if (StringUtils.isBlank(key)) {
            return ResultEntity.error("数据为空");
        }
        return permissionService.isKeyExist(key) ? ResultEntity.error("关键字重复") : ResultEntity.success("关键字可使用");
    }
}
