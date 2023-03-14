package cn.iris.hamster.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.iris.hamster.bean.dto.CooperativeDto;
import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.bean.entity.Cooperative;
import cn.iris.hamster.common.utils.CommonUtils;
import cn.iris.hamster.service.CooperativeService;
import cn.iris.hamster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 合作企业接口类
 *
 * @author Iris
 * @ClassName CooperativeController
 * @date 2023/1/6 9:56
 */

@PreAuthorize("hasAuthority('co:manage')")
@RequestMapping("/co")
@RestController
public class CooperativeController {
    @Autowired
    private CooperativeService cooperativeService;
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResultEntity listByLimit(Cooperative query) {
        CommonUtils.setPageParam(query);
        HashMap<String, Object> data = new HashMap<>();
        data.put("cos", cooperativeService.listByLimit(query));
        data.put("total", cooperativeService.getCountByLimit(query));
        data.put("size", query.getSize());
        return ResultEntity.success(data);
    }

    @PostMapping("/{cid}/update")
    public ResultEntity updateCo(@PathVariable Long cid, @RequestBody CooperativeDto co) {
        Cooperative update = new Cooperative().setId(cid);
        BeanUtil.copyProperties(co, update);
        cooperativeService.updateById(update);
        return ResultEntity.success("更新客户信息成功");
    }

    @PostMapping("/add")
    public ResultEntity updateCo(@RequestBody CooperativeDto co) {
        Cooperative update = new Cooperative().setId(CommonUtils.randId());
        BeanUtil.copyProperties(co, update);
        cooperativeService.updateById(update);
        return ResultEntity.success("更新客户信息成功");
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
