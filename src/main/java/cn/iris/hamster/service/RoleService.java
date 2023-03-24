package cn.iris.hamster.service;

import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.Role;
import cn.iris.hamster.bean.vo.RoleVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Iris
* @description 针对表【role(角色表)】的数据库操作Service
* @createDate 2022-12-29 11:33:39
*/
public interface RoleService extends IService<Role> {

    /**
     * getRolesByUid(String id)方法重写
     *
     * @param id uid
     * @return List->String
     */
    List<Role> getRolesByUid(Long id);

    /**
     * 更改用户-角色状态
     * @param rid 角色对象
     * @param status 操作行为类型
     * @return 操作结果
     */
    void changeStatus(Long rid, String status);

    /**
     * 存储角色信息
     * @param role 角色类信息
     * @return
     */
    ResultEntity saveRole(Role role);

    /**
     * 关键字是否存在
     * @param key
     * @return
     */
    boolean isKeyExist(String key);

    /**
     * 对角色赋权,覆盖原有权限信息
     * @param rid
     * @param pids
     * @return
     */
    ResultEntity grant(Long rid, List<Long> pids);

    /**
     * 获取查询条件的角色列表
     * @param query
     * @return
     */
    List<RoleVo> listByLimit(Role query);

    /**
     * 获取当前条件的角色总数
     *
     * @param query
     * @return
     */
    Integer getCountByLimit(Role query);

    /**
     * 更新角色拥有的权限
     * @param rid
     * @param pids
     */
    Integer updateR_P(Long rid, List<Long> pids);

    /**
     * 删除角色拥有的权限
     * @param rid
     */
    void deleteR_P(Long rid);

    /**
     * 更新系统管理员角色权限
     * @param pid
     * @param status
     */
    void updateAdminPerms(Long pid, String status);
}
