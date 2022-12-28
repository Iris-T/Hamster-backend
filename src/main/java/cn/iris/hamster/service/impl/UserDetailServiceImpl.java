package cn.iris.hamster.service.impl;

import cn.iris.hamster.bean.entity.LoginUser;
import cn.iris.hamster.bean.pojo.User;
import cn.iris.hamster.common.exception.BaseException;
import cn.iris.hamster.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

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
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper);
        // 用户名不存在则抛出异常
        if (ObjectUtils.isEmpty(user)) {
            throw new BaseException("用户名或密码错误");
        }

        // 查询用户对应权限信息

        return new LoginUser(user);
    }
}
