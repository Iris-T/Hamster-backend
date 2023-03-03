package cn.iris.hamster.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.vo.PermissionVo;
import cn.iris.hamster.common.utils.CommonUtils;
import cn.iris.hamster.mapper.PermissionMapper;
import cn.iris.hamster.bean.pojo.Permission;
import cn.iris.hamster.service.PermissionService;
import cn.iris.hamster.common.constants.CommonConstants;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        perms.forEach(p -> p.setChildren(permissionMapper.selectList(
                new QueryWrapper<Permission>().eq("parent_id", p.getId())
                        .eq("status", CommonConstants.STATUS_ENABLE)
        )));
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




