package cn.iris.hamster.controller;

import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.User;
import cn.iris.hamster.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 系统服务接口
 *
 * @author Iris
 * @ClassName SystemController
 * @date 2022/12/30 14:04
 */

@Api("系统服务操作接口")
@RestController
@RequestMapping("/sys")
@PreAuthorize("hasRole('admin')")
public class SystemController {

    @Autowired
    private UserService userService;

    @ApiOperation("获取用户列表")
    @GetMapping("/user/list")
    public ResultEntity userList() {
        return ResultEntity.success(userService.list());
    }

    @ApiOperation("查询指定用户信息")
    @GetMapping("/user/{uid}")
    public ResultEntity userQuery(@PathVariable Long uid) {
        return ResultEntity.success(userService.getById(uid));
    }

    @ApiOperation("保存用户信息")
    @PostMapping("/user/save")
    public ResultEntity userSave(User user) {
        boolean res = userService.saveOrUpdate(user);
        return res ? ResultEntity.success("更新成功") : ResultEntity.error("更新失败");
    }

}
