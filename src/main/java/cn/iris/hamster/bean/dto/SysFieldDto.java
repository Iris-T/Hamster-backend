package cn.iris.hamster.bean.dto;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 系统参数的参数类
 *
 * @author Iris
 * @ClassName SysFieldDto
 * @date 2023/3/8 16:04
 */
@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysFieldDto {
    private String name;
    private String key;
    private String type;
    private String str;
    private String value;
    private String status;
    private String remark;
}
