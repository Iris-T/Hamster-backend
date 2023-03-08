package cn.iris.hamster.service.impl;

import cn.iris.hamster.bean.entity.SystemField;
import cn.iris.hamster.bean.vo.SystemFieldVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iris.hamster.service.SystemFieldService;
import cn.iris.hamster.mapper.SystemFieldMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Iris
* @description 针对表【system_field(系统字段表)】的数据库操作Service实现
* @createDate 2023-02-02 15:21:30
*/
@Service
public class SystemFieldServiceImpl extends ServiceImpl<SystemFieldMapper, SystemField>
    implements SystemFieldService {

    @Override
    public List<SystemFieldVo> listByLimit(SystemField query) {
        return baseMapper.listByLimit(query.getStartIndex(), query);
    }

    @Override
    public Integer getCountByLimit(SystemField query) {
        return baseMapper.getCountByLimit(query);
    }
}




