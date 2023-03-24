package cn.iris.hamster.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.bean.dto.RePwdDto;
import cn.iris.hamster.bean.dto.UserReProfileDto;
import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.Permission;
import cn.iris.hamster.bean.pojo.Role;
import cn.iris.hamster.bean.pojo.User;
import cn.iris.hamster.bean.vo.UserVo;
import cn.iris.hamster.common.exception.BaseException;
import cn.iris.hamster.common.utils.RedisUtils;
import cn.iris.hamster.common.utils.UserUtils;
import cn.iris.hamster.mapper.CooperativeMapper;
import cn.iris.hamster.mapper.RoleMapper;
import cn.iris.hamster.mapper.UserMapper;
import cn.iris.hamster.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static cn.iris.hamster.common.constants.CommonConstants.*;

/**
 * @author Iris
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2022-12-27 14:40:20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private CooperativeMapper cooperativeMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = BaseException.class)
    @Override
    public String getUserAuthorityInfo(String uid) {
        String authority = "";
        // 获取角色信息
        String redisKey = REDIS_AUTHORITY_KEY_PREFIX + uid;
        List<Role> roles = roleMapper.getRolesByUid(Long.valueOf(uid));
        if (roles.size() > 0) {
            authority = roles.stream().map(r -> ROLE_PREFIX + r.getKey()).collect(Collectors.joining(",")).concat(",");
        }
        // 获取权限信息
        List<String> perms = baseMapper.getPermsByUid(uid);
        if (perms.size() > 0) {
            String permCodes = String.join(",", perms);
            authority = authority.concat(permCodes);
        }
        redisUtils.set(redisKey, authority, TOKEN_TTL_SECONDS);
        return authority;
    }

    @Override
    public ResultEntity userBind(Long uid, Long cid) {
        // 查询用户绑定企业状态
        int cnt = baseMapper.isbind(uid);
        if (cnt > 0) {
            return ResultEntity.error("用户已绑定企业");
        }
        // 添加绑定数据
        cnt = baseMapper.bind(uid, cid);
        return cnt > 0 ? ResultEntity.success("绑定成功") : ResultEntity.error("绑定失败");
    }

    @Override
    public ResultEntity userDisbind(Long uid) {
        return baseMapper.disbind(uid) > 0 ? ResultEntity.success("取消绑定成功") : ResultEntity.error("取消绑定失败");
    }

    @Override
    public ResultEntity rePwd(RePwdDto rePwdDto) {
        Long userId = UserUtils.getUserId();
        User user = baseMapper.selectById(userId);
        if (ObjectUtil.isEmpty(user)) {
            return ResultEntity.error("用户数据不存在");
        }
        if (!passwordEncoder.matches(rePwdDto.getOldPassword(), user.getPassword())) {
            return ResultEntity.error("原密码校验错误，请重新输入");
        }

        user.setPassword(passwordEncoder.encode(rePwdDto.getNewPassword()));
        int update = baseMapper.updateById(user);

        if (update > 0) {
            // 删除当前登录凭证
            redisUtils.del(REDIS_CACHE_TOKEN_PREFIX + userId);
            redisUtils.del(REDIS_AUTHORITY_KEY_PREFIX + userId);
            // 删除服务器内用户信息
            UserUtils.delUserInfo();
            return ResultEntity.success("更新成功");
        } else {
            return ResultEntity.error("更新失败");
        }
    }

    @Override
    public List<Permission> getMenu() {
        Long userId = UserUtils.getUserId();
        List<Permission> perms = baseMapper.getMenuByUid(userId);

        // 将扁平结构转换为嵌套结构
        ArrayList<Permission> menu = new ArrayList<>();
        for (Permission perm : perms) {
            if (perm.getParentId() == null) {
                perm.setChildren(getChildren(perm.getId(), perms));
                menu.add(perm);
            }
        }
        menu.sort(Comparator.comparingLong(Permission::getId));
        return menu;
    }

    @Override
    public List<UserVo> listByLimit(User query) {
        // 根据条件返回用户列表
        return baseMapper.listByLimit(query.getStartIndex(), query);
    }

    @Override
    public Integer getCountByLimit(User query) {
        return baseMapper.getCountByLimit(query);
    }

    @Transactional(rollbackFor = BaseException.class)
    @Override
    public void changeUserRole(Long uid, Long rid) {
        // 删除原有角色绑定
        baseMapper.deleteU_R(uid);
        // 增加新角色绑定
        baseMapper.insertU_R(uid, rid, STATUS_ENABLE);
    }

    @Override
    public ResultEntity reProfile(UserReProfileDto user) {
        User updateUser = new User();
        updateUser.setId(UserUtils.getUserId());
        // 数据复制时忽略空值
        BeanUtil.copyProperties(user, updateUser);
        baseMapper.updateById(updateUser);
        // 刷新ContextHolder中的数据
        UserUtils.setUserInfo(baseMapper.selectById(UserUtils.getUserId()));
        return ResultEntity.success("个人信息更新成功");
    }

    /**
     * 查找指定id菜单的子菜单
     *
     * @param id
     * @param perms
     * @return
     */
    private List<Permission> getChildren(Long id, List<Permission> perms) {
        ArrayList<Permission> children = new ArrayList<>();
        for (Permission perm : perms) {
            if (id.equals(perm.getParentId())) {
                perm.setChildren(getChildren(perm.getId(), perms));
                children.add(perm);
            }
        }
        children.sort(Comparator.comparingLong(Permission::getId));
        return children;
    }

    private boolean isUserValid(User user) {
        boolean temp = true;
        if (ObjectUtil.isNotEmpty(user.getId())) {
            temp = ObjectUtil.isNotEmpty(getById(user.getId()));
        }
        return temp;
    }
}
