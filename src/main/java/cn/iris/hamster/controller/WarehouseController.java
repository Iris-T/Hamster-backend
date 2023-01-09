package cn.iris.hamster.controller;

import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.Warehouse;
import cn.iris.hamster.service.WarehouseService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 仓库服务接口类
 *
 * @author Iris
 * @ClassName WarehouseController
 * @date 2023/1/6 16:26
 */

@PreAuthorize("hasAnyRole('admin', 'wh_keeper')")
@RestController
@RequestMapping("wh")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping("save")
    public ResultEntity save(Warehouse wh) {
        return warehouseService.saveWh(wh);
    }

    @PostMapping("changeStatus")
    public ResultEntity changeStatus(Long wid, String type) {
        Warehouse wh = warehouseService.getById(wid);
        if (ObjectUtils.isEmpty(wh)) {
            return ResultEntity.error("数据不存在");
        }
        return warehouseService.changeStatus(wh, type);
    }

    @PostMapping("changeType")
    public ResultEntity changeType(Long wid, String type) {
        Warehouse wh = warehouseService.getById(wid);
        if (ObjectUtils.isEmpty(wh)) {
            return ResultEntity.error("数据不存在");
        }
        return warehouseService.changeType(wh, type);
    }

}
