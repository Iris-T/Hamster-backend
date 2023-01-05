package cn.iris.hamster.bean.enums;

import lombok.Getter;

/**
 * 货物类型枚举类
 *
 * @author Iris
 * @ClassName CargoTypeEnum
 * @date 2023/1/5 15:05
 */

@Getter
public enum CargoTypeEnum {
    /**
     * 适用于货物类型的枚举
     */
    CORN("1", "谷物粮食"),
    FRESH("2", "生鲜蔬菜"),
    ELEC("3", "电子工业品"),
    DAILY("4", "日用工业品"),
    HIGH_RISK("5", "高危产品"),
    STEEL("6", "钢材及相关制品"),
    MINERAL("7", "矿物")
    ;

    private final String key;

    private final String value;

    CargoTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
