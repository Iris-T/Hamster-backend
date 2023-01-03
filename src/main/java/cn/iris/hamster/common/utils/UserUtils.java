package cn.iris.hamster.common.utils;

import cn.hutool.crypto.digest.MD5;
import cn.iris.hamster.bean.entity.LoginUser;
import cn.iris.hamster.bean.pojo.User;
import com.alibaba.ttl.TransmittableThreadLocal;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户工具类
 *
 * @author Iris
 * @ClassName UserUtils
 * @date 2022/12/30 13:07
 */
public class UserUtils {
    public static final String KEY_USERINFO = "userinfo";

    public static ThreadLocal<Map<String, Object>> threadLocal = new TransmittableThreadLocal<>();
    private static final Logger log = LoggerFactory.getLogger(UserUtils.class);

    public static User getUserInfo() {
        User user = getByThreadLocalKey(KEY_USERINFO, User.class);
        if (user != null) {
            log.trace("获取到用户ID{},用户名{},姓名{}",user.getId(), user.getUsername(), user.getName());
            return user;
        }
        user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.debug("获取到用户ID{},用户名{},姓名{}",user.getId(), user.getUsername(), user.getName());
        setThreadLocalData(KEY_USERINFO, user);
        return user;
    }

    public static void setUserInfo(User user) {
        setThreadLocalData(KEY_USERINFO, user);
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
        return getUserInfo().getId();
    }

    /**
     * 获取当前用户权限信息
     * @return 用户权限信息
     */
    public static Collection<? extends GrantedAuthority> getAuthority() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

    public static Map<String, Object> threadLocalMap() {
        Map<String, Object> dataMap = threadLocal.get();
        if (dataMap == null) {
            dataMap = new HashMap<>();
            threadLocal.set(dataMap);
        }
        return dataMap;
    }

    public static <T> T getByThreadLocalKey(String key, Class<T> clazz) {
        Object obj = threadLocalMap().get(key);
        if (obj == null) {
            return null;
        }
        if (clazz.isInstance(obj)) {
            return (T) obj;
        } else {
            return null;
        }
    }

    public static <T> void setThreadLocalData(String key, T data) {
        threadLocalMap().put(key, data);
    }


    public static void clean() {
        threadLocal.remove();
    }
}
