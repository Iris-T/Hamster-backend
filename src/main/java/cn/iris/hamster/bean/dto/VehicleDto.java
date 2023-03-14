package cn.iris.hamster.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 车辆信息参数类
 *
 * @author Iris
 * @ClassName VehicleDto
 * @date 2023/3/14 9:32
 */
@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
    private String plateNo;
    private Double load;
    private Double space;
    private String localWh;
}
