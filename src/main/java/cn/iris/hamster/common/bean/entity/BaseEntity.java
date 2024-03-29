package cn.iris.hamster.common.bean.entity;

import cn.iris.hamster.common.constants.CommonConstants;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

/**
 * 查询条件DTO类
 *
 * @author Iris
 * @ClassName BaseEntity
 * @date 2023/2/5 15:04
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    /**
     * 搜索关键词
     */
    @ExcelIgnore
    @JsonIgnore
    @TableField(exist = false)
    private String keyword;
    /**
     * 查询列表当前页
     */
    @ExcelIgnore
    @JsonIgnore
    @TableField(exist = false)
    private Integer cur = 1;
    /**
     * 查询列表当前页大小
     */
    @ExcelIgnore
    @JsonIgnore
    @TableField(exist = false)
    private Integer size = CommonConstants.DEFAULT_PAGE_SIZE;

    /**
     * 创建用户id
     */
    @ExcelIgnore
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 创建时间
     */
    @ExcelIgnore
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新用户id
     */
    @ExcelIgnore
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     * 更新时间
     */
    @ExcelIgnore
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @JsonIgnore
    public Integer getStartIndex() {
        return size * (cur - 1);
    }
}
