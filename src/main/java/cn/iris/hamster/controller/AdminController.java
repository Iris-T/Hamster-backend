package cn.iris.hamster.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.bean.dto.PermissionDto;
import cn.iris.hamster.bean.dto.RoleDto;
import cn.iris.hamster.bean.dto.UserDto;
import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.bean.entity.Permission;
import cn.iris.hamster.bean.entity.Role;
import cn.iris.hamster.bean.entity.User;
import cn.iris.hamster.common.exception.BaseException;
import cn.iris.hamster.common.utils.CommonUtils;
import cn.iris.hamster.common.utils.ListUtils;
import cn.iris.hamster.common.utils.UserUtils;
import cn.iris.hamster.service.PermissionService;
import cn.iris.hamster.service.RoleService;
import cn.iris.hamster.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static cn.iris.hamster.common.constants.CommonConstants.STATUS_ENABLE;

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

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/user/{id}/pwd/reset")
    public ResultEntity userPwdReset(@PathVariable Long id) {
        User user = userService.getById(id);
        int len = user.getIdNo().length();
        // 截取末尾6位
        user.setPassword(passwordEncoder.encode(user.getIdNo().substring(len - 6)));
        userService.updateById(user);
        logger.warn("管理员${}重置了用户${}的密码", UserUtils.getUserId(), user.getId());
        return ResultEntity.success("用户密码重置成功");
    }
}
