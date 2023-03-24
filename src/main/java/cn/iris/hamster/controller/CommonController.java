package cn.iris.hamster.controller;

import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.User;
import cn.iris.hamster.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 共用接口
 *
 * @author Iris
 * @ClassName CommonController
 * @date 2023/2/21 9:44
 */

@RestController
public class CommonController {
    @Autowired
    private UserService userService;

    /**
     * 检查用户身份证号重复
     * @param idNo 身份证号
     * @return
     */
    @PostMapping("/check/user/idNo")
    public ResultEntity checkUserIdNo(@RequestBody String idNo) {
        long cnt = userService.count(new QueryWrapper<User>().eq("ID_NO", idNo));
        return cnt == 0 ? ResultEntity.success() : ResultEntity.error();
    }

    /**
     * 检查用户用户名重复
     * @param username 用户名
     * @return
     */
    @PostMapping("/check/user/username")
    public ResultEntity checkUserUsername(@RequestBody String username) {
        long cnt = userService.count(new QueryWrapper<User>().eq("username", username));
        return cnt == 0 ? ResultEntity.success() : ResultEntity.error();
    }
}
