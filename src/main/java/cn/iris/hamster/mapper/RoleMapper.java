package cn.iris.hamster.mapper;

import cn.iris.hamster.bean.entity.Role;
import cn.iris.hamster.bean.vo.RoleVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author asus
* @description 针对表【role(角色表)】的数据库操作Mapper
* @createDate 2022-12-29 11:33:39
* @Entity cn.iris.hamster.bean.entity.Role
*/

@Repository
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 通过Uid获取用户对应对象列表
     *
     * @param uid 用户ID
     * @return 角色列表
     */
    List<Role> getRolesByUid(Long uid);

    /**
     * key是否存在
     * @param key
     * @return
     */
    Integer isKeyExist(String key);

    /**
     * 对角色权限进行批量更新
     * @param rid
     * @param pids
     */
    Integer insertR_P(@Param("rid") Long rid, @Param("pids") List<Long> pids);

    /**
     * 对指定角色ID权限进行删除
     * @param rid
     */
    void deleteR_P(Long rid);

    /**
     * 修改对应角色ID的用户_角色信息启用状态
     * @param rid
     */
    void changeStatus(Long rid, String status);

    /**
     * 获取查询条件的角色列表
     *
     * @param startIndex
     * @param query
     * @return
     */
    List<RoleVo> listByLimit(@Param("start") Integer startIndex, @Param("query") Role query);

    /**
     * 获取当前条件的角色总数
     *
     * @param query
     * @return
     */
    Integer getCountByLimit(@Param("query") Role query);

    /**
     * 系统管理员权限更新
     * @param pid
     * @param status
     */
    void updateAdminPerms(@Param("pid") Long pid, @Param("status") String status);
}




