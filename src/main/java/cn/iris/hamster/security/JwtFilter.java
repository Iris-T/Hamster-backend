package cn.iris.hamster.security;

import cn.iris.hamster.bean.pojo.User;
import cn.iris.hamster.common.utils.JwtUtils;
import cn.iris.hamster.common.utils.UserUtils;
import cn.iris.hamster.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Jwt监听器
 *
 * @author Iris
 * @ClassName JwtFilter
 * @date 2022/12/29 11:21
 */

@Component
public class JwtFilter extends BasicAuthenticationFilter {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailServiceImpl userDetailService;

    public JwtFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(jwtUtils.getHeader());
        if (StringUtils.isEmpty(token)) {
            // token为空则向下执行其他逻辑
            chain.doFilter(request, response);
            return;
        }
        Claims claims = jwtUtils.parseToken(token);
        if (ObjectUtils.isEmpty(claims)) {
            throw new JwtException("Token异常");
        }
        if (jwtUtils.isExpire(token)) {
            throw new JwtException("Token过期");
        }
        // token正常
        String uid = claims.getId();
        User user = userService.getById(uid);
        user.setPassword(null);
        // 获取用户信息
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, token, userDetailService.getAuthority(uid));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request,response);
    }
}
