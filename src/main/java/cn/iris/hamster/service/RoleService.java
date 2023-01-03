package cn.iris.hamster.service;

import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author asus
* @description 针对表【role(角色表)】的数据库操作Service
* @createDate 2022-12-29 11:33:39
*/
public interface RoleService extends IService<Role> {

    /**
     * 通过Uid获取角色列表
     *
     * @param id uid
     * @return 字符串拼接列表
     */
    List<Role> getRolesByUid(String id);

    /**
     * getRolesByUid(String id)方法重写
     *
     * @param id uid
     * @return List->String
     */
    List<Role> getRolesByUid(Long id);

    /**
     * 更改角色状态
     * @param role 角色对象
     * @param type 操作行为类型
     * @return 操作结果
     */
    ResultEntity changeStatus(Role role, Integer type);
}
