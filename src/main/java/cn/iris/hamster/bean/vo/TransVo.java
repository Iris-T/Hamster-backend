package cn.iris.hamster.bean.vo;

import cn.iris.hamster.common.bean.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Iris
 * @ClassName TransVo
 * @date 2023/4/11 19:26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransVo extends BaseVo {
    private Long id;
    private Long driverId;
    private Long vehicleId;
    private Long startWhId;
    private Long endWhId;
    private String driver;
    private String vehicle;
    private String startWh;
    private String endWh;
    private Date startTime;
    private Date endTime;
}
