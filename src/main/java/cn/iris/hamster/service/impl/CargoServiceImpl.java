package cn.iris.hamster.service.impl;

import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.enums.CargoStatusEnum;
import cn.iris.hamster.bean.enums.CargoTypeEnum;
import cn.iris.hamster.common.utils.CommonUtils;
import cn.iris.hamster.common.utils.UserUtils;
import cn.iris.hamster.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iris.hamster.bean.pojo.Cargo;
import cn.iris.hamster.service.CargoService;
import cn.iris.hamster.mapper.CargoMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author asus
* @description 针对表【cargo(货物表)】的数据库操作Service实现
* @createDate 2023-01-05 15:04:05
*/
@Service
public class CargoServiceImpl extends ServiceImpl<CargoMapper, Cargo>
    implements CargoService{
    @Autowired
    private CargoMapper cargoMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultEntity newCargo(Cargo cargo) {
        if (!isCargoValid(cargo)) {
            return ResultEntity.error("数据格式有误");
        }
        cargo.setId(CommonUtils.randId());
        cargo.setStatus(CargoStatusEnum.NO_WAREHOUSE.getKey());
        if (ObjectUtils.isEmpty(cargo.getCooperativeId())) {
            cargo.setCooperativeId(userMapper.getCurUserCoId(UserUtils.getUserId()));
        }
        int cnt = cargoMapper.insert(cargo);
        return cnt > 0 ? ResultEntity.success("新增货物成功") : ResultEntity.error("新增货物失败");
    }

    @Override
    public ResultEntity modify(Cargo cargo) {
        if (ObjectUtils.isEmpty(cargo.getId())) {
            return ResultEntity.error("参数有误");
        }
        Cargo info = getById(cargo.getId());
        if (ObjectUtils.isEmpty(info)) {
            return ResultEntity.error("数据不存在");
        }
        if (CargoStatusEnum.NO_WAREHOUSE.getKey().equals(info.getStatus())) {
            // 未入库，可以修改信息
            int cnt = cargoMapper.updateById(cargo);
            return cnt > 0 ? ResultEntity.success("货物信息修改成功") : ResultEntity.error("货物信息修改失败");
        } else {
            return ResultEntity.error("货物已确认，无法修改信息");
        }
    }

    private boolean isCargoValid(Cargo cargo) {
        return StringUtils.isNotEmpty(cargo.getName())
                && StringUtils.isNotEmpty(cargo.getType());
    }
}




