package cn.iris.hamster.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.User;
import cn.iris.hamster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统服务接口
 *
 * @author Iris
 * @ClassName SystemController
 * @date 2022/12/30 14:04
 */

@RestController
@RequestMapping("/sys")
@PreAuthorize("hasRole('admin')")
public class SystemController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/list")
    public ResultEntity userList() {
        List<User> users = userService.list();
        users = users.stream().peek(user -> user.setPassword(null)).collect(Collectors.toList());
        return ResultEntity.success(users);
    }

    @GetMapping("/user")
    public ResultEntity userQuery(Long uid) {
        User user = userService.getById(uid);
        if (ObjectUtil.isEmpty(user)) {
            return ResultEntity.error("用户不存在");
        }
        user.setPassword(null);
        return ResultEntity.success(user);
    }

    @PostMapping("/user/save")
    public ResultEntity userSave(User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/user/grant")
    public ResultEntity userGrant(Long uid, @RequestBody List<Long> rids) {
        return userService.userGrant(uid, rids);
    }

    @PostMapping("/co/addAdmin")
    public ResultEntity addCoAdmin(Long uid, Long cid) {
        if (ObjectUtil.isEmpty(uid) || ObjectUtil.isEmpty(cid)) {
            return ResultEntity.error("请求参数错误");
        }
        return userService.addCoAdmin(uid, cid);
    }

    @PostMapping("/co/delAdmin")
    public ResultEntity delAdmin(Long uid) {
        if (ObjectUtil.isEmpty(uid)) {
            return ResultEntity.error("请求参数错误");
        }
        return userService.delCoAdmin(uid);
    }

    @PostMapping("/co/bind")
    public ResultEntity userBind(Long uid, Long cid) {
        return userService.userBind(uid, cid);
    }

    @PostMapping("/co/disbind")
    public ResultEntity userDisbind(Long uid) {
        return userService.userDisbind(uid);
    }

}
