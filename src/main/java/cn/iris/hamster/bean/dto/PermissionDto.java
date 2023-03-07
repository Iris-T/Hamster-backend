package cn.iris.hamster.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Permission参数类
 *
 * @author Iris
 * @ClassName PermissionDto
 * @date 2023/3/6 10:50
 */

@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDto {

    private String name;
    private String pKey;
    private String isMenu;
    private String icon;
    private String path;
    private String remark;
    private Long parentId;
}
