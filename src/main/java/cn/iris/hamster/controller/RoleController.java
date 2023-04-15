package cn.iris.hamster.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.iris.hamster.bean.dto.RoleDto;
import cn.iris.hamster.bean.pojo.Permission;
import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.Role;
import cn.iris.hamster.common.exception.BaseException;
import cn.iris.hamster.common.utils.CommonUtils;
import cn.iris.hamster.common.utils.ListUtils;
import cn.iris.hamster.service.PermissionService;
import cn.iris.hamster.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static cn.iris.hamster.common.constants.CommonConstants.STATUS_ENABLE;

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
    @Autowired
    private PermissionService permService;

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/list")
    public ResultEntity roleList(Role query) {
        CommonUtils.setPageParam(query);
        HashMap<String, Object> data = new HashMap<>();
        data.put("roles", roleService.listByLimit(query));
        data.put("perms", permService.list(new QueryWrapper<Permission>().eq("status", STATUS_ENABLE)));
        data.put("total", roleService.getCountByLimit(query));
        data.put("size", query.getSize());
        return ResultEntity.success(data);
    }

    @PreAuthorize("hasRole('admin')")
    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/{id}/changeStatus")
    public ResultEntity changeStatus(@PathVariable Long id, @RequestBody String status) {
        if (!CommonUtils.isRightStatus(status)) {
            throw new BaseException("参数错误");
        }
        Role role = roleService.getById(id);
        if (role.getStatus().equals(status)) {
            throw new BaseException("参数错误");
        }
        role.setStatus(status);
        roleService.updateById(role);
        // 更新用户角色表内容
        roleService.changeStatus(id, status);
        return ResultEntity.success("更新状态成功");
    }

    @PreAuthorize("hasRole('admin')")
    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/add")
    public ResultEntity addRole(@RequestBody RoleDto role) {
        // 核查信息
        long cnt = roleService.count(new QueryWrapper<Role>().eq("key", role.getKey()));
        if (cnt > 0) {
            throw new BaseException("信息重复或有误，请核对后重试或联系管理员");
        }
        Role insert = new Role();
        BeanUtil.copyProperties(role, insert);
        roleService.save(insert);
        List<Long> pids = ListUtils.listToKeys(role.getPerms(), Permission::getId);
        roleService.updateR_P(insert.getId(), pids);
        return ResultEntity.success("新增权限角色成功");
    }

    @PreAuthorize("hasRole('admin')")
    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/{rid}/update")
    public ResultEntity updateRole(@PathVariable Long rid, @RequestBody RoleDto role) {
        Role update = new Role().setId(rid);
        BeanUtil.copyProperties(role, update);
        List<Long> pids = ListUtils.listToKeys(role.getPerms(), Permission::getId);
        // 先更新权限
        roleService.deleteR_P(rid);
        if (pids.size() > 0) {
            roleService.updateR_P(rid, pids);
        }
        roleService.updateById(update);
        return ResultEntity.success("更新角色数据成功");
    }

    @PostMapping("/isKeyExist")
    public ResultEntity isKeyExist(String key) {
        if (StringUtils.isBlank(key)) {
            return ResultEntity.error("数据为空");
        }

        return roleService.isKeyExist(key) ? ResultEntity.error("关键字重复") : ResultEntity.success("关键字可使用");
    }
}