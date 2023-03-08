package cn.iris.hamster.mapper;

import cn.iris.hamster.bean.entity.SystemField;
import cn.iris.hamster.bean.vo.SystemFieldVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author asus
* @description 针对表【system_field(系统字段表)】的数据库操作Mapper
* @createDate 2023-02-02 15:21:30
* @Entity cn.iris.hamster.bean.entity.SystemField
*/

@Repository
public interface SystemFieldMapper extends BaseMapper<SystemField> {

    /**
     * 按限制条件返回分页数据
     * @param startIndex
     * @param query
     * @return
     */
    List<SystemFieldVo> listByLimit(@Param("start") Integer startIndex, @Param("query") SystemField query);

    /**
     * 按限制条件返回符合记录的总数
     * @param query
     * @return
     */
    Integer getCountByLimit(@Param("query") SystemField query);
}




