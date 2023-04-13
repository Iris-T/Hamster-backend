package cn.iris.hamster.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.bean.pojo.Permission;
import cn.iris.hamster.bean.vo.PermissionVo;
import cn.iris.hamster.mapper.PermissionMapper;
import cn.iris.hamster.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.iris.hamster.common.constants.CommonConstants.STATUS_ENABLE;

/**
 * @author Iris
 * @description 针对表【permission(权限功能表)】的数据库操作Service实现
 * @createDate 2023-01-03 09:19:53
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
        implements PermissionService {

    @Override
    public Long add(Permission perm) {
        // 避免重复权限关键字
        Integer exist = baseMapper.isKeyExist(perm.getKey());
        if (!isPermValid(perm) || exist > 0) {
            return -1L;
        }
        int insert = baseMapper.insert(perm);
        return insert > 0 ? perm.getId() : -1L;
    }

    @Override
    public void changeStatus(Long pid, String status) {
        baseMapper.changeStatus(pid, status);
    }

    @Override
    public boolean isKeyExist(String key) {
        return baseMapper.isKeyExist(key) > 0;
    }

    @Override
    public List<PermissionVo> listByLimit(Permission query) {
        List<PermissionVo> perms = baseMapper.listByLimit(query.getStartIndex(), query);
        List<PermissionVo> enableMenuList = perms.stream()
                .filter(p -> "0".equals(p.getIsMenu()) && STATUS_ENABLE.equals(p.getStatus()))
                .toList();
        List<PermissionVo> children;
        for (PermissionVo p : perms) {
            children = enableMenuList.stream().filter(m -> p.getId().equals(m.getParentId())).toList();
            p.setChildren(children.size() > 0 ? children : null);
        }
        return perms;
    }

    @Override
    public Integer getCountByLimit(Permission query) {
        return baseMapper.getCountByLimit(query);
    }

    private boolean isPermValid(Permission perm) {
        boolean temp = true;
        if (ObjectUtil.isNotEmpty(perm.getId())) {
            temp = ObjectUtil.isNotEmpty(getById(perm.getId()));
        }
        return temp
                && StringUtils.isNotEmpty(perm.getName())
                && StringUtils.isNotEmpty(perm.getKey())
                && StringUtils.isNotEmpty(perm.getStatus());
    }
}




