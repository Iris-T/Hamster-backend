package cn.iris.hamster.bean.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * 公共状态枚举类
 *
 * @author Iris
 * @ClassName CommonStatusEnum
 * @date 2023/3/15 9:21
 */
@Getter
@AllArgsConstructor
public enum CommonStatusEnum {
    /**
     * 公共状态枚举类
     */
    ENABLE("0", "启用"),
    DISABLE("1", "停用"),
    UNKNOWN("2", "未知");

    private final String status;
    private final String word;

    public static CommonStatusEnum convertFromStatus(String status) {
        return Stream.of(values()).filter(bean -> bean.status.equals(status)).findFirst().orElse(UNKNOWN);
    }

    public static CommonStatusEnum convertFromWord(String word) {
        return Stream.of(values()).filter(bean -> bean.word.equals(word)).findFirst().orElse(UNKNOWN);
    }
}
