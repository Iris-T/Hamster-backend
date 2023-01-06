package cn.iris.hamster.controller;

import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.User;
import cn.iris.hamster.common.utils.UserUtils;
import cn.iris.hamster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/bind")
    public ResultEntity bind(Long uid, Long cid) {
        return userService.userBind(uid, cid);
    }

    @PostMapping("/disband")
    public ResultEntity disbind(Long uid) {
        return userService.userDisbind(uid);
    }
}
