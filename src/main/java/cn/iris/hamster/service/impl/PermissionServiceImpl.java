package cn.iris.hamster.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.bean.pojo.Permission;
import cn.iris.hamster.bean.vo.PermissionVo;
import cn.iris.hamster.common.utils.ListUtils;
import cn.iris.hamster.mapper.PermissionMapper;
import cn.iris.hamster.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static cn.iris.hamster.common.constants.CommonConstants.STATUS_ENABLE;

/**
 * @author Iris
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
    public void changeStatus(Long pid, String status) {
        permissionMapper.changeStatus(pid, status);
    }

    @Override
    public boolean isKeyExist(String key) {
        return permissionMapper.isKeyExist(key) > 0;
    }

    @Override
    public List<PermissionVo> listByLimit(Permission query) {
        List<PermissionVo> perms = permissionMapper.listByLimit(query.getStartIndex(), query);
        List<PermissionVo> enableMenuList = perms.stream().filter(p -> "0".equals(p.getIsMenu()) && STATUS_ENABLE.equals(p.getStatus())).toList();
        List<Permission> children;
        for (PermissionVo p : perms) {
            children = enableMenuList.stream().filter(m -> p.getId().equals(m.getParentId())).map(m -> {
                Permission permission = new Permission();
                BeanUtil.copyProperties(m, permission);
                return permission;
            }).toList();
            p.setChildren(children.size() > 0 ? children : null);
        }
        return perms;
    }

    @Override
    public Integer getCountByLimit(Permission query) {
        return permissionMapper.getCountByLimit(query);
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




