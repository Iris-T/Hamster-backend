package cn.iris.hamster.security;

import cn.iris.hamster.bean.pojo.LoginUser;
import cn.iris.hamster.bean.pojo.User;
import cn.iris.hamster.common.exception.BaseException;
import cn.iris.hamster.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * UserDetailService实现类
 *
 * @author Iris
 * @ClassName UserDetailServiceImpl
 * @date 2022/12/27 15:16
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)) {
            throw new BaseException("用户名或密码错误");
        }
        // 查询用户信息
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        // 用户名不存在则抛出异常
        if (ObjectUtils.isEmpty(user)) {
            throw new BaseException("用户名或密码错误");
        }
        // 查询用户对应权限信息
        return new LoginUser(user, getAuthority(user.getId()));
    }

    public Collection<? extends GrantedAuthority> getAuthority(String uid) {
        String authority = userService.getUserAuthorityInfo(uid);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
    }

    public Collection<? extends GrantedAuthority> getAuthority(Long uid) {
        return getAuthority(String.valueOf(uid));
    }
}
