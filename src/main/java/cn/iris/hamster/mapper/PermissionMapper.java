package cn.iris.hamster.mapper;

import cn.iris.hamster.bean.pojo.Permission;
import cn.iris.hamster.bean.vo.PermissionVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author asus
* @description 针对表【permission(权限功能表)】的数据库操作Mapper
* @createDate 2023-01-03 09:19:53
* @Entity cn.iris.hamster.bean.pojo.Permission
*/

@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 权限关键词是否存在
     * @param key 权限对象
     * @return boolean值
     */
    Integer isKeyExist(String key);

    /**
     * 更新系统管理员权限信息
     * @param pid
     */
    void updateAdminR_P(Long pid);

    /**
     * 修改对应pid的角色_权限信息启用状态
     * @param pid
     */
    void changeStatus(Long pid, String status);

    /**
     * 根据rid获取对应pid列表
     * @param rid 角色id
     * @return pids
     */
    List<Permission> getPermsByRid(@Param("rid") Long rid);

    /**
     * 分页查询权限对象信息
     *
     * @param start
     * @param query
     * @return
     */
    List<PermissionVo> listByLimit(@Param("start") Integer start, @Param("query") Permission query);

    /**
     * 根据查询条件获取总数
     * @param query
     * @return
     */
    Integer getCountByLimit(@Param("query") Permission query);
}




