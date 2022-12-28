package cn.iris.hamster.mapper;

import cn.iris.hamster.bean.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author asus
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2022-12-27 14:40:20
* @Entity bean.pojo.cn.iris.hamster.User
*/
@Repository
public interface UserMapper extends BaseMapper<User> {

}




