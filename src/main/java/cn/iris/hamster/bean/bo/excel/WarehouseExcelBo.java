package cn.iris.hamster.bean.bo.excel;

import cn.iris.hamster.common.bean.entity.BaseEntity;
import cn.iris.hamster.common.convert.CommonStatusConverter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 仓库信息导出类
 *
 * @author Iris
 * @ClassName WarehouseExcelBo
 * @date 2023/3/16 11:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseExcelBo extends BaseEntity {
    /**
     * 仓库名称
     */
    @ExcelProperty("仓库名称")
    @ColumnWidth(value = 30)
    @TableField(value = "`name`")
    private String name;

    /**
     * 仓库空间
     */
    @ExcelProperty("仓库承载/m³")
    @ColumnWidth(value = 20)
    @NumberFormat("#.000")
    private Double space;

    /**
     * 仓库地址
     */
    @ExcelProperty("仓库地址")
    @ColumnWidth(value = 50)
    private String address;

    /**
     * 城市代码
     */
    @ExcelProperty("城市代码")
    @ColumnWidth(value = 20)
    private String cityCode;

    /**
     * 仓库使用状态-0为正常,1为停用
     */
    @ExcelProperty(value = "使用状态", converter = CommonStatusConverter.class)
    @ColumnWidth(value = 10)
    private String status;

    /**
     * 备注
     */
    @ExcelProperty("备注信息")
    @ColumnWidth(value = 40)
    private String remark;
}
