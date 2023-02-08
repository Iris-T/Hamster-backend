package cn.iris.hamster.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.bean.dto.QueryDto;
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
    public ResultEntity userList(QueryDto query) throws BaseException {
        HashMap<String, Object> data = new HashMap<>();
        List<UserRoleVo> users = userService.listByLimit(query);
        int total = userService.getCountByLimit(query);
        data.put("users", users);
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

    @PostMapping("/user/add")
    public ResultEntity saveOrUpdate(User user) {
        boolean res = false;
        if (ObjectUtil.isEmpty(user.getId())) {
            // 新增用户
            long cnt = userService.count(new QueryWrapper<User>().eq("ID_NO", user.getIdNo()));
            if (cnt > 0) {
                return ResultEntity.error("身份证件号已被录入，请核对");
            }
            user.setId(CommonUtils.randId());
            if (StringUtils.isEmpty(user.getPassword())) {
                user.setPassword(passwordEncoder.encode("123456"));
            }
            res = userService.save(user);
        } else {
            return ResultEntity.error("参数错误");
        }
        return res ? ResultEntity.success("插入用户数据成功") : ResultEntity.error("插入用户数据失败");
    }

    @PostMapping("/user/update")
    public ResultEntity updateUser(User user) {
        if (ObjectUtil.isNotEmpty(user.getId())) {
            // 更新用户
            long cnt = userService.count(new QueryWrapper<User>().eq("id", user.getId()).eq("status", CommonConstants.STATUS_ENABLE));
            if (cnt < 0) {
                return ResultEntity.error("数据不存在");
            }
        } else {
            return ResultEntity.error("参数错误");
        }
        return userService.updateById(user) ? ResultEntity.success("更新用户数据成功") : ResultEntity.error("更新用户数据失败");
    }

    @PostMapping("/user/grant")
    public ResultEntity changeUserRole(Long uid, Long rid) {
        long cnt1 = userService.count(new QueryWrapper<User>().eq("id", uid).eq("status", CommonConstants.STATUS_ENABLE));
        long cnt2 = roleService.count(new QueryWrapper<Role>().eq("id", rid).eq("status", CommonConstants.STATUS_ENABLE));
        if (cnt1 < 0 || cnt2 < 0) {
            return ResultEntity.error("参数错误");
        }
        return userService.changeUserRole(uid, rid);
    }
}
