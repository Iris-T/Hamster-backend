package cn.iris.hamster.mapper;

import cn.iris.hamster.bean.pojo.Trans;
import cn.iris.hamster.bean.vo.TransVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* @author asus
* @description 针对表【trans(运单表)】的数据库操作Mapper
* @createDate 2023-01-05 16:15:17
* @Entity cn.iris.hamster.bean.pojo.Trans
*/

@Repository
public interface TransMapper extends BaseMapper<Trans> {

    /**
     * 根据限制条件获取运输记录列表
     *
     * @param startIndex
     * @param query
     * @return
     */
    TransVo getTranListByLimit(@Param("start") Integer startIndex, @Param("query") Trans query);

    /**
     * 根据限制条件获取满足条件的总记录数
     * @param query
     * @return
     */
    Integer getTransCountByLimit(@Param("query") Trans query);
}




