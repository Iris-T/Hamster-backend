package cn.iris.hamster.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iris.hamster.bean.pojo.User;
import cn.iris.hamster.service.UserService;
import cn.iris.hamster.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author asus
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2022-12-27 14:40:20
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




