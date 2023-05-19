package cn.iris.hamster.bean.pojo;

import cn.iris.hamster.common.bean.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 货物财务计算类
 *
 * @author Iris
 * @ClassName Finance
 * @date 2023/5/19 15:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Finance extends BaseEntity {

    /**
     * 货物ID
     */
    private Long cid;
    /**
     * 运单编号
     */
    private Long tid;
    /**
     * 货物名称、货物重量、占用空间、基本费用、运输路程
     */
    @TableId
    private String cargoName;
    private BigDecimal weight;
    private BigDecimal space;
    private BigDecimal baseFee;
    private Double distance;
    /**
     * 委托人
     */
    private String cooperative;
    /**
     * 货物类型
     */
    private String cargoType;
    private String driver;
    private String vehicle;
    /**
     * 起始点
     */
    private String startWh;
    /**
     * 目的地
     */
    private String target;
}
