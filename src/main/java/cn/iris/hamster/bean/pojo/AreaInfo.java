package cn.iris.hamster.bean.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * 行政规划区类
 *
 * @author Iris
 * @ClassName AreaInfo
 * @date 2023/3/21 14:35
 */
@TableName(value ="area_info")
@Getter
public class AreaInfo {
    @TableId
    private Long id;
    private Long pid;
    /**
     * 行政区编码
     */
    private String code;
    /**
     * 行政区名称
     */
    @TableField(value = "`name`")
    private String name;
    /**
     * 行政区全称
     */
    private String fullName;
    /**
     * 级次id 0:省/自治区/直辖市 1:市级 2:县级
     */
    private String type;
    /**
     * 经度
     */
    private BigDecimal lon;
    /**
     * 纬度
     */
    private BigDecimal lat;
}
