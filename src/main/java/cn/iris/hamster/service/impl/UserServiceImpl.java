package cn.iris.hamster.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.Role;
import cn.iris.hamster.bean.pojo.User;
import cn.iris.hamster.common.utils.CommonUtils;
import cn.iris.hamster.common.utils.RedisUtils;
import cn.iris.hamster.mapper.RoleMapper;
import cn.iris.hamster.mapper.UserMapper;
import cn.iris.hamster.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public String getUserAuthorityInfo(String uid) {
        String authority = "";
        // 获取角色信息
        String redisKey = REDIS_AUTHORITY_KEY_PREFIX + uid;
        if (redisUtils.hasKey(redisKey)) {
            authority = String.valueOf(redisUtils.get(redisKey));
        } else {
            List<Role> roles = roleMapper.getRolesByUid(uid);
            if (roles.size() > 0) {
                authority = roles.stream().map(r -> ROLE_PREFIX + r.getRKey()).collect(Collectors.joining(",")).concat(",");
            }
            // 获取权限信息
            List<String> perms = userMapper.getPermsByUid(uid);
            if (perms.size() > 0) {
                String permCodes = String.join(",", perms);
                authority = authority.concat(permCodes);
            }
            redisUtils.set(redisKey, authority, TOKEN_TTL_SECONDS);
        }
        return authority;
    }

    @Override
    public ResultEntity saveUser(User user) {
        if (!isUserValid(user)) {
            return ResultEntity.error("数据格式有误");
        }

        user.setId(CommonUtils.randId());
        user.setStatus(CommonUtils.checkStatus(user.getStatus()));

        boolean res = saveOrUpdate(user);
        return res ? ResultEntity.success("更新成功") : ResultEntity.error("更新失败");
    }

    private boolean isUserValid(User user) {
        boolean temp = true;
        if (ObjectUtil.isNotEmpty(user.getId())) {
            temp = ObjectUtil.isNotEmpty(getById(user.getId()));
        }
        return temp;
    }
}




