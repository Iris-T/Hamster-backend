package cn.iris.hamster.bean.vo;

import cn.iris.hamster.common.bean.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 客户视图类
 *
 * @author Iris
 * @ClassName CooperativeVo
 * @date 2023/3/10 9:51
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CooperativeVo extends BaseVo {
    private String name;
    private String phone;
    private String address;
    private String usci;
    /**
     * 联系人姓名
     */
    private String contractName;
}
