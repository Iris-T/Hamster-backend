package cn.iris.hamster.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.iris.hamster.bean.dto.TransDto;
import cn.iris.hamster.bean.enums.CargoStatusEnum;
import cn.iris.hamster.bean.enums.TransStatusEnum;
import cn.iris.hamster.bean.enums.VehicleStatusEnum;
import cn.iris.hamster.bean.pojo.Cargo;
import cn.iris.hamster.bean.pojo.Trans;
import cn.iris.hamster.bean.pojo.Vehicle;
import cn.iris.hamster.bean.pojo.Warehouse;
import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.common.constants.CommonConstants;
import cn.iris.hamster.common.exception.BaseException;
import cn.iris.hamster.common.utils.CommonUtils;
import cn.iris.hamster.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
    @Autowired
    private UserService userService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private CargoService cargoService;

    @PreAuthorize("hasAuthority('trans:manage')")
    @GetMapping("/list")
    public ResultEntity transList(Trans query) {
        CommonUtils.setPageParam(query);
        HashMap<String, Object> data = new HashMap<>();
        data.put("trans", transService.getTransListByLimit(query));
        data.put("whs", warehouseService.list(new QueryWrapper<Warehouse>().select("id", "`name`").eq("`status`", CommonConstants.STATUS_ENABLE)));
        data.put("drivers", userService.getDriverSelectList());
        data.put("vehicles", vehicleService.list(new QueryWrapper<Vehicle>().select("id", "plate_no").eq("`status`", VehicleStatusEnum.UNUSED.getKey())));
        data.put("total", transService.getTransCountByLimit(query));
        data.put("size", query.getSize());
        return ResultEntity.success(data);
    }

    @PreAuthorize("hasRole('admin')")
    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/{id}/changeStatus")
    public ResultEntity changeStatus(@PathVariable Long id, @RequestBody String status) {
        Trans trans = transService.getById(id);
        if (TransStatusEnum.isRightTransStatus(status) && !trans.getStatus().equals(status)) {
            trans.setStatus(status);
            Long endWhId = trans.getEndWhId();
            // 更新货物所在仓库
            if (TransStatusEnum.OVER.getKey().equals(status)) {
                List<Long> cids = transService.getCargoListByTid(id);
                List<Cargo> cargos = cargoService.getBaseMapper().selectBatchIds(cids);
                cargos.forEach(cargo -> cargo.setLocalWh(endWhId).setStatus(CargoStatusEnum.END_TRANS.getKey()));
                cargoService.updateBatchById(cargos);
            }
            transService.updateById(trans);
            return ResultEntity.success("更新状态成功");
        } else {
            throw new BaseException("参数错误");
        }
    }

    @PreAuthorize("hasRole('admin')")
    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/add")
    public ResultEntity addTrans(@RequestBody TransDto trans) {
        if (trans.isValid()) {
            Trans add = new Trans()
                    .setId(CommonUtils.randId())
                    .setStatus(TransStatusEnum.UN_CONFIRM.getKey());
            BeanUtil.copyProperties(trans, add);
            System.out.println(add);
            transService.save(add);
            // 更新货物状态
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
        transService.updateById(update);
        return ResultEntity.success("修改成功");
    }

    @PreAuthorize("hasAuthority('trans:delete')")
    @Transactional(rollbackFor = BaseException.class)
    @PostMapping("/delete")
    public ResultEntity deleteTrans(@RequestBody List<Long> ids) {
        transService.removeBatchByIds(ids);
        return ResultEntity.success("删除成功");
    }
}
