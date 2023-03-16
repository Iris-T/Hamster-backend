package cn.iris.hamster.bean.enums;

import cn.iris.hamster.common.utils.ListUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 车辆状态枚举
 *
 * @author Iris
 * @ClassName VehicleStatusEnum
 * @date 2023/1/6 9:01
 */

@Getter
@AllArgsConstructor
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

    public static List<String> getKeys() {
        return Stream.of(values()).map(VehicleStatusEnum::getKey).collect(Collectors.toList());
    }

    public static List<String> getValues() {
        return Stream.of(values()).map(VehicleStatusEnum::getValue).collect(Collectors.toList());
    }
}
