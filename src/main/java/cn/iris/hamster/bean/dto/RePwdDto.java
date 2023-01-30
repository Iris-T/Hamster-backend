package cn.iris.hamster.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * 更新密码对象
 *
 * @author Iris
 * @ClassName RePwdDto
 * @date 2023/1/29 15:48
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RePwdDto {

    /**
     * 旧密码
     */
    private String oldPassword;
    /**
     * 新密码
     */
    private String newPassword;
    /**
     * 再次确认
     */
    private String reConfirmPassword;

    /**
     * 返回字段值是否存在空
     * 复用方法
     * @return Boolean值
     */
    public boolean isAnyBlank() {
        return StringUtils.isAnyBlank(oldPassword, newPassword, reConfirmPassword);
    }
}
