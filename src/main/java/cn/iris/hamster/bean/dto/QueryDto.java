package cn.iris.hamster.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询条件DTO类
 *
 * @author Iris
 * @ClassName QueryDto
 * @date 2023/2/5 15:04
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryDto {

    /**
     * 搜索关键词
     */
    private String keyword;
    /**
     * 用户性别
     * @see cn.iris.hamster.bean.pojo.User
     */
    private String gender;
    /**
     * 查询对象状态
     */
    private String status;
    /**
     * 查询列表当前页
     */
    private Integer cur;
    /**
     * 查询列表当前页大小
     */
    private Integer size;

    public Integer getStartIndex() {
        return size * (cur - 1);
    }
}
