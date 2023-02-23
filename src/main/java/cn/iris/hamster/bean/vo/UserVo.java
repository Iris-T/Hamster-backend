package cn.iris.hamster.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户角色视图类
 *
 * @author Iris
 * @ClassName UserVo
 * @date 2023/2/6 14:42
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {

    private Long id;
    private String username;
    private String role;
    private String name;
    private String gender;
    private String idNo;
    private String phone;
    private String address;
    private String status;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
}
