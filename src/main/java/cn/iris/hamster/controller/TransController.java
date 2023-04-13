package cn.iris.hamster.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.iris.hamster.bean.dto.TransDto;
import cn.iris.hamster.bean.enums.TransStatusEnum;
import cn.iris.hamster.bean.pojo.Trans;
import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.common.exception.BaseException;
import cn.iris.hamster.common.utils.CommonUtils;
import cn.iris.hamster.service.TransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 运输作业接口类
 *
 * @author Iris
 * @ClassName TransController
 * @date 2023/1/6 16:27
 */

@RestController
@RequestMapping("/trans")
public class TransController {

    @Autowired
    private TransService transService;

    @PreAuthorize("hasAuthority('trans:manage')")
    @GetMapping("/list")
    public ResultEntity transList(Trans query) {
        CommonUtils.setPageParam(query);
        HashMap<String, Object> data = new HashMap<>();
        data.put("trans", transService.getTransListByLimit(query));
        data.put("total", transService.getTransCountByLimit(query));
        data.put("size", query.getSize());
        return ResultEntity.success(data);
    }

    @PreAuthorize("hasRole('admin')")
    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/{id}/changeStatus")
    public ResultEntity changeStatus(@PathVariable Long id, @RequestBody String status) {
        if (!CommonUtils.isRightStatus(status)) {
            throw new BaseException("参数错误");
        }
        Trans trans = transService.getById(id);
        if (trans.getStatus().equals(status)) {
            throw new BaseException("参数错误");
        }
        trans.setStatus(status);
        transService.updateById(trans);
        return ResultEntity.success("更新状态成功");
    }

    @PreAuthorize("hasRole('admin')")
    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/role/add")
    public ResultEntity addTrans(@RequestBody TransDto trans) {
        if (trans.isValid()) {
            Trans add = new Trans()
                    .setId(CommonUtils.randId())
                    .setStatus(TransStatusEnum.UN_CONFIRM.getValue());
            BeanUtil.copyProperties(trans, add);
            transService.save(add);
            return ResultEntity.success("添加成功");
        }
        return ResultEntity.error("数据错误");
    }

    @PreAuthorize("hasRole('admin')")
    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/{tid}/update")
    public ResultEntity updateTrans(@PathVariable Long tid, @RequestBody Trans trans) {
        Trans update = new Trans().setId(tid);
        BeanUtil.copyProperties(trans, update);
        return ResultEntity.success("修改成功");
    }
}
