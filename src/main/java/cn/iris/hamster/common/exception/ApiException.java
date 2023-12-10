package cn.iris.hamster.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 接口调用异常
 *
 * @author Iris
 * @ClassName ApiException
 * @date 2023/12/10 19:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ApiException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6206966759967032593L;

    private String apiName;
    private String errMsg;
}
