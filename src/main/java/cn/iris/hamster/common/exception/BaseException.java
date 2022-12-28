package cn.iris.hamster.common.exception;

import java.io.Serial;

/**
 * 基础异常类
 *
 * @author Iris
 * @ClassName BaseException
 * @date 2022/12/28 14:31
 */
public class BaseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5730750774514438416L;

    private String errorCode;

    public BaseException() {
        super();
    }

    public BaseException(String message, Throwable cause,
                         boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
