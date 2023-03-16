package cn.iris.hamster.bean.vo;

import cn.iris.hamster.common.bean.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Iris
 * @ClassName WareHouseVo
 * @date 2023/3/14 14:16
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WareHouseVo extends BaseVo {
    private String name;
    private Double space;
    private String address;
    private String cityCode;
    /**
     * 仓库承载
     */
    private Double load;
    private Double usedSpace;
}
