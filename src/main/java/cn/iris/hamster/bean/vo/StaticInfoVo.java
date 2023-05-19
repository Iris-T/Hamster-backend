package cn.iris.hamster.bean.vo;

import cn.iris.hamster.common.constants.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
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
    private String value;
    /**
     * 子数据块标题
     */
    private String subTitle;
    /**
     * 子数据值
     */
    private String subValue;
    /**
     * 数值块元素type类型
     */
    private String type;
    /**
     * 数值单位
     */
    private String unit;

    public StaticInfoVo(String title, Object value) {
        this(title, String.valueOf(value), null, null, null);
    }

    public StaticInfoVo(String title, Object value, String subTitle, Object subValue, String unit) {
        this(title, String.valueOf(value), subTitle, String.valueOf(subValue), CommonConstants.TYPE_SUCCESS, unit);
    }
}
