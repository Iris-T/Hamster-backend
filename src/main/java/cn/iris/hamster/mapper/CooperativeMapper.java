package cn.iris.hamster.mapper;

import cn.iris.hamster.bean.pojo.Cooperative;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author asus
* @description 针对表【cooperative(合作企业)】的数据库操作Mapper
* @createDate 2023-01-05 15:55:35
* @Entity cn.iris.hamster.bean.pojo.Cooperative
*/

@Repository
public interface CooperativeMapper extends BaseMapper<Cooperative> {

    /**
     * 每月新增合作伙伴数
     * @return
     */
    Integer monthlyNewCoCount();
}




