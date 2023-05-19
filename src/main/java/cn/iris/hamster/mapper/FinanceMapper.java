package cn.iris.hamster.mapper;

import cn.iris.hamster.bean.pojo.Finance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Iris
 * @ClassName FinanceMapper
 * @date 2023/5/19 20:47
 */
@Repository
public interface FinanceMapper extends BaseMapper<Finance> {
    List<Finance> listByLimit(@Param("finance") Finance finance, @Param("start") Integer start);

    Integer getCountByLimit(@Param("finance") Finance finance);

    BigDecimal getCurMonFinance();
}
