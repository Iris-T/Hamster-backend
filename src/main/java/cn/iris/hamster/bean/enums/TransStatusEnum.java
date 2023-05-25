package cn.iris.hamster.bean.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 运输状态枚举类
 *
 * @author Iris
 * @ClassName TransStatusEnum
 * @date 2023/4/13 16:05
 */
@Getter
@AllArgsConstructor
public enum TransStatusEnum {
    /**
     * 运输状态枚举
     */
    UN_CONFIRM("0","未确认"),
    UN_START("1", "未开始"),
    ON_TRANS("2", "运输中"),
    OVER("3", "已结束"),
    UNKNOWN("-1", "未知");

    private final String key;
    private final String value;

    public static String getValueByKey(String key) {
        for (TransStatusEnum e : values()) {
            if (key.equals(e.key)) {
                return e.value;
            }
        }
        return UNKNOWN.value;
    }

    public static String getKeyByValue(String value) {
        for (TransStatusEnum e : values()) {
            if (value.equals(e.value)) {
                return e.key;
            }
        }
        return UNKNOWN.key;
    }

    public static Boolean isRightTransStatus(String status) {
        for (TransStatusEnum e : values()) {
            if (e.key.equals(status)) {
                return true;
            }
        }
        return false;
    }
}
