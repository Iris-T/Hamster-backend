package cn.iris.hamster.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.bean.vo.CooperativeVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iris.hamster.bean.pojo.Cooperative;
import cn.iris.hamster.service.CooperativeService;
import cn.iris.hamster.mapper.CooperativeMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author asus
* @description 针对表【cooperative(合作企业)】的数据库操作Service实现
* @createDate 2023-01-05 15:55:35
*/
@Service
public class CooperativeServiceImpl extends ServiceImpl<CooperativeMapper, Cooperative>
    implements CooperativeService{

    @Override
    public Integer monthlyNewCoCount() {
        return baseMapper.monthlyNewCoCount();
    }

    @Override
    public List<CooperativeVo> listByLimit(Cooperative query) {
        return baseMapper.listByLimit(query);
    }

    @Override
    public Integer getCountByLimit(Cooperative query) {
        return baseMapper.getCountByLimit(query);
    }

    private boolean isCoValid(Cooperative co) {
        boolean temp = true;
        if (ObjectUtil.isNotEmpty(co.getId())) {
            temp = ObjectUtil.isNotEmpty(getById(co.getId()));
        }
        return temp
                && StringUtils.isNotEmpty(co.getName())
                && StringUtils.isNotEmpty(co.getPhone())
                && StringUtils.isNotEmpty(co.getAddress())
                && StringUtils.isNotEmpty(co.getUsci());
    }
}




