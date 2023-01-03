package cn.iris.hamster.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户登录对象
 *
 * @author Iris
 * @ClassName UserLoginDto
 * @date 2022/12/28 15:02
 */

@Data
@AllArgsConstructor
public class UserLoginDto {

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

}
