package cn.iris.hamster.service.impl;

import cn.iris.hamster.bean.vo.VehicleVo;
import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.bean.enums.VehicleStatusEnum;
import cn.iris.hamster.common.exception.BaseException;
import cn.iris.hamster.common.utils.CommonUtils;
import cn.iris.hamster.mapper.WarehouseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iris.hamster.bean.entity.Vehicle;
import cn.iris.hamster.service.VehicleService;
import cn.iris.hamster.mapper.VehicleMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<VehicleVo> listByLimit(Vehicle query) {
        return baseMapper.listByLimit(query.getStartIndex(), query);
    }

    @Override
    public Integer getCountByLimit(Vehicle query) {
        return baseMapper.getCountByLimit(query);
    }

    private boolean isVehicleValid(Vehicle vehicle) {
        return StringUtils.isNotEmpty(vehicle.getPlateNo())
                && ObjectUtils.isNotEmpty(vehicle.getLoad())
                && ObjectUtils.isNotEmpty(vehicle.getLocalWh());
    }
}




