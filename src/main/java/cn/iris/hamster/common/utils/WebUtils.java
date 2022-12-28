package cn.iris.hamster.common.utils;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpStatus;
import org.apache.commons.lang3.CharEncoding;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 数据渲染工具类
 *
 * @author Iris
 * @ClassName WebUtils
 * @date 2022/12/28 14:26
 */
public class WebUtils {

    public static String randerString(HttpServletResponse resp, String str) {
        try {
            resp.setStatus(HttpStatus.HTTP_OK);
            resp.setContentType(ContentType.JSON.getValue());
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().print(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
