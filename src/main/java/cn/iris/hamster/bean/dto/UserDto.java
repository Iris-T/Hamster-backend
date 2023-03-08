package cn.iris.hamster.bean.dto;

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
@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    /**
     * 权限角色ID
     */
    @TableField(exist = false)
    private Long rid;

    private Long id;
    private String username;
    private String name;
    private String gender;
    private String idNo;
    private String phone;
    private String address;
}