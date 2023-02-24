package cn.iris.hamster.bean.entity;

import cn.iris.hamster.common.constants.CommonConstants;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JsonIgnore
    @TableField(exist = false)
    private String keyword;
    /**
     * 查询列表当前页
     */
    @JsonIgnore
    @TableField(exist = false)
    private Integer cur = 1;
    /**
     * 查询列表当前页大小
     */
    @JsonIgnore
    @TableField(exist = false)
    private Integer size = CommonConstants.DEFAULT_PAGE_SIZE;

    /**
     * 创建用户id
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新用户id
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @JsonIgnore
    public Integer getStartIndex() {
        return size * (cur - 1);
    }
}
