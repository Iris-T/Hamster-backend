package cn.iris.hamster.service;

import cn.iris.hamster.bean.pojo.Trans;
import cn.iris.hamster.bean.vo.TransVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Iris
* @description 针对表【trans(运单表)】的数据库操作Service
* @createDate 2023-01-05 16:15:17
*/
public interface TransService extends IService<Trans> {

    /**
     * 根据限制条件获取运输记录列表
     * @param query
     * @return
     */
    TransVo getTransListByLimit(Trans query);

    /**
     * 根据限制条件获取满足条件的总记录数
     * @param query
     * @return
     */
    Integer getTransCountByLimit(Trans query);
}
