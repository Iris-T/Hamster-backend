package cn.iris.hamster.mapper;

import cn.iris.hamster.bean.pojo.Vehicle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author asus
* @description 针对表【vehicle(车辆表)】的数据库操作Mapper
* @createDate 2023-01-05 15:56:46
* @Entity cn.iris.hamster.bean.pojo.Vehicle
*/

@Mapper
public interface VehicleMapper extends BaseMapper<Vehicle> {

}




