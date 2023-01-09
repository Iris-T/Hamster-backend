package cn.iris.hamster.mapper;

import cn.iris.hamster.bean.pojo.Cargo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author asus
* @description 针对表【cargo(货物表)】的数据库操作Mapper
* @createDate 2023-01-05 15:04:05
* @Entity cn.iris.hamster.bean.pojo.Cargo
*/

@Mapper
public interface CargoMapper extends BaseMapper<Cargo> {

}




