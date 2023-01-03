package cn.iris.hamster.security;

import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.common.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 用户权限过滤处理器
 *
 * @author Iris
 * @ClassName HamsterAccessDeniedHandler
 * @date 2022/12/30 9:39
 */

@Component
public class HamsterAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpStatus.HTTP_FORBIDDEN);
        ServletOutputStream outputStream = response.getOutputStream();
        ResultEntity resp = ResultEntity.forbidden("权限不足，限制访问");
        outputStream.write(JSONUtil.toJsonStr(resp).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
