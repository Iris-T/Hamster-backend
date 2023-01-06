package cn.iris.hamster.bean.enums;

import lombok.Getter;

/**
 * 车辆状态枚举
 *
 * @author Iris
 * @ClassName VehicleStatusEnum
 * @date 2023/1/6 9:01
 */

@Getter
public enum VehicleStatusEnum {
    /**
     * 车辆状态枚举
     */
    UNUSED("0", "限制"),
    WORK("1", "作业"),
    CHECK("2", "检修"),
    DISUSE("3", "停用")
    ;

    private String key;
    private String value;

    VehicleStatusEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
