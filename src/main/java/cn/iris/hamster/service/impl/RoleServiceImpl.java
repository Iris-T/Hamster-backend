package cn.iris.hamster.service.impl;

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
}




