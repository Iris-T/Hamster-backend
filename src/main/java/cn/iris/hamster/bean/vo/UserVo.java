package cn.iris.hamster.bean.vo;

import cn.iris.hamster.common.bean.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户角色视图类
 *
 * @author Iris
 * @ClassName UserVo
 * @date 2023/2/6 14:42
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo extends BaseVo {

    private String username;
    private String role;
    private String name;
    private String gender;
    private String idNo;
    private String phone;
    private String address;
}
