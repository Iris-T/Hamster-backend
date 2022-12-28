package cn.iris.hamster.bean.entity;

import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 统一响应结果类
 *
 * @author Iris
 * @ClassName ResultEntity
 * @date 2022/12/28 11:14
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultEntity<T> {
    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 提示信息
     */
    private final String msg;
    /**
     * 返回数据
     */
    private final T data;

    private ResultEntity(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultEntity<Object> success(String msg, Object data) {
        return new ResultEntity<>(HttpStatus.HTTP_OK, msg, data);
    }

    public static ResultEntity<Object> error(String msg, Object data) {
        return new ResultEntity<>(HttpStatus.HTTP_INTERNAL_ERROR, msg, data);
    }

    public static ResultEntity<Object> unAuthorized(String msg, Object data) {
        return new ResultEntity<>(HttpStatus.HTTP_UNAUTHORIZED, msg, data);
    }

    public static ResultEntity<Object> forbidden(String msg, Object data) {
        return new ResultEntity<>(HttpStatus.HTTP_FORBIDDEN, msg, data);
    }

    public static ResultEntity success() {
        return success("请求成功");
    }

    public static ResultEntity success(String msg) {
        return success(msg, null);
    }

    public static ResultEntity error() {
        return error("服务器错误,请联系管理员");
    }

    public static ResultEntity error(String msg) {
        return error(msg, null);
    }

    public static ResultEntity unAuthorized() {
        return unAuthorized("请登录后继续操作");
    }

    public static ResultEntity unAuthorized(String msg) {
        return unAuthorized(msg, null);
    }

    public static ResultEntity forbidden() {
        return forbidden("无权限访问");
    }

    public static ResultEntity forbidden(String msg) {
        return forbidden(msg, null);
    }

}
