package cn.iris.hamster.service;

import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * @param perm
     * @param type 0 启用，1 禁用
     * @return
     */
    ResultEntity changeStatus(Permission perm, Integer type);

    /**
     * 存储更新权限信息
     * @param perm
     * @return
     */
    ResultEntity savePerm(Permission perm);

    /**
     * 返回关键字查询结果
     * @param key
     * @return
     */
    boolean isKeyExist(String key);
}
