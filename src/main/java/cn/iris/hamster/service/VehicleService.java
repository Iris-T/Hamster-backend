package cn.iris.hamster.service;

import cn.iris.hamster.bean.vo.VehicleVo;
import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.bean.entity.Vehicle;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author asus
* @description 针对表【vehicle(车辆表)】的数据库操作Service
* @createDate 2023-01-05 15:56:46
*/
public interface VehicleService extends IService<Vehicle> {

    /**
     * 根据限制条件查询车辆信息列表
     * @param query
     * @return
     */
    List<VehicleVo> listByLimit(Vehicle query);

    /**
     * 根据限制条件获取记录总条数
     * @param query
     * @return
     */
    Integer getCountByLimit(Vehicle query);
}
