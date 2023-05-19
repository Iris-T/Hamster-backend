package cn.iris.hamster.service.impl;

import cn.iris.hamster.bean.pojo.Finance;
import cn.iris.hamster.mapper.FinanceMapper;
import cn.iris.hamster.service.FinanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Iris
 * @ClassName FinanceServiceImpl
 * @date 2023/5/19 20:54
 */
@Service
public class FinanceServiceImpl extends ServiceImpl<FinanceMapper, Finance> implements FinanceService {

    @Override
    public List<Finance> listByLimit(Finance finance) {
        return baseMapper.listByLimit(finance, finance.getStartIndex());
    }

    @Override
    public Integer getCountByLimit(Finance finance) {
        return baseMapper.getCountByLimit(finance);
    }

    @Override
    public BigDecimal getCurMonFinance() {
        return baseMapper.getCurMonFinance();
    }
}
