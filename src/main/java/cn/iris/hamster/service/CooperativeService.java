package cn.iris.hamster.service;

import cn.iris.hamster.bean.vo.CooperativeVo;
import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.bean.entity.Cooperative;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Iris
* @description 针对表【cooperative(合作企业)】的数据库操作Service
* @createDate 2023-01-05 15:55:35
*/
public interface CooperativeService extends IService<Cooperative> {

    /**
     * 每月新增月结客户（企业）数量
     * @return
     */
    Integer monthlyNewCoCount();

    /**
     * 根据查询条件查询月结客户信息
     * @param query
     * @return
     */
    List<CooperativeVo> listByLimit(Cooperative query);

    /**
     * 根据查询条件返回总结果条数
     * @param query
     * @return
     */
    Integer getCountByLimit(Cooperative query);
}
