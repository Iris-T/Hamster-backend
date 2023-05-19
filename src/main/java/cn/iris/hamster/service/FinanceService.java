package cn.iris.hamster.service;

import cn.iris.hamster.bean.pojo.Finance;
import cn.iris.hamster.mapper.FinanceMapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Iris
 * @ClassName FinanceService
 * @date 2023/5/19 20:53
 */
public interface FinanceService extends IService<Finance> {
    List<Finance> listByLimit(Finance finance);

    Integer getCountByLimit(Finance finance);

    BigDecimal getCurMonFinance();
}
