package cn.iris.hamster.common.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础视图类
 *
 * @author Iris
 * @ClassName BaseVo
 * @date 2023/3/8 10:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseVo implements Serializable {
    private Long id;
    private String status;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String remark;
}
