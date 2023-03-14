package cn.iris.hamster.mapper;

import cn.iris.hamster.bean.entity.Vehicle;
import cn.iris.hamster.bean.vo.VehicleVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author asus
* @description 针对表【vehicle(车辆表)】的数据库操作Mapper
* @createDate 2023-01-05 15:56:46
* @Entity cn.iris.hamster.bean.entity.Vehicle
*/

@Repository
public interface VehicleMapper extends BaseMapper<Vehicle> {

    /**
     * 根据限制条件查询车辆信息列表
     *
     * @param start
     * @param query
     * @return
     */
    List<VehicleVo> listByLimit(@Param("start") Integer start, @Param("query") Vehicle query);

    /**
     * 根据限制条件获取记录总条数
     * @param query
     * @return
     */
    Integer getCountByLimit(@Param("query") Vehicle query);
}




