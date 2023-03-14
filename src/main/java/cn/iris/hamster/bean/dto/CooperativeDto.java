package cn.iris.hamster.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 月结客户参数类
 *
 * @author Iris
 * @ClassName CooperativeDto
 * @date 2023/3/10 16:05
 */
@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CooperativeDto {
    private String name;
    private String phone;
    private String address;
    private String usci;
    private String status;
    /**
     * 联系人id
     */
    private Long contactId;
}
