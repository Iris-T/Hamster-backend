package cn.iris.hamster.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.vo.RoleVo;
import cn.iris.hamster.common.exception.BaseException;
import cn.iris.hamster.common.utils.CommonUtils;
import cn.iris.hamster.mapper.PermissionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iris.hamster.bean.pojo.Role;
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
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permMapper;

    @Override
    public List<Role> getRolesByUid(String id) {
        return roleMapper.getRolesByUid(id);
    }

    @Override
    public List<Role> getRolesByUid(Long id) {
        return getRolesByUid(String.valueOf(id));
    }

    @Override
    public void changeStatus(Long rid, String status) {
        roleMapper.changeStatus(rid, status);
    }

    @Override
    public ResultEntity saveRole(Role role) {
        role.setStatus(CommonUtils.checkStatus(role.getStatus()));

        if (!isRoleValid(role)) {
            return ResultEntity.error("提交的数据格式错误");
        }

        // 检查关键字重复
        if (isKeyExist(role.getRKey())) {
            return ResultEntity.error("权限关键字重复");
        }

        boolean res = ObjectUtil.isNotEmpty(role.getId()) ? updateById(role) : save(role);
        return res ? ResultEntity.success("更新成功") : ResultEntity.error("更新失败");
    }

    @Override
    public boolean isKeyExist(String key) {
        return roleMapper.isKeyExist(key) > 0;
    }

    @Transactional(rollbackFor = BaseException.class)
    @Override
    public ResultEntity grant(Long rid, List<Long> pids) {
        // 删除
        roleMapper.deleteR_P(rid);

        if (pids.size() == 0) {
            return ResultEntity.success("更新角色权限成功");
        }
        // 赋权
        Integer cnt = roleMapper.insertR_P(rid, pids);
        return cnt == pids.size() ? ResultEntity.success("更新角色权限成功") : ResultEntity.error("更新角色权限失败");
    }

    @Override
    public List<RoleVo> listByLimit(Role query) {
        List<RoleVo> roles = roleMapper.listByLimit(query.getStartIndex(), query);
        roles.forEach(r -> r.setPerms(permMapper.getPermsByRid(r.getId())));
        return roles;
    }

    @Override
    public Integer getCountByLimit(Role query) {
        return roleMapper.getCountByLimit(query);
    }

    @Override
    public Integer updateR_P(Long rid, List<Long> pids) {
        return roleMapper.insertR_P(rid, pids);
    }

    @Override
    public void deleteR_P(Long rid) {
        roleMapper.deleteR_P(rid);
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
                && StringUtils.isNotEmpty(role.getRKey())
                && StringUtils.isNotEmpty(role.getStatus());
    }
}




