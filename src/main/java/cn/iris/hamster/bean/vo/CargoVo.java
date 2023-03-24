package cn.iris.hamster.bean.vo;

import cn.iris.hamster.common.bean.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 货物视图类
 *
 * @author Iris
 * @ClassName CargoVo
 * @date 2023/3/17 9:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoVo extends BaseVo {
    private String name;
    private Long type;
    private String typeName;
    private Double space;
    private Double weight;
    private String cooperativeName;
    /**
     * 目的地
     */
    private String dest;
    private String sup;
    private String destAddr;
    /**
     * 验收仓
     */
    private String startWh;
    /**
     * 所在仓/收货仓
     */
    private String localWh;
}
