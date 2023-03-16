package cn.iris.hamster.service.impl;

import cn.iris.hamster.bean.vo.WareHouseVo;
import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.common.constants.CommonConstants;
import cn.iris.hamster.common.exception.BaseException;
import cn.iris.hamster.common.utils.CommonUtils;
import cn.iris.hamster.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iris.hamster.bean.entity.Warehouse;
import cn.iris.hamster.service.WarehouseService;
import cn.iris.hamster.mapper.WarehouseMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author asus
* @description 针对表【warehouse(仓库表)】的数据库操作Service实现
* @createDate 2023-01-05 15:56:56
*/
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse>
    implements WarehouseService{

    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseServiceImpl.class);

    @Override
    public List<WareHouseVo> listByLimit(Warehouse query) {
        return baseMapper.listByLimit(query.getStartIndex(), query);
    }

    @Override
    public Integer getCountByLimit(Warehouse query) {
        return baseMapper.getCountByLimit(query);
    }

    @Override
    public Warehouse getWhByName(String name) {
        if (StringUtils.isBlank(name)) {
            LOGGER.error("参数有误");
            throw new BaseException("参数有误");
        }
        return baseMapper.getWhByName(name);
    }


}




