package cn.iris.hamster.service.impl;

import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.enums.VehicleStatusEnum;
import cn.iris.hamster.bean.pojo.Warehouse;
import cn.iris.hamster.common.constants.CommonConstants;
import cn.iris.hamster.common.exception.BaseException;
import cn.iris.hamster.common.utils.CommonUtils;
import cn.iris.hamster.mapper.WarehouseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iris.hamster.bean.pojo.Vehicle;
import cn.iris.hamster.service.VehicleService;
import cn.iris.hamster.mapper.VehicleMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author asus
* @description 针对表【vehicle(车辆表)】的数据库操作Service实现
* @createDate 2023-01-05 15:56:46
*/
@Service
public class VehicleServiceImpl extends ServiceImpl<VehicleMapper, Vehicle>
    implements VehicleService{
    @Autowired
    private VehicleMapper vehicleMapper;
    @Autowired
    private WarehouseMapper warehouseMapper;

    @Override
    public ResultEntity add(Vehicle vehicle) {
        if (!isVehicleValid(vehicle)) {
            return ResultEntity.error("数据格式有误");
        }

        //检查关键数据
        vehicle.setId(CommonUtils.randId());
        vehicle.setStatus(VehicleStatusEnum.UNUSED.getKey());

        int cnt = vehicleMapper.insert(vehicle);
        return cnt > 0 ? ResultEntity.success("新增车辆数据成功") : ResultEntity.error("新增车辆数据失败");
    }

    @Override
    public ResultEntity changeStatus(Vehicle vehicle, String type) {
        int flag = 0;
        // 查看类型是否合法
        for (VehicleStatusEnum value : VehicleStatusEnum.values()) {
            if (value.getKey().equals(type)) {
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            return ResultEntity.error("参数错误");
        }
        // 查询车辆使用状态
        if (vehicle.getStatus().equals(type)) {
            return ResultEntity.error("操作无效");
        }
        // 车辆使用中不可闲置或报废
        if (VehicleStatusEnum.WORK.getKey().equals(vehicle.getStatus())) {
            return ResultEntity.error("车辆尚有作业任务,无法修改状态");
        }
        // 进行更新
        vehicle.setStatus(type);
        int cnt = vehicleMapper.updateById(vehicle);
        return cnt > 0 ? ResultEntity.success("修改车辆状态成功") : ResultEntity.error("修改车辆状态失败");
    }

    @Transactional(rollbackFor = BaseException.class)
    @Override
    public ResultEntity changeWh(Long vid, Long wid) {
        // 查询车辆状态
        Vehicle vehicle = vehicleMapper.selectById(vid);
        if (VehicleStatusEnum.WORK.getKey().equals(vehicle.getStatus())) {
            return ResultEntity.error("车辆尚有作业任务,无法修改所在仓库");
        }
        vehicle.setLocalWh(wid);
        int cnt = vehicleMapper.updateById(vehicle);
        return cnt > 0 ? ResultEntity.success("更新车辆所属仓库成功") : ResultEntity.error("更新车辆所属仓库失败");
    }

    private boolean isVehicleValid(Vehicle vehicle) {
        return StringUtils.isNotEmpty(vehicle.getPlateNo())
                && ObjectUtils.isNotEmpty(vehicle.getLoad())
                && ObjectUtils.isNotEmpty(vehicle.getLocalWh());
    }
}




