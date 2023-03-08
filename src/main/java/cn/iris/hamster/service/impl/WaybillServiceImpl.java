package cn.iris.hamster.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iris.hamster.bean.entity.Waybill;
import cn.iris.hamster.service.WaybillService;
import cn.iris.hamster.mapper.WaybillMapper;
import org.springframework.stereotype.Service;

/**
* @author asus
* @description 针对表【waybill(运单表)】的数据库操作Service实现
* @createDate 2023-01-05 16:15:17
*/
@Service
public class WaybillServiceImpl extends ServiceImpl<WaybillMapper, Waybill>
    implements WaybillService{

}




