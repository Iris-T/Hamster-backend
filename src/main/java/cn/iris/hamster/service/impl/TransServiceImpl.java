package cn.iris.hamster.service.impl;

import cn.iris.hamster.bean.pojo.Trans;
import cn.iris.hamster.bean.vo.TransVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iris.hamster.service.TransService;
import cn.iris.hamster.mapper.TransMapper;
import org.springframework.stereotype.Service;

/**
* @author asus
* @description 针对表【trans(运单表)】的数据库操作Service实现
* @createDate 2023-01-05 16:15:17
*/
@Service
public class TransServiceImpl extends ServiceImpl<TransMapper, Trans>
    implements TransService {

    @Override
    public TransVo getTransListByLimit(Trans query) {
        return baseMapper.getTranListByLimit(query.getStartIndex(), query);
    }

    @Override
    public Integer getTransCountByLimit(Trans query) {
        return baseMapper.getTransCountByLimit(query);
    }
}




