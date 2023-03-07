package cn.iris.hamster.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.bean.dto.PermissionDto;
import cn.iris.hamster.bean.dto.RoleDto;
import cn.iris.hamster.bean.dto.UserDto;
import cn.iris.hamster.bean.entity.BaseEntity;
import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.Permission;
import cn.iris.hamster.bean.pojo.Role;
import cn.iris.hamster.bean.pojo.User;
import cn.iris.hamster.bean.vo.PermissionVo;
import cn.iris.hamster.common.exception.BaseException;
import cn.iris.hamster.common.utils.CommonUtils;
import cn.iris.hamster.common.utils.ListUtils;
import cn.iris.hamster.service.PermissionService;
import cn.iris.hamster.service.RoleService;
import cn.iris.hamster.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static cn.iris.hamster.common.constants.CommonConstants.STATUS_ENABLE;

/**
 * 管理员操作接口
 *
 * @author Iris
 * @ClassName AdminController
 * @date 2023/2/3 16:37
 */

@RestController
@PreAuthorize("hasRole('admin')")
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/user/{id}/pwd/reset")
    public ResultEntity userPwdReset(@PathVariable Long id) {
        User user = userService.getById(id);
        int len = user.getIdNo().length();
        // 截取末尾6位
        user.setPassword(passwordEncoder.encode(user.getIdNo().substring(len - 6)));
        userService.updateById(user);
        return ResultEntity.success("用户密码重置成功");
    }

    @GetMapping("/user/list")
    public ResultEntity userList(User query) {
        CommonUtils.setPageParam(query);
        HashMap<String, Object> data = new HashMap<>();
        data.put("users", userService.listByLimit(query));
        data.put("roles", roleService.list(new QueryWrapper<Role>().eq("status", STATUS_ENABLE)));
        data.put("total", userService.getCountByLimit(query));
        data.put("size", query.getSize());
        return ResultEntity.success(data);
    }

    @GetMapping("/role/list")
    public ResultEntity roleList(Role query) {
        CommonUtils.setPageParam(query);
        HashMap<String, Object> data = new HashMap<>();
        data.put("roles", roleService.listByLimit(query));
        data.put("perms", permService.list(new QueryWrapper<Permission>().eq("status", STATUS_ENABLE)));
        data.put("total", roleService.getCountByLimit(query));
        data.put("size", query.getSize());
        return ResultEntity.success(data);
    }

    @GetMapping("/perm/list")
    public ResultEntity permList(Permission query) {
        CommonUtils.setPageParam(query);
        HashMap<String, Object> data = new HashMap<>();
        data.put("perms", permService.listByLimit(query));
        data.put("total", permService.getCountByLimit(query));
        data.put("size", query.getSize());
        return ResultEntity.success(data);
    }

    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/{module}/{id}/changeStatus")
    public ResultEntity changeUserStatus(@PathVariable String module, @PathVariable Long id, @RequestBody String status) {
        if (StringUtils.isBlank(module) || !CommonUtils.isRightStatus(status)) {
            throw new BaseException("参数错误");
        }

        switch (module) {
            case "user": {
                User user = userService.getById(id);
                checkStatus(user, status);
                user.setStatus(status);
                userService.updateById(user);
            }
            case "role": {
                Role role = roleService.getById(id);
                checkStatus(role, status);
                role.setStatus(status);
                roleService.updateById(role);
                // 更新用户角色表内容
                roleService.changeStatus(id, status);
            }
            case "perm": {
                Permission perm = permService.getById(id);
                checkStatus(perm, status);
                perm.setStatus(status);
                permService.updateById(perm);
                // 刷新角色权限表内容
                permService.changeStatus(id, status);
            }
            default: return ResultEntity.success("更新状态成功");
        }
    }

    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/user/add")
    public ResultEntity addUser(@RequestBody UserDto user) {
        if (ObjectUtil.isNotEmpty(user.getId())) {
            throw new BaseException("参数错误");
        }
        // 核查信息
        long cnt = userService.count(new QueryWrapper<User>().eq("username", user.getUsername()).or().eq("ID_NO", user.getIdNo()));
        if (cnt > 0) {
            throw new BaseException("用户信息核对有误或重复，请核对后重试或联系管理员");
        }
        User add = new User();
        BeanUtil.copyProperties(user, add);
        add.setId(CommonUtils.randId()).setPassword(StringUtils.isBlank(add.getPassword()) ? passwordEncoder.encode(add.getIdNo().substring(add.getIdNo().length() - 6)) : passwordEncoder.encode(add.getPassword()));
        userService.save(add);
        if (ObjectUtil.isNotEmpty(user.getRid())) {
            userService.changeUserRole(user.getId(), user.getRid());
        }
        return ResultEntity.success("新增用户数据成功");
    }

    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/user/{uid}/update")
    public ResultEntity updateUser(@PathVariable Long uid, @RequestBody UserDto user) {
        user.setId(uid);
        // 更新用户权限
        if (ObjectUtil.isNotEmpty(user.getRid())) {
            userService.changeUserRole(user.getId(), user.getRid());
        }
        User update = new User();
        BeanUtil.copyProperties(user, update);
        userService.updateById(update);
        return ResultEntity.success("更新用户数据成功");
    }

    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/role/add")
    public ResultEntity addRole(@RequestBody RoleDto role) {
        // 核查信息
        long cnt = roleService.count(new QueryWrapper<Role>().eq("r_key", role.getRKey()));
        if (cnt > 0) {
            throw new BaseException("信息重复或有误，请核对后重试或联系管理员");
        }
        Role insert = new Role();
        BeanUtil.copyProperties(role, insert);
        roleService.save(insert);
        return ResultEntity.success("新增权限角色成功");
    }

    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/role/{rid}/update")
    public ResultEntity updateRole(@PathVariable Long rid, @RequestBody RoleDto role) {
        Role update = new Role().setId(rid);
        BeanUtil.copyProperties(role, update);
        List<Long> pids = ListUtils.listToKeys(update.getPerms(), Permission::getId);
        roleService.deleteR_P(rid);
        if (pids.size() > 0) {
            roleService.updateR_P(rid, pids);
        }
        roleService.updateById(update);
        return ResultEntity.success("更新角色数据成功");
    }

    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/perm/add")
    public ResultEntity addPermission(@RequestBody PermissionDto perm) {
        // 核查信息
        long cnt = permService.count(new QueryWrapper<Permission>().eq("p_key", perm.getPKey()));
        if (cnt > 0) {
            throw new BaseException("信息重复或有误，请核对重试或联系管理员");
        }
        Permission add = new Permission();
        BeanUtil.copyProperties(perm, add);
        permService.save(add);
        // 为系统管理员添加权限
        roleService.updateAdminPerms(add.getId(), add.getStatus());
        return ResultEntity.success("新增权限成功");
    }

    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/perm/{pid}/update")
    public ResultEntity updatePermission(@PathVariable Long pid, @RequestBody PermissionDto perm) {
        Permission update = new Permission().setId(pid);
        BeanUtil.copyProperties(perm, update);
        permService.update(new UpdateWrapper<Permission>().set(update.getParentId() == null, "parent_id", update.getParentId()).eq("id", pid));
        permService.updateById(update);
        return ResultEntity.success("更新权限成功");
    }

    private <T> void checkStatus(T t, String status) {
        if (ObjectUtil.isEmpty(t)) {
            logger.error("参数错误");
            throw new BaseException("参数错误");
        }
        if (t instanceof User u && u.getStatus().equals(status)) {
            throw new BaseException("错误操作");
        }
        if (t instanceof Role r && r.getStatus().equals(status)) {
            throw new BaseException("错误操作");
        }
        if (t instanceof Permission p && p.getStatus().equals(status)) {
            throw new BaseException("错误操作");
        }
    }
}
