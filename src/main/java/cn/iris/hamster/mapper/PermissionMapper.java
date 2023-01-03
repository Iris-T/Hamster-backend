package cn.iris.hamster.mapper;

import cn.iris.hamster.bean.pojo.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author asus
* @description 针对表【permission(权限功能表)】的数据库操作Mapper
* @createDate 2023-01-03 09:19:53
* @Entity cn.iris.hamster.bean.pojo.Permission
*/

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 权限关键词是否存在
     * @param key 权限对象
     * @return boolean值
     */
    Integer isKeyExist(String key);
}




