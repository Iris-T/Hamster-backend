package cn.iris.hamster.service;

import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.Warehouse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author asus
* @description 针对表【warehouse(仓库表)】的数据库操作Service
* @createDate 2023-01-05 15:56:56
*/
public interface WarehouseService extends IService<Warehouse> {

    /**
     * 更新存储仓库信息
     * @param wh
     * @return
     */
    ResultEntity saveWh(Warehouse wh);

    /**
     * 修改仓库启用状态
     * @param wh 仓库ID
     * @param type 状态类型
     * @return
     */
    ResultEntity changeStatus(Warehouse wh, String type);
}
