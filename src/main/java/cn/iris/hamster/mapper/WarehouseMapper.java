package cn.iris.hamster.mapper;

import cn.iris.hamster.bean.entity.Warehouse;
import cn.iris.hamster.bean.vo.WareHouseVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author asus
* @description 针对表【warehouse(仓库表)】的数据库操作Mapper
* @createDate 2023-01-05 15:56:56
* @Entity cn.iris.hamster.bean.entity.Warehouse
*/

@Repository
public interface WarehouseMapper extends BaseMapper<Warehouse> {

    /**
     * 根据限定条件获取仓库信息列表
     *
     * @param start
     * @param query
     * @return
     */
    List<WareHouseVo> listByLimit(@Param("start") Integer start, @Param("query") Warehouse query);

    /**
     * 根据限定条件获取记录总数
     * @param query
     * @return
     */
    Integer getCountByLimit(@Param("query") Warehouse query);

    /**
     * 通过名称完全匹配返回对象
     * @param name
     * @return
     */
    Warehouse getWhByName(@Param("name") String name);
}




