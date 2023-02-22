package cn.iris.hamster.bean.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer cur;
    /**
     * 查询列表当前页大小
     */
    @JsonIgnore
    @TableField(exist = false)
    private Integer size;

    @JsonIgnore
    public Integer getStartIndex() {
        return size * (cur - 1);
    }
}
