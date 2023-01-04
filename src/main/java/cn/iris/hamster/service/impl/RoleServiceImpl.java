package cn.iris.hamster.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.common.constants.CommonConstants;
import cn.iris.hamster.common.utils.CommonUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iris.hamster.bean.pojo.Role;
import cn.iris.hamster.service.RoleService;
import cn.iris.hamster.mapper.RoleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Role> getRolesByUid(String id) {
        return roleMapper.getRolesByUid(id);
    }

    @Override
    public List<Role> getRolesByUid(Long id) {
        return getRolesByUid(String.valueOf(id));
    }

    @Override
    public ResultEntity changeStatus(Role role, Integer type) {
        int res;
        // 启用
        if (type.toString().equals(CommonConstants.STATUS_ENABLE)) {
            if (CommonConstants.STATUS_ENABLE.equals(role.getStatus())) {
                return ResultEntity.error("已被启用");
            }
            role.setStatus(CommonConstants.STATUS_ENABLE);
            res = roleMapper.updateById(role);
        } else { // 禁用
            if (CommonConstants.STATUS_DISABLE.equals(role.getStatus())) {
                return ResultEntity.error("已被禁用");
            }
            role.setStatus(CommonConstants.STATUS_DISABLE);
            res = roleMapper.updateById(role);
        }
        return res > 0 ? ResultEntity.success("修改状态成功") : ResultEntity.error();
    }

    @Override
    public ResultEntity saveRole(Role role) {
        if (!isRoleValid(role)) {
            return ResultEntity.error("提交的数据格式错误");
        }

        role.setStatus(CommonUtils.checkStatus(role.getStatus()));

        // 检查关键字重复
        if (isKeyExist(role.getRKey())) {
            return ResultEntity.error("权限关键字重复");
        }

        boolean res = saveOrUpdate(role);
        return res ? ResultEntity.success("更新成功") : ResultEntity.error("更新失败");
    }

    @Override
    public boolean isKeyExist(String key) {
        return roleMapper.isKeyExist(key) > 0;
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




