package cn.iris.hamster.mapper;

import cn.iris.hamster.bean.entity.Cargo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author asus
* @description 针对表【cargo(货物表)】的数据库操作Mapper
* @createDate 2023-01-05 15:04:05
* @Entity cn.iris.hamster.bean.entity.Cargo
*/

@Repository
public interface CargoMapper extends BaseMapper<Cargo> {

}




