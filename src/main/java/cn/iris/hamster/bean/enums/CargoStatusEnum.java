package cn.iris.hamster.bean.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 货物运输状态枚举类
 *
 * @author Iris
 * @ClassName CargoStatusEnum
 * @date 2023/1/5 15:49
 */

@Getter
@AllArgsConstructor
public enum CargoStatusEnum {
    /**
     * 货物运输状态枚举
     */
    NO_WAREHOUSE("0", "未入库"),
    WAREHOUSED("1", "已入库"),
    IN_TRANSIT("2", "运输中"),
    END_TRANSIT("3", "运输结束"),
    OUT_WAREHOUSE("4", "已出库")
    ;


    private final String key;
    private final String value;
}
