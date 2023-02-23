package cn.iris.hamster.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.Role;
import cn.iris.hamster.bean.pojo.User;
import cn.iris.hamster.common.constants.CommonConstants;
import cn.iris.hamster.common.exception.BaseException;
import cn.iris.hamster.common.utils.CommonUtils;
import cn.iris.hamster.service.RoleService;
import cn.iris.hamster.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/user/list")
    public ResultEntity userList(User query) {
        CommonUtils.setPageParam(query);
        HashMap<String, Object> data = new HashMap<>();
        data.put("users", userService.listByLimit(query));
        data.put("roles", roleService.list(new QueryWrapper<Role>().eq("status", CommonConstants.STATUS_ENABLE)));
        data.put("total", userService.getCountByLimit(query));
        data.put("size", query.getSize());
        return ResultEntity.success(data);
    }

    @GetMapping("/role/list")
    public ResultEntity roleList(Role query) {
        CommonUtils.setPageParam(query);
        HashMap<String, Object> data = new HashMap<>();
        data.put("roles", roleService.listByLimit(query));
        data.put("total", roleService.getCountByLimit(query));
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
                if (user.getStatus().equals(status)) {
                    throw new BaseException("错误操作");
                }
                user.setStatus(status);
                userService.updateById(user);
            }
            case "role": {
                Role role = roleService.getById(id);
                if (role.getStatus().equals(status)) {
                    throw new BaseException("错误操作");
                }
                role.setStatus(status);
                roleService.updateById(role);
                // 更新用户角色表内容
                roleService.changeStatus(id, status);
            }
            default: return ResultEntity.success("更新状态成功");
        }
    }

    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/user/add")
    public ResultEntity addUser(@RequestBody User user) {
        if (ObjectUtil.isNotEmpty(user.getId())) {
            throw new BaseException("参数错误");
        }
        // 核查信息
        long cnt = userService.count(new QueryWrapper<User>().eq("username", user.getUsername()).or().eq("ID_NO", user.getIdNo()));
        if (cnt > 0) {
            throw new BaseException("用户信息核对有误或重复，请核对后重试或联系管理员");
        }
        user.setId(CommonUtils.randId())
                .setPassword(StringUtils.isBlank(user.getPassword()) ? passwordEncoder.encode("123456") : passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        if (ObjectUtil.isNotEmpty(user.getRid())) {
            userService.changeUserRole(user.getId(), user.getRid());
        }
        return ResultEntity.success("新增用户数据成功");
    }

    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/user/{uid}/update")
    public ResultEntity updateUser(@PathVariable Long uid, @RequestBody User user) {
        user.setId(uid);
        // 重设密码
        if (ObjectUtil.isNotEmpty(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        // 更新用户权限
        if (ObjectUtil.isNotEmpty(user.getRid())) {
            userService.changeUserRole(user.getId(), user.getRid());
        }
        userService.updateById(user);
        return ResultEntity.success("更新用户数据成功");
    }

    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/role/add")
    public ResultEntity addRole(@RequestBody Role role) {
        if (ObjectUtil.isNotEmpty(role.getId())) {
            throw new BaseException("参数错误");
        }
        // 核查信息
        long cnt = roleService.count(new QueryWrapper<Role>().eq("r_key", role.getRKey()));
        if (cnt > 0) {
            throw new BaseException("角色信息何查有误或重复，请核对后重试或联系管理员");
        }
        role.setId(CommonUtils.randId());
        roleService.save(role);
        return ResultEntity.success("新增权限角色成功");
    }

    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/role/{rid}/update")
    public ResultEntity updateRole(@PathVariable Long rid, @RequestBody Role role) {
        role.setId(rid);
        roleService.updateById(role);
        return ResultEntity.success("更新角色数据成功");
    }
}
