package cn.iris.hamster.config;

import cn.iris.hamster.common.exception.DefaultExceptionAdvice;
import cn.iris.hamster.security.*;
import cn.iris.hamster.security.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 安全配置类
 *
 * @author Iris
 * @ClassName SecurityConfig
 * @date 2022/12/28 15:24
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebMvcConfigurationSupport {
    /**
     * 端口白名单
     */
    private static final String[] URL_WHITELIST = {
            "/login",
            "/druid/**"
    };

    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginFailHandler loginFailHandler;
    @Autowired
    private HamsterLogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private HamsterAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private HamsterAccessDeniedHandler accessDeniedHandler;

    @Lazy
    @Autowired
    private JwtFilter jwtFilter;

    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * 配置过滤
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.cors().and()
                // 关闭csrf
                .csrf().disable()
                // 登录配置
                .formLogin()
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailHandler)
                //配置退出处理器
                .and().logout().logoutSuccessHandler(logoutSuccessHandler)
                .and()
                // 取消Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests(auth -> auth
                        // 放开白名单接口，其余接口均需认证
                        .antMatchers(URL_WHITELIST).permitAll()
                        .anyRequest().authenticated()
                )
                // 拦截异常
                .exceptionHandling()
                // 未认证
                .authenticationEntryPoint(authenticationEntryPoint)
                // 权限不足
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .userDetailsService(userDetailService)
                // 请求过滤器
                .addFilter(jwtFilter)
                .build();
    }
}
