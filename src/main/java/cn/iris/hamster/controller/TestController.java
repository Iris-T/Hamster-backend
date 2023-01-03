package cn.iris.hamster.controller;

import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.common.utils.UserUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 *
 * @author Iris
 * @ClassName TestController
 * @date 2022/12/30 13:59
 */

@RestController
@RequestMapping("/test")
public class TestController {

    @PreAuthorize("hasRole('admin')")
    @RequestMapping("/userinfo")
    public ResultEntity getCurrentUserInfo() {
        return ResultEntity.success(UserUtils.getUserInfo());
    }
}
