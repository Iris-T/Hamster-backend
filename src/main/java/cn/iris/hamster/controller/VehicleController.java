package cn.iris.hamster.controller;

import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.Vehicle;
import cn.iris.hamster.bean.pojo.Warehouse;
import cn.iris.hamster.service.VehicleService;
import cn.iris.hamster.service.WarehouseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 汽车接口类
 *
 * @author Iris
 * @ClassName VehicleController
 * @date 2023/1/6 16:25
 */

@PreAuthorize("hasAuthority('vehicle:op')")
@RestController
@RequestMapping("vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping("add")
    public ResultEntity add(Vehicle vehicle) {
        return vehicleService.add(vehicle);
    }

    @PostMapping("changeStatus")
    public ResultEntity changeStatus(Long vid, String type) {
        Vehicle vehicle = vehicleService.getById(vid);
        if (ObjectUtils.isEmpty(vehicle)) {
            return ResultEntity.error("数据不存在");
        }

        return vehicleService.changeStatus(vehicle, type);
    }

    @PostMapping("changeWh")
    public ResultEntity changeWh(Long vid, Long wid) {
        return vehicleService.changeWh(vid, wid);
    }
}
