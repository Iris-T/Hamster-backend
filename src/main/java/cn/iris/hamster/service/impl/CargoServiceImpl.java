package cn.iris.hamster.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iris.hamster.bean.pojo.Cargo;
import cn.iris.hamster.service.CargoService;
import cn.iris.hamster.mapper.CargoMapper;
import org.springframework.stereotype.Service;

/**
* @author asus
* @description 针对表【cargo(货物表)】的数据库操作Service实现
* @createDate 2023-01-05 15:04:05
*/
@Service
public class CargoServiceImpl extends ServiceImpl<CargoMapper, Cargo>
    implements CargoService{

}




