package cn.iris.hamster.controller;

import cn.iris.hamster.bean.pojo.User;
import cn.iris.hamster.common.utils.UserUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/userinfo")
    public User getUserInfo() {
        return UserUtils.getUserInfo();
    }
}
