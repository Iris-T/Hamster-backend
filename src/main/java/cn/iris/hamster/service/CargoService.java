package cn.iris.hamster.service;

import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.bean.entity.Cargo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author asus
* @description 针对表【cargo(货物表)】的数据库操作Service
* @createDate 2023-01-05 15:04:05
*/
public interface CargoService extends IService<Cargo> {

    /**
     * 新增货物
     * @param cargo
     * @return
     */
    ResultEntity newCargo(Cargo cargo);

    /**
     * 仅限在货物未入仓的情况下修改货物信息
     * @param cargo
     * @return
     */
    ResultEntity modify(Cargo cargo);
}
