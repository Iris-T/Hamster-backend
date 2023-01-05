package cn.iris.hamster.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iris.hamster.bean.pojo.Vehicle;
import cn.iris.hamster.service.VehicleService;
import cn.iris.hamster.mapper.VehicleMapper;
import org.springframework.stereotype.Service;

/**
* @author asus
* @description 针对表【vehicle(车辆表)】的数据库操作Service实现
* @createDate 2023-01-05 15:56:46
*/
@Service
public class VehicleServiceImpl extends ServiceImpl<VehicleMapper, Vehicle>
    implements VehicleService{

}




