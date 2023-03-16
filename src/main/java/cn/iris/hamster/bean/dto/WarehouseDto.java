package cn.iris.hamster.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Iris
 * @ClassName WarehouseDto
 * @date 2023/3/14 16:03
 */
@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDto {
    private String name;
    private Double space;
    private String address;
    private String cityCode;
    private String remark;
}
