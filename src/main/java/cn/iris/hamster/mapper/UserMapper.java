package cn.iris.hamster.mapper;

import cn.iris.hamster.bean.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author asus
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2022-12-27 14:40:20
* @Entity bean.pojo.cn.iris.hamster.User
*/
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据uid获取对应权限信息
     * @param uid 用户ID
     * @return 权限key字符列表
     */
    List<String> getPermsByUid(String uid);
}




