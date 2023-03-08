package cn.iris.hamster.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.bean.dto.RePwdDto;
import cn.iris.hamster.bean.dto.UserDto;
import cn.iris.hamster.bean.dto.UserReProfileDto;
import cn.iris.hamster.bean.entity.Role;
import cn.iris.hamster.bean.entity.User;
import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.common.exception.BaseException;
import cn.iris.hamster.common.utils.CommonUtils;
import cn.iris.hamster.common.utils.UserUtils;
import cn.iris.hamster.service.RoleService;
import cn.iris.hamster.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iris.hamster.common.constants.CommonConstants.STATUS_ENABLE;

/**
 * 用户服务接口
 *
 * @author Iris
 * @ClassName UserController
 * @date 2022/12/30 14:03
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/rePwd")
    public ResultEntity rePwd(@RequestBody RePwdDto rePwdDto) {
        if (rePwdDto.isAnyBlank()) {
            return ResultEntity.error("错误的请求参数");
        }
        if (rePwdDto.getOldPassword().equals(rePwdDto.getNewPassword())) {
            return ResultEntity.error("新旧密码不能相同");
        }
        if (!rePwdDto.getNewPassword().equals(rePwdDto.getReConfirmPassword())) {
            return ResultEntity.error("两次输入密码不同");
        }
        return userService.rePwd(rePwdDto);
    }

    @PostMapping("/reProfile")
    public ResultEntity updateInfo(@RequestBody UserReProfileDto user) {
        return userService.reProfile(user);
    }

    @GetMapping("/userinfo")
    public ResultEntity getUserInfo() {
        Map<String, Object> res = new HashMap<>();
        res.put("info", UserUtils.getUserInfo());
        // 将角色和权限封装为list
        res.put("authority", UserUtils.getAuthority().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        // 封装用户菜单数据
        res.put("menu", userService.getMenu());
        return ResultEntity.success(res);
    }

    @GetMapping("/menu")
    public ResultEntity getMenu() {
        return ResultEntity.success(userService.getMenu());
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/list")
    public ResultEntity userList(User query) {
        CommonUtils.setPageParam(query);
        HashMap<String, Object> data = new HashMap<>();
        data.put("users", userService.listByLimit(query));
        data.put("roles", roleService.list(new QueryWrapper<Role>().eq("status", STATUS_ENABLE)));
        data.put("total", userService.getCountByLimit(query));
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
        User user = userService.getById(id);
        if (user.getStatus().equals(status)) {
            throw new BaseException("参数错误");
        }
        user.setStatus(status);
        userService.updateById(user);
        return ResultEntity.success("更新状态成功");
    }

    @PreAuthorize("hasRole('admin')")
    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/add")
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

    @PreAuthorize("hasRole('admin')")
    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/{uid}/update")
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

    @PreAuthorize("hasAuthority('co:user:bind')")
    @PostMapping("/bind")
    public ResultEntity bind(Long uid, Long cid) {
        return userService.userBind(uid, cid);
    }

    @PreAuthorize("hasAuthority('co:user:bind')")
    @PostMapping("/disband")
    public ResultEntity disbind(Long uid) {
        return userService.userDisbind(uid);
    }
}
