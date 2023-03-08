package cn.iris.hamster.mapper;

import cn.iris.hamster.bean.entity.Warehouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author asus
* @description 针对表【warehouse(仓库表)】的数据库操作Mapper
* @createDate 2023-01-05 15:56:56
* @Entity cn.iris.hamster.bean.entity.Warehouse
*/

@Repository
public interface WarehouseMapper extends BaseMapper<Warehouse> {
}




