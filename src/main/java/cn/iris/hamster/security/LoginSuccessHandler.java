package cn.iris.hamster.security;

import cn.hutool.json.JSONUtil;
import cn.iris.hamster.bean.pojo.LoginUser;
import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.User;
import cn.iris.hamster.common.exception.BaseException;
import cn.iris.hamster.common.utils.JwtUtils;
import cn.iris.hamster.common.utils.RedisUtils;
import cn.iris.hamster.service.RoleService;
import cn.iris.hamster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static cn.iris.hamster.common.constants.CommonConstants.*;

/**
 * 登录成功过滤器
 *
 * @author Iris
 * @ClassName LoginSuccessHandler
 * @date 2022/12/29 11:09
 */

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private UserService userService;

    @Override
    @Transactional(rollbackFor = BaseException.class)
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        response.setContentType("application/json;charset=utf-8");
        ServletOutputStream outputStream = response.getOutputStream();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        String token = jwtUtils.createToken(loginUser);
        User user = userService.getById(loginUser.getUser().getId());

        // 隐藏敏感信息
        user.setPassword(null);
        user.setIdNo(null);
        user.setAddress(null);
        user.setName(null);
        user.setPhone(null);
        redisUtils.set(REDIS_CACHE_TOKEN_PREFIX + user.getId(), user, TOKEN_TTL_SECONDS);

        response.setHeader(jwtUtils.getHeader(), token);
        ResultEntity res = ResultEntity.success("登录成功");
        outputStream.write(JSONUtil.toJsonStr(res).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
