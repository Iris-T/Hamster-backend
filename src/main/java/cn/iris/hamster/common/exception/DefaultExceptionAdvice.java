package cn.iris.hamster.common.exception;


import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.common.utils.UserUtils;
import io.jsonwebtoken.JwtException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.sql.SQLException;

/**
 * 默认异常处理器
 *
 * @author Iris
 * @ClassName DefaultExceptionAdvice
 * @date 2022/12/28 14:34
 */

@RestControllerAdvice(basePackages = {"cn.iris.hamster.controller"})
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Component
public class DefaultExceptionAdvice {
    private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionAdvice.class);

    /**
     * 数据范围异常关键字
     */
    private static final String RANGE_EXCEPTION = "range of";
    /**
     * 转换异常关键字
     */
    private static final String FROM_EXCEPTION = "from";

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {

        String errorMessage = e.getMessage();
        String errorInfo = "参数解析失败!";
        logger.error(errorInfo, e);
        try {

            //解析错误信息中的class 和field
            String className = StringUtils.substringBetween(errorMessage, "through reference chain: ", "[");
            String filedName = StringUtils.substringBetween(errorMessage, String.format("%s[\"", className), "\"]");
            if (StringUtils.isBlank(className) || StringUtils.isBlank(filedName)) {
                return generateResponse(ResultEntity.error(errorInfo), e);
            }

            // 获取字段的中文名称
            Class clazz = Class.forName(className);
            Field field = FieldUtils.getField(clazz, filedName, true);

            //             根据内容提示具体的错误信息
            errorInfo = "参数错误!";

            if (errorMessage.contains(RANGE_EXCEPTION)) {
                errorInfo = "数值超出范围!";
            } else if (errorMessage.contains(FROM_EXCEPTION)) {
                errorInfo = "类型错误！";
                if (field.getType().equals(Long.class) || field.getType().equals(Integer.class)) {
                    errorInfo = "应整数型!";
                } else if (field.getType().equals(Float.class) || field.getType().equals(Double.class)) {
                    errorInfo = "应数值型!";
                }
            }
            errorInfo = String.format("%s %s", filedName, errorInfo);
        } catch (Exception e1) {
            logger.error("处理参数错误异常信息错误", e);
        }
        return generateResponse(ResultEntity.error(errorInfo), e);
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("不支持当前请求方法", e);
        return generateResponse(ResultEntity.error("不支持当前请求方法"), e);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<?> handleHttpMediaTypeNotSupportedException(Exception e) {
        logger.error("不支持当前媒体类型", e);
        return generateResponse(ResultEntity.error("不支持当前媒体类型"), e);
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({DuplicateKeyException.class})
    public ResponseEntity<?> handleSQLException(DuplicateKeyException e) {
        logger.error("数据重复", e);
        return generateResponse(ResultEntity.error("数据重复"), e);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({SQLException.class})
    public ResponseEntity<?> handleSQLException(SQLException e) {
        logger.error("服务运行SQL异常", e);
        return generateResponse(ResultEntity.error("SQL语句执行异常,请检查日志!"), e);
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BaseException.class})
    public ResponseEntity<?> handleBaseException(BaseException e) {
        logger.error("异常", e);
        String message = e.getMessage();
        return generateResponse(ResultEntity.error(message), e);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("参数验证不通过", e);
        return generateResponse(ResultEntity.error(e.getBindingResult().getAllErrors().iterator().next().getDefaultMessage()), e);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleException(Exception e) {
        logger.error("异常", e);
        // 判断是否是被其他异常再次包裹
        Throwable[] suppressed = e.getSuppressed();
        for (Throwable throwable : suppressed) {
            if (throwable instanceof BaseException) {
                e = (BaseException) throwable;
            }
        }
        return generateResponse(ResultEntity.error(), e);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BindException.class})
    public ResponseEntity<?> handleBindException(BindException e) {
        String message = e.getBindingResult().getAllErrors().iterator().next().getDefaultMessage();
        if (message.contains("Failed to convert")) {
            message = "参数类型错误";
        }
        logger.error("异常", e);
        return generateResponse(ResultEntity.error(message), e);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<?> handAccessDeniedException(AccessDeniedException e) {
        String msg = e.getMessage();
        logger.debug("用户{}ID{}跨权限访问被拦截", UserUtils.getUserName(), UserUtils.getUserId());
        return generateResponse(ResultEntity.forbidden(msg), e);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({JwtException.class})
    public ResponseEntity<?> handleJWTException(JwtException e) {
        String msg = e.getMessage();
        logger.debug("用户{}{}", UserUtils.getUserId(), e.getMessage());
        return generateResponse(ResultEntity.unAuthorized(msg), e);
    }


    private <T extends ResultEntity> ResponseEntity<T> generateResponse(T body, Exception e) {
        HttpStatus httpStatus = HttpStatus.OK;
        ResponseEntity.BodyBuilder builder = ResponseEntity.status(httpStatus);
        return builder.body(body);
    }
}
