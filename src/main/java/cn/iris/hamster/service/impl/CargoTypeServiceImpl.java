package cn.iris.hamster.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iris.hamster.bean.pojo.CargoType;
import cn.iris.hamster.service.CargoTypeService;
import cn.iris.hamster.mapper.CargoTypeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author asus
* @description 针对表【cargo_type(系统字段-货物类型表)】的数据库操作Service实现
* @createDate 2023-03-23 09:53:29
*/
@Service
public class CargoTypeServiceImpl extends ServiceImpl<CargoTypeMapper, CargoType>
    implements CargoTypeService{

    @Override
    public List<CargoType> listByLimit(CargoType query) {
        return baseMapper.listByLimit(query, query.getStartIndex());
    }

    @Override
    public Integer getCountByLimit(CargoType query) {
        return baseMapper.getCountByLimit(query);
    }
}




