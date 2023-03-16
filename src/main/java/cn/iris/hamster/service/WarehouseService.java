package cn.iris.hamster.service;

import cn.iris.hamster.bean.entity.Warehouse;
import cn.iris.hamster.bean.vo.WareHouseVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Iris
* @description 针对表【warehouse(仓库表)】的数据库操作Service
* @createDate 2023-01-05 15:56:56
*/
public interface WarehouseService extends IService<Warehouse> {

    /**
     * 根据限定条件获取仓库信息列表
     * @param query
     * @return
     */
    List<WareHouseVo> listByLimit(Warehouse query);

    /**
     * 根据限定条件获取记录总数
     * @param query
     * @return
     */
    Integer getCountByLimit(Warehouse query);

    /**
     * 通过名称完全匹配返回对象
     * @param name
     * @return
     */
    Warehouse getWhByName(String name);
}
