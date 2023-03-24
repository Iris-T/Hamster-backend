package cn.iris.hamster.bean.dto;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 货物参数类
 *
 * @author Iris
 * @ClassName CargoDto
 * @date 2023/3/17 13:51
 */
@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CargoDto {
    private String name;
    private Long type;
    private Double space;
    private Double weight;
    private Long cooperativeId;
    private String dest;
    private String sup;
    private String remark;
}
