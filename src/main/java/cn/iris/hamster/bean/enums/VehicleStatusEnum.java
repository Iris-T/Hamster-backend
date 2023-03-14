package cn.iris.hamster.bean.enums;

import cn.iris.hamster.common.utils.ListUtils;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    UNUSED("0", "闲置"),
    WORK("1", "作业"),
    CHECK("2", "检修"),
    DISUSE("3", "停用");

    private final String key;
    private final String value;

    VehicleStatusEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static List<String> getKeys() {
        return Arrays.stream(VehicleStatusEnum.values()).map(VehicleStatusEnum::getKey).collect(Collectors.toList());
    }

    public static List<String> getValues() {
        return Arrays.stream(VehicleStatusEnum.values()).map(VehicleStatusEnum::getValue).collect(Collectors.toList());
    }
}
