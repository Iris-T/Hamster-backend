package cn.iris.hamster.bean.dto;

import cn.hutool.core.util.ObjUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 运输参数类
 *
 * @author Iris
 * @ClassName TransDto
 * @date 2023/4/13 15:34
 */
@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransDto {
    private Long driverId;
    private Long vehicleId;
    private Long startWhId;
    private Long endWhId;
    private String remark;

    public Boolean isValid() {
        return ObjUtil.isAllNotEmpty(driverId, vehicleId, startWhId, endWhId) ;
    }
}
