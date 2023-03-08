package cn.iris.hamster.service;

import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.bean.entity.Vehicle;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author asus
* @description 针对表【vehicle(车辆表)】的数据库操作Service
* @createDate 2023-01-05 15:56:46
*/
public interface VehicleService extends IService<Vehicle> {

    /**
     * 添加新的车辆信息
     * @param vehicle
     * @return
     */
    ResultEntity add(Vehicle vehicle);

    /**
     * 修改车辆使用状态
     * @param vehicle
     * @param type
     * @return
     */
    ResultEntity changeStatus(Vehicle vehicle, String type);

    /**
     * 修改车辆所在仓库
     * @param vid
     * @param wid
     * @return
     */
    ResultEntity changeWh(Long vid, Long wid);
}
