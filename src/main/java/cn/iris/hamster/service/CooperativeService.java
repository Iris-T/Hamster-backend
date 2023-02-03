package cn.iris.hamster.service;

import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.Cooperative;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author asus
* @description 针对表【cooperative(合作企业)】的数据库操作Service
* @createDate 2023-01-05 15:55:35
*/
public interface CooperativeService extends IService<Cooperative> {

    /**
     * 更新存储合作企业信息
     * @param co
     * @return
     */
    ResultEntity saveCo(Cooperative co);


    /**
     * 每月新增合作伙伴数量
     * @return
     */
    Integer monthlyNewCoCount();
}
