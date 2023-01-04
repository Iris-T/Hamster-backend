package cn.iris.hamster.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.common.utils.CommonUtils;
import cn.iris.hamster.mapper.PermissionMapper;
import cn.iris.hamster.bean.pojo.Permission;
import cn.iris.hamster.service.PermissionService;
import cn.iris.hamster.common.constants.CommonConstants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author asus
 * @description 针对表【permission(权限功能表)】的数据库操作Service实现
 * @createDate 2023-01-03 09:19:53
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
        implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Long add(Permission perm) {
        // 避免重复权限关键字
        Integer exist = permissionMapper.isKeyExist(perm.getPKey());
        if (!isPermValid(perm) || exist > 0) {
            return -1L;
        }
        int insert = permissionMapper.insert(perm);
        return insert > 0 ? perm.getId() : -1L;
    }

    @Override
    public ResultEntity changeStatus(Permission perm, Integer type) {
        int res;
        // 启用
        if (type.toString().equals(CommonConstants.STATUS_ENABLE)) {
            if (CommonConstants.STATUS_ENABLE.equals(perm.getStatus())) {
                return ResultEntity.error("已被启用");
            }
            perm.setStatus(CommonConstants.STATUS_ENABLE);
            res = permissionMapper.updateById(perm);
        } else { // 禁用
            if (CommonConstants.STATUS_DISABLE.equals(perm.getStatus())) {
                return ResultEntity.error("已被禁用");
            }
            perm.setStatus(CommonConstants.STATUS_DISABLE);
            res = permissionMapper.updateById(perm);
        }
        return res > 0 ? ResultEntity.success("修改状态成功") : ResultEntity.error();
    }

    @Override
    public ResultEntity savePerm(Permission perm) {
        // 检查字段是否正确
        if (!isPermValid(perm)) {
            return ResultEntity.error("提交的数据格式错误");
        }

        // 检查权限状态设置
        perm.setStatus(CommonUtils.checkStatus(perm.getStatus()));

        // 检查关键字重复
        if (isKeyExist(perm.getPKey())) {
            return ResultEntity.error("权限关键字重复");
        }

        boolean res = saveOrUpdate(perm);

        // 更新管理员角色的权限


        return res ? ResultEntity.success("更新成功")
                : ResultEntity.error("更新失败");
    }

    @Override
    public boolean isKeyExist(String key) {
        return permissionMapper.isKeyExist(key) > 0;
    }

    private boolean isPermValid(Permission perm) {
        boolean temp = true;
        if (ObjectUtil.isNotEmpty(perm.getId())) {
            temp = ObjectUtil.isNotEmpty(getById(perm.getId()));
        }
        return temp
                && StringUtils.isNotEmpty(perm.getName())
                && StringUtils.isNotEmpty(perm.getPKey())
                && StringUtils.isNotEmpty(perm.getStatus());
    }
}




