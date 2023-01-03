package cn.iris.hamster.service.impl;

import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.common.constants.CommonConstants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iris.hamster.bean.pojo.Role;
import cn.iris.hamster.service.RoleService;
import cn.iris.hamster.mapper.RoleMapper;
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
}




