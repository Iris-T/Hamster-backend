package cn.iris.hamster.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户查询条件DTO类
 *
 * @author Iris
 * @ClassName UserQueryDto
 * @date 2023/2/5 15:04
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryDto {

    private String keyword;
    private String gender;
    private String status;
}
