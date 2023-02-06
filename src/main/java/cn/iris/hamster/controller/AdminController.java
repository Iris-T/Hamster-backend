package cn.iris.hamster.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.bean.dto.UserQueryDto;
import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.User;
import cn.iris.hamster.bean.vo.UserRoleVo;
import cn.iris.hamster.common.constants.CommonConstants;
import cn.iris.hamster.common.exception.BaseException;
import cn.iris.hamster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 获取用户列表
     * @return
     */
    @GetMapping("/user/list")
    public ResultEntity userList(Integer cur, Integer size, UserQueryDto query) throws BaseException {
        System.out.println(query);
        HashMap<String, Object> data = new HashMap<>();
        List<UserRoleVo> users = userService.listByLimit(cur, size, query);
        int total = userService.getCountByLimit(query);
        data.put("users", users);
        data.put("total", total);
        data.put("size", ObjectUtil.isEmpty(size) ? CommonConstants.DEFAULT_PAGE_SIZE : size);
        return ResultEntity.success(data);
    }
}
