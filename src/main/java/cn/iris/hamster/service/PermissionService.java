package cn.iris.hamster.service;

import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.Permission;
import cn.iris.hamster.bean.vo.PermissionVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author asus
* @description 针对表【permission(权限功能表)】的数据库操作Service
* @createDate 2023-01-03 09:19:53
*/
public interface PermissionService extends IService<Permission> {

    /**
     * 返回存储的操作结果
     *
     * @param perm Permission实体对象
     * @return true / false
     */
    Long add(Permission perm);

    /**
     * 修改权限状态
     * @param pid 权限id
     * @param status 0 启用，1 禁用
     * @return
     */
    void changeStatus(Long pid, String status);

    /**
     * 返回关键字查询结果
     * @param key
     * @return
     */
    boolean isKeyExist(String key);

    /**
     * 分页查询权限对象信息
     * @param query
     * @return
     */
    List<PermissionVo> listByLimit(Permission query);

    /**
     * 根据查询条件获取总数
     * @param query
     * @return
     */
    Integer getCountByLimit(Permission query);
}
