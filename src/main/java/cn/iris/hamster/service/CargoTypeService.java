package cn.iris.hamster.service;

import cn.iris.hamster.bean.pojo.CargoType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author asus
* @description 针对表【cargo_type(系统字段-货物类型表)】的数据库操作Service
* @createDate 2023-03-23 09:53:29
*/
public interface CargoTypeService extends IService<CargoType> {

    List<CargoType> listByLimit(CargoType query);

    Integer getCountByLimit(CargoType query);
}
