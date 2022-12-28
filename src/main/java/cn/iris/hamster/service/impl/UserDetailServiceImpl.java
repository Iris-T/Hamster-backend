package cn.iris.hamster.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * UserDetailService实现类
 *
 * @author Iris
 * @ClassName UserDetailServiceImpl
 * @date 2022/12/27 15:16
 */
public class UserDetailServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO 查询用户信息

        // TODO 查询用户对应权限信息
    }
}
