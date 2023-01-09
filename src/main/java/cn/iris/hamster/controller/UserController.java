package cn.iris.hamster.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.User;
import cn.iris.hamster.common.utils.UserUtils;
import cn.iris.hamster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/userinfo")
    public User getUserInfo() {
        return UserUtils.getUserInfo();
    }

    @PostMapping("/update")
    public ResultEntity updateInfo(@RequestBody User user) {
        return userService.saveUser(user);
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

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/list")
    public ResultEntity userList() {
        List<User> users = userService.list();
        users = users.stream().peek(user -> user.setPassword(null)).collect(Collectors.toList());
        return ResultEntity.success(users);
    }

    @PreAuthorize("hasAuthority('user:query')")
    @GetMapping("/query")
    public ResultEntity userQuery(Long uid) {
        User user = userService.getById(uid);
        if (ObjectUtil.isEmpty(user)) {
            return ResultEntity.error("用户不存在");
        }
        user.setPassword(null);
        return ResultEntity.success(user);
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/save")
    public ResultEntity userSave(User user) {
        return userService.saveUser(user);
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/grant")
    public ResultEntity userGrant(Long uid, @RequestBody List<Long> rids) {
        return userService.userGrant(uid, rids);
    }

}
