package cn.iris.hamster.bean.dto;

import cn.iris.hamster.bean.pojo.User;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户更新类
 *
 * @author Iris
 * @ClassName UserDto
 * @date 2023/2/24 9:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends User {

    /**
     * 权限角色ID
     */
    @TableField(exist = false)
    private Long rid;
}