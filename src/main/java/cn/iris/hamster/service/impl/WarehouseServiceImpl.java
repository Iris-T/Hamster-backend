package cn.iris.hamster.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iris.hamster.bean.pojo.Warehouse;
import cn.iris.hamster.service.WarehouseService;
import cn.iris.hamster.mapper.WarehouseMapper;
import org.springframework.stereotype.Service;

/**
* @author asus
* @description 针对表【warehouse(仓库表)】的数据库操作Service实现
* @createDate 2023-01-05 15:56:56
*/
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse>
    implements WarehouseService{

}




