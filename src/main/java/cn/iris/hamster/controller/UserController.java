package cn.iris.hamster.controller;

import cn.iris.hamster.bean.dto.RePwdDto;
import cn.iris.hamster.bean.dto.UserReProfileDto;
import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.common.utils.UserUtils;
import cn.iris.hamster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
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
