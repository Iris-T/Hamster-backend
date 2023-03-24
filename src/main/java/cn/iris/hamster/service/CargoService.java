package cn.iris.hamster.service;

import cn.iris.hamster.bean.vo.CargoVo;
import cn.iris.hamster.bean.pojo.Cargo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author asus
* @description 针对表【cargo(货物表)】的数据库操作Service
* @createDate 2023-01-05 15:04:05
*/
public interface CargoService extends IService<Cargo> {

    /**
     * 根据限制条件返回货物视图类列表
     * @param query
     * @return
     * @see cn.iris.hamster.bean.vo.CargoVo
     */
    List<CargoVo> listByLimit(Cargo query);

    /**
     * 根据限制条件返回记录总数
     * @param query
     * @return
     */
    Integer getCountByLimit(Cargo query);
}
