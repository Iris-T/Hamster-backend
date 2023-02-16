package cn.iris.hamster.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户更新资料DTO类
 *
 * @author Iris
 * @ClassName UserReProfileDto
 * @date 2023/2/8 16:40
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReProfileDto {
    private String username;
    private String name;
    private String gender;
    private String phone;
    private String address;
}
