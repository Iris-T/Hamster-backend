package cn.iris.hamster.bean.vo;

import cn.iris.hamster.common.constants.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * 静态数据视图类
 *
 * @author Iris
 * @ClassName StaticInfoVo
 * @date 2023/2/1 17:22
 */

@Data
@AllArgsConstructor
public class StaticInfoVo {

    /**
     * 块元素标题
     */
    private String title;
    /**
     * 展示数值
     */
    private Long value;
    /**
     * 子数据块标题
     */
    private String subTitle;
    /**
     * 子数据值
     */
    private Long subValue;
    /**
     * 数值块元素type类型
     */
    private String type;
    /**
     * 数值单位
     */
    private String unit;

    public StaticInfoVo(String title, long value) {
        this(title, value, null, null, null, null);
    }

    public StaticInfoVo(String title, int value) {
        this(title, Long.valueOf(value));
    }

    public StaticInfoVo(String title, long value, String subTitle, long subValue, String unit) {
        this(title, value, subTitle, subValue, CommonConstants.TYPE_SUCCESS, unit);
    }

    public StaticInfoVo(String title, int value, String subTitle, int subValue, String unit) {
        this(title, (long) value, subTitle, (long) subValue, CommonConstants.TYPE_SUCCESS, unit);
    }

    public StaticInfoVo(String title, long value, String subTitle, int subValue, String unit) {
        this(title, value, subTitle, (long) subValue, CommonConstants.TYPE_SUCCESS, unit);
    }

    public StaticInfoVo(String title, int value, String subTitle, long subValue, String unit) {
        this(title, (long) value, subTitle, subValue, CommonConstants.TYPE_SUCCESS, unit);
    }
}
