package cn.iris.hamster.bean.vo;

import cn.iris.hamster.common.bean.vo.BaseVo;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 车辆信息视图类
 *
 * @author Iris
 * @ClassName VehicleVo
 * @date 2023/3/13 16:51
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleVo extends BaseVo {
    private String plateNo;
    private Double load;
    private Double space;
    private String localWh;
}
