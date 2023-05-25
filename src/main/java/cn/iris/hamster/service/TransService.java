package cn.iris.hamster.service;

import cn.iris.hamster.bean.pojo.Trans;
import cn.iris.hamster.bean.vo.TransVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Iris
* @description 针对表【trans(运单表)】的数据库操作Service
* @createDate 2023-01-05 16:15:17
*/
public interface TransService extends IService<Trans> {

    /**
     * 根据限制条件获取运输记录列表
     *
     * @param query
     * @return
     */
    List<TransVo> getTransListByLimit(Trans query);

    /**
     * 根据限制条件获取满足条件的总记录数
     * @param query
     * @return
     */
    Integer getTransCountByLimit(Trans query);

    /**
     * 获取运单绑定的货物id列表
     * @param id
     * @return
     */
    List<Long> getCargoListByTid(Long id);
}
