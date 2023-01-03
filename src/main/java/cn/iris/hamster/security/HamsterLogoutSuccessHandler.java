package cn.iris.hamster.security;

import cn.hutool.json.JSONUtil;
import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.common.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 退出登录成功处理器
 *
 * @author Iris
 * @ClassName HamsterLogoutSuccessHandler
 * @date 2022/12/30 9:28
 */

@Component
public class HamsterLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication != null){
            // 手动退出
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        response.setContentType("application/json;charset=utf-8");
        ServletOutputStream outputStream = response.getOutputStream();
        response.setHeader(jwtUtils.getHeader(),"");
        ResultEntity resp = ResultEntity.success("退出成功");
        outputStream.write(JSONUtil.toJsonStr(resp).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
