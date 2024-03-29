package cn.iris.hamster.mapper;

import cn.iris.hamster.bean.pojo.CargoType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author asus
* @description 针对表【cargo_type(系统字段-货物类型表)】的数据库操作Mapper
* @createDate 2023-03-23 09:53:29
* @Entity cn.iris.hamster.bean.pojo.CargoType
*/
public interface CargoTypeMapper extends BaseMapper<CargoType> {

    List<CargoType> listByLimit(@Param("query") CargoType query, @Param("start") Integer start);

    Integer getCountByLimit(@Param("query") CargoType query);
}




