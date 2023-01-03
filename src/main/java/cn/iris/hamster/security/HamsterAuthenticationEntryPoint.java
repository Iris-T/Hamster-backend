package cn.iris.hamster.security;

import cn.hutool.json.JSONUtil;
import cn.iris.hamster.bean.entity.ResultEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 用户是否认证过滤
 *
 * @author Iris
 * @ClassName HamsterAuthenticationEntryPoint
 * @date 2022/12/30 9:36
 */

@Component
public class HamsterAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ServletOutputStream outputStream = response.getOutputStream();
        ResultEntity resp = ResultEntity.unAuthorized("尚未登录，请登陆后再进行操作");
        outputStream.write(JSONUtil.toJsonStr(resp).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
