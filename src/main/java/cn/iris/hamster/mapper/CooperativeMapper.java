package cn.iris.hamster.mapper;

import cn.iris.hamster.bean.pojo.Cooperative;
import cn.iris.hamster.bean.vo.CooperativeVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author asus
* @description 针对表【cooperative(合作企业)】的数据库操作Mapper
* @createDate 2023-01-05 15:55:35
* @Entity cn.iris.hamster.bean.pojo.Cooperative
*/

@Repository
public interface CooperativeMapper extends BaseMapper<Cooperative> {

    /**
     * 每月新增月结客户（企业）数
     * @return
     */
    Integer monthlyNewCoCount();

    /**
     * 根据查询条件查询月结客户信息
     * @param query
     * @return
     */
    List<CooperativeVo> listByLimit(@Param("query") Cooperative query);

    /**
     * 根据查询条件返回总结果条数
     * @param query
     * @return
     */
    Integer getCountByLimit(@Param("query") Cooperative query);
}




