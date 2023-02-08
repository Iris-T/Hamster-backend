package cn.iris.hamster.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.Cooperative;
import cn.iris.hamster.common.utils.UserUtils;
import cn.iris.hamster.service.CooperativeService;
import cn.iris.hamster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 合作企业接口类
 *
 * @author Iris
 * @ClassName CoController
 * @date 2023/1/6 9:56
 */

@PreAuthorize("hasAuthority('co:op')")
@RequestMapping("co")
@RestController
public class CoController {
    @Autowired
    private CooperativeService cooperativeService;
    @Autowired
    private UserService userService;

    /**
     * 添加或更新企业信息，仅限系统管理员和企业管理员操作
     * @param co
     * @return
     */
    @PostMapping("save")
    public ResultEntity save(Cooperative co) {
        return cooperativeService.saveCo(co);
    }

    @PostMapping("/userBind")
    public ResultEntity userBind(Long uid, Long cid) {
        return userService.userBind(uid, cid);
    }

    @PostMapping("/userDisbind")
    public ResultEntity userDisbind(Long uid) {
        return userService.userDisbind(uid);
    }
}
