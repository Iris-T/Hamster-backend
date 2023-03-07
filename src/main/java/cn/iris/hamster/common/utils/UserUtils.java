package cn.iris.hamster.common.utils;

import cn.iris.hamster.bean.pojo.User;
import com.alibaba.ttl.TransmittableThreadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户工具类
 *
 * @author Iris
 * @ClassName UserUtils
 * @date 2022/12/30 13:07
 */
public class UserUtils {
    public static ThreadLocal<User> threadLocal = new TransmittableThreadLocal<>();
    private static final Logger log = LoggerFactory.getLogger(UserUtils.class);

    public static void setUserInfo(User user) {
        threadLocal.set(user);
    }

    public static void delUserInfo() {
        SecurityContextHolder.getContext().setAuthentication(null);
        SecurityContextHolder.clearContext();
        threadLocal.set(null);
    }

    /**
     * 获取当前用户Token
     * @return Token字符串
     */
    public static String getToken() {
        return ((String) SecurityContextHolder.getContext().getAuthentication().getCredentials());
    }

    /**
     * 获取当前用户登录用户名
     * @return 用户名
     */
    public static String getUserLoginName() {
        return getUserInfo().getUsername();
    }

    /**
     * 获取当前用户姓名
     * @return 用户姓名
     */
    public static String getUserName() {
        return getUserInfo().getName();
    }

    /**
     * 获取当前用户ID
     * @return 用户ID
     */
    public static Long getUserId() {
        Long uid = getUserInfo().getId();
        log.info("获取到用户ID===>{}", uid);
        return uid;
    }

    /**
     * 获取当前用户权限信息
     * @return 用户权限信息
     */
    public static Collection<? extends GrantedAuthority> getAuthority() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

    public static User getUserInfo() {
        User user = threadLocal.get();
        if (user != null) {
            log.info("获取到用户ID===>{},用户名===>{},姓名===>{}", user.getId(), user.getUsername(), user.getName());
        }
        return user;
    }

    public static void clean() {
        threadLocal.remove();
    }
}
