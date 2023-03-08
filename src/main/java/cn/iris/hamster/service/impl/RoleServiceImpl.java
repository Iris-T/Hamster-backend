package cn.iris.hamster.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.bean.vo.RoleVo;
import cn.iris.hamster.common.exception.BaseException;
import cn.iris.hamster.common.utils.CommonUtils;
import cn.iris.hamster.mapper.PermissionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iris.hamster.bean.entity.Role;
import cn.iris.hamster.service.RoleService;
import cn.iris.hamster.mapper.RoleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author asus
* @description 针对表【role(角色表)】的数据库操作Service实现
* @createDate 2022-12-29 11:33:39
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{
    @Autowired
    private PermissionMapper permMapper;

    @Override
    public List<Role> getRolesByUid(Long id) {
        return baseMapper.getRolesByUid(id);
    }

    @Override
    public void changeStatus(Long rid, String status) {
        baseMapper.changeStatus(rid, status);
    }

    @Override
    public ResultEntity saveRole(Role role) {
        role.setStatus(CommonUtils.checkStatus(role.getStatus()));

        if (!isRoleValid(role)) {
            return ResultEntity.error("提交的数据格式错误");
        }

        // 检查关键字重复
        if (isKeyExist(role.getKey())) {
            return ResultEntity.error("权限关键字重复");
        }

        boolean res = ObjectUtil.isNotEmpty(role.getId()) ? updateById(role) : save(role);
        return res ? ResultEntity.success("更新成功") : ResultEntity.error("更新失败");
    }

    @Override
    public boolean isKeyExist(String key) {
        return baseMapper.isKeyExist(key) > 0;
    }

    @Transactional(rollbackFor = BaseException.class)
    @Override
    public ResultEntity grant(Long rid, List<Long> pids) {
        // 删除
        baseMapper.deleteR_P(rid);

        if (pids.size() == 0) {
            return ResultEntity.success("更新角色权限成功");
        }
        // 赋权
        Integer cnt = baseMapper.insertR_P(rid, pids);
        return cnt == pids.size() ? ResultEntity.success("更新角色权限成功") : ResultEntity.error("更新角色权限失败");
    }

    @Override
    public List<RoleVo> listByLimit(Role query) {
        List<RoleVo> roles = baseMapper.listByLimit(query.getStartIndex(), query);
        roles.forEach(r -> r.setPerms(permMapper.getPermsByRid(r.getId())));
        return roles;
    }

    @Override
    public Integer getCountByLimit(Role query) {
        return baseMapper.getCountByLimit(query);
    }

    @Override
    public Integer updateR_P(Long rid, List<Long> pids) {
        return baseMapper.insertR_P(rid, pids);
    }

    @Override
    public void deleteR_P(Long rid) {
        baseMapper.deleteR_P(rid);
    }

    @Override
    public void updateAdminPerms(Long pid, String status) {
        baseMapper.updateAdminPerms(pid, status);
    }

    private boolean isRoleValid(Role role) {
        boolean temp = true;
        // 排除无数据ID插入
        if (ObjectUtil.isNotEmpty(role.getId())) {
            temp = ObjectUtil.isNotEmpty(getById(role.getId()));
        }
        // 排除空数据
        return temp
                && StringUtils.isNotEmpty(role.getName())
                && StringUtils.isNotEmpty(role.getKey())
                && StringUtils.isNotEmpty(role.getStatus());
    }
}




