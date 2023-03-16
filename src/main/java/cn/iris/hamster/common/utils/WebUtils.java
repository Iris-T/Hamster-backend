package cn.iris.hamster.common.utils;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpStatus;
import cn.iris.hamster.common.exception.BaseException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 数据渲染工具类
 *
 * @author Iris
 * @ClassName WebUtils
 * @date 2022/12/28 14:26
 */
public class WebUtils {

    /**
     * 格式化响应结果中的字符串
     * @param resp 响应结果对象
     * @param str 字符串
     */
    public static void renderString(HttpServletResponse resp, String str) {
        try {
            resp.setStatus(HttpStatus.HTTP_OK);
            resp.setContentType(ContentType.JSON.getValue());
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().print(str);
        } catch (IOException e) {
            throw new BaseException(e);
        }
    }

    /**
     * 格式化导出的Excel
     * @param response 响应结果对象
     * @param filename 导出文件名
     */
    public static void setExcelResponseProp(HttpServletResponse response, String filename) {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(filename, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        response.setHeader("content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
    }
}
