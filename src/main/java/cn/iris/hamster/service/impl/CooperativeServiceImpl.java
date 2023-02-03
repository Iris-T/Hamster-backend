package cn.iris.hamster.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.common.utils.CommonUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iris.hamster.bean.pojo.Cooperative;
import cn.iris.hamster.service.CooperativeService;
import cn.iris.hamster.mapper.CooperativeMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author asus
* @description 针对表【cooperative(合作企业)】的数据库操作Service实现
* @createDate 2023-01-05 15:55:35
*/
@Service
public class CooperativeServiceImpl extends ServiceImpl<CooperativeMapper, Cooperative>
    implements CooperativeService{
    @Autowired
    private CooperativeMapper cooperativeMapper;

    @Override
    public ResultEntity saveCo(Cooperative co) {
        if (!isCoValid(co)) {
            return ResultEntity.error("数据格式错误");
        }

        int cnt = 0;
        if (ObjectUtil.isNotEmpty(co.getId())) {
            cnt = cooperativeMapper.updateById(co);
        } else {
            co.setId(CommonUtils.randId());
            cnt = cooperativeMapper.insert(co);
        }

        return cnt > 0 ? ResultEntity.success("更新企业信息成功") : ResultEntity.error("更新企业信息失败");
    }

    @Override
    public Integer monthlyNewCoCount() {
        return cooperativeMapper.monthlyNewCoCount();
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




