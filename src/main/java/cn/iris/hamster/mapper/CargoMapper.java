package cn.iris.hamster.mapper;

import cn.iris.hamster.bean.pojo.Cargo;
import cn.iris.hamster.bean.vo.CargoVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author asus
* @description 针对表【cargo(货物表)】的数据库操作Mapper
* @createDate 2023-01-05 15:04:05
* @Entity cn.iris.hamster.bean.pojo.Cargo
*/

@Repository
public interface CargoMapper extends BaseMapper<Cargo> {

    /**
     * 根据限制条件返回货物视图类列表
     * @param query
     * @return
     * @see cn.iris.hamster.bean.vo.CargoVo
     */
    List<CargoVo> listByLimit(@Param("start") Integer start, @Param("query") Cargo query);

    /**
     * 根据限制条件返回记录总数
     * @param query
     * @return
     */
    Integer getCountByLimit(@Param("query") Cargo query);

    /**
     * 获取选择列表
     * @return
     */
    List<Cargo> getSelectList();
}




