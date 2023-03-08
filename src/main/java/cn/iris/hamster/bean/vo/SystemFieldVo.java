package cn.iris.hamster.bean.vo;

import cn.iris.hamster.common.bean.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * SystemField视图类
 *
 * @author Iris
 * @ClassName SystemFieldVo
 * @date 2023/3/8 10:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SystemFieldVo extends BaseVo {
    private String name;
    private String key;
    private String type;
    private String str;
    private String value;
}
