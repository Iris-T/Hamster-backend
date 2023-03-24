package cn.iris.hamster.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.iris.hamster.bean.dto.VehicleDto;
import cn.iris.hamster.bean.enums.VehicleStatusEnum;
import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.Vehicle;
import cn.iris.hamster.common.exception.BaseException;
import cn.iris.hamster.common.utils.CommonUtils;
import cn.iris.hamster.service.VehicleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 汽车接口类
 *
 * @author Iris
 * @ClassName VehicleController
 * @date 2023/1/6 16:25
 */

@PreAuthorize("hasAuthority('vehicle:manage')")
@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/list")
    public ResultEntity listByLimit(Vehicle query) {
        CommonUtils.setPageParam(query);
        HashMap<String, Object> data = new HashMap<>();
        data.put("vehicles", vehicleService.listByLimit(query));
        data.put("total", vehicleService.getCountByLimit(query));
        data.put("size", query.getSize());
        return ResultEntity.success(data);
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/{vid}/changeStatus")
    public ResultEntity changeStatus(@PathVariable Long vid, @RequestBody String status) {
        if (StringUtils.isBlank(status) || !VehicleStatusEnum.getKeys().contains(status)) {
            throw new BaseException("参数错误");
        }
        Vehicle vehicle = vehicleService.getById(vid);
        if (ObjectUtils.isEmpty(vehicle)) {
            throw new BaseException("数据不存在");
        }
        vehicle.setStatus(status);
        vehicleService.updateById(vehicle);
        return ResultEntity.success("更新车辆状态成功");
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/add")
    public ResultEntity addVehicle(@RequestBody VehicleDto vehicle) {
        if (ObjectUtils.isEmpty(vehicle)) {
            throw new BaseException("参数有误");
        }
        // 检查车牌是否重复
        long count = vehicleService.count(new QueryWrapper<Vehicle>().eq("plate_no", vehicle.getPlateNo()));
        if (count > 0) {
            throw new BaseException("车牌信息重复");
        }
        Vehicle add = new Vehicle().setId(CommonUtils.randId())
                .setStatus(VehicleStatusEnum.UNUSED.getKey());
        BeanUtil.copyProperties(vehicle, add);
        vehicleService.save(add);
        return ResultEntity.success("添加车辆信息成功");
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/{vid}/update")
    public ResultEntity updateVehicle(@PathVariable Long vid, @RequestBody VehicleDto vehicle) {
        Vehicle update = new Vehicle().setId(vid);
        BeanUtil.copyProperties(vehicle, update);
        vehicleService.updateById(update);
        return ResultEntity.success("更新车辆信息成功");
    }
}
