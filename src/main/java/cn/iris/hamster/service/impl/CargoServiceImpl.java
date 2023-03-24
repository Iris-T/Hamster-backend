package cn.iris.hamster.service.impl;

import cn.iris.hamster.bean.vo.CargoVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iris.hamster.bean.pojo.Cargo;
import cn.iris.hamster.service.CargoService;
import cn.iris.hamster.mapper.CargoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author asus
* @description 针对表【cargo(货物表)】的数据库操作Service实现
* @createDate 2023-01-05 15:04:05
*/
@Service
public class CargoServiceImpl extends ServiceImpl<CargoMapper, Cargo>
    implements CargoService{
    @Override
    public List<CargoVo> listByLimit(Cargo query) {
        return baseMapper.listByLimit(query.getStartIndex(), query);
    }

    @Override
    public Integer getCountByLimit(Cargo query) {
        return baseMapper.getCountByLimit(query);
    }
}




