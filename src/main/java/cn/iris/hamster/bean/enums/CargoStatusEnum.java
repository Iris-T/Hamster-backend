package cn.iris.hamster.bean.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * 货物运输状态枚举类
 *
 * @author Iris
 * @ClassName CargoStatusEnum
 * @date 2023/1/5 15:49
 */

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CargoStatusEnum {
    /**
     * 货物运输状态枚举
     */
    NO_WAREHOUSE("0", "未入库"),
    WAREHOUSED("1", "已入库"),
    IN_TRANS("2", "运输中"),
    END_TRANS("3", "运输结束"),
    OUT_WAREHOUSE("4", "已出库"),
    UNKNOWN("-1", "未知")
    ;


    private final String key;
    private final String value;

    /**
     * 根据状态判断货物是否未入库
     * @return
     */
    public static Boolean isNoWarehouse(String status) {
        return status.equals(NO_WAREHOUSE.getKey());
    }

    public static String getValueByKey(String key) {
        return Stream.of(values()).filter(bean -> bean.getKey().equals(key)).findFirst().orElse(UNKNOWN).getValue();
    }
}
