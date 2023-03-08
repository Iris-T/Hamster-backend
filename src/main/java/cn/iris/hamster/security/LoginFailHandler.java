package cn.iris.hamster.security;

import cn.hutool.json.JSONUtil;
import cn.iris.hamster.common.bean.entity.ResultEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 登录失败处理器
 *
 * @author Iris
 * @ClassName LoginFailHandler
 * @date 2022/12/30 9:03
 */
@Component
public class LoginFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json:charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        ResultEntity resp = ResultEntity.error("用户名或密码不正确");
        outputStream.write(JSONUtil.toJsonStr(resp).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
