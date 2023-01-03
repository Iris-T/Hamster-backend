package cn.iris.hamster.mapper;

import cn.iris.hamster.bean.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author asus
* @description 针对表【role(角色表)】的数据库操作Mapper
* @createDate 2022-12-29 11:33:39
* @Entity cn.iris.hamster.bean.pojo.Role
*/

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 通过Uid获取用户对应对象列表
     *
     * @param uid 用户ID
     * @return 角色列表
     */
    List<Role> getRolesByUid(String uid);
}




