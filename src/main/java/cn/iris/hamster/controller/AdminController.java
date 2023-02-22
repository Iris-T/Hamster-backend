package cn.iris.hamster.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.bean.entity.BaseEntity;
import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.Role;
import cn.iris.hamster.bean.pojo.User;
import cn.iris.hamster.bean.vo.UserRoleVo;
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
import java.util.List;

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

    /**
     * 获取用户列表
     *
     * @return
     */
    @GetMapping("/user/list")
    public ResultEntity userList(User query) throws BaseException {
        HashMap<String, Object> data = new HashMap<>();
        List<UserRoleVo> users = userService.listByLimit(query);
        List<Role> roles = roleService.list();
        int total = userService.getCountByLimit(query);
        data.put("users", users);
        data.put("roles", roles);
        data.put("total", total);
        data.put("size", ObjectUtil.isEmpty(query.getSize()));
        return ResultEntity.success(data);
    }

    @PostMapping("/user/{uid}/changeStatus")
    public ResultEntity changeUserStatus(@PathVariable Long uid,@RequestBody String status) throws BaseException {
        if (!CommonUtils.isRightStatus(status)) {
            return ResultEntity.error("参数错误");
        }
        User user = userService.getById(uid);
        if (user.getStatus().equals(status)) {
            // 状态重复
            return CommonConstants.STATUS_ENABLE.equals(status)
                    ? ResultEntity.error("用户已被启用")
                    : ResultEntity.error("用户已被停用");
        }

        user.setStatus(status);
        userService.updateById(user);
        return ResultEntity.success("更新用户状态成功");
    }

    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/user/add")
    public ResultEntity saveOrUpdate(@RequestBody User user) {
        boolean res;
        if (ObjectUtil.isNotEmpty(user.getId())) {
            return ResultEntity.error("参数错误");
        }
        // 核查信息
        long cnt = userService.count(new QueryWrapper<User>().eq("username", user.getUsername()).or().eq("ID_NO", user.getIdNo()));
        if (cnt > 0) {
            return ResultEntity.error("用户信息核对有误或重复，请核对或联系管理员");
        }
        user.setId(CommonUtils.randId())
                .setPassword(StringUtils.isBlank(user.getPassword()) ? passwordEncoder.encode("123456") : passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        if (ObjectUtil.isNotEmpty(user.getRid())) {
            userService.changeUserRole(user.getId(), user.getRid());
        }
        return ResultEntity.success("插入用户数据成功");
    }

    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/user/{uid}/update")
    public ResultEntity updateUser(@PathVariable Long uid, @RequestBody User user) {
        // 核验用户信息
        long cnt = userService.count(new QueryWrapper<User>().eq("id", uid).eq("status", CommonConstants.STATUS_ENABLE));
        if (cnt <= 0) {
            return ResultEntity.error("用户数据不存在");
        }
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

    @GetMapping("/role/list")
    public ResultEntity roleList() throws BaseException {
        return ResultEntity.success(roleService.list());
    }
}
