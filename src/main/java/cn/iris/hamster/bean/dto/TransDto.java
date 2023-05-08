package cn.iris.hamster.bean.dto;

import cn.hutool.core.util.ObjUtil;

/**
 * 运输参数类
 *
 * @author Iris
 * @ClassName TransDto
 * @date 2023/4/13 15:34
 */
public class TransDto {
    private Long driverId;
    private Long vehicleId;
    private Long startWhId;
    private Long endWhId;

    public Boolean isValid() {
        return ObjUtil.isAllNotEmpty(driverId, vehicleId, startWhId, endWhId) ;
    }
}
