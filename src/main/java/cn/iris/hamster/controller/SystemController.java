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

}
