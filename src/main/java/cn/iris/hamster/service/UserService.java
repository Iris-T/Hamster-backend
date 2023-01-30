package cn.iris.hamster.service;

import cn.iris.hamster.bean.dto.RePwdDto;
import cn.iris.hamster.bean.entity.ResultEntity;
import cn.iris.hamster.bean.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author asus
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2022-12-27 14:40:20
*/


public interface UserService extends IService<User> {

    /**
     * 查询用户权限信息
     * @param uid 用户ID
     * @return 拼接字符串
     */
    String getUserAuthorityInfo(String uid);

    /**
     * 存储更新用户信息
     * @param user
     * @return
     */
    ResultEntity saveUser(User user);

    /**
     * 赋予用户角色
     * @param uid
     * @param rids
     * @return
     */
    ResultEntity userGrant(Long uid, List<Long> rids);

    /**
     * 将指定用户添加到对应企业，同时赋予co_admin权限
     * @param uid
     * @param cid
     * @return
     */
    ResultEntity addCoAdmin(Long uid, Long cid);

    /**
     * 取消指定用户的co_admin权限信息
     * @param uid
     * @return
     */
    ResultEntity delCoAdmin(Long uid);

    /**
     * 用户绑定至指定企业
     * @param uid
     * @param cid
     * @return
     */
    ResultEntity userBind(Long uid, Long cid);

    /**
     * 取消用户和企业的绑定
     * @param uid
     * @return
     */
    ResultEntity userDisbind(Long uid);

    /**
     * 更新用户密码
     *
     * @param rePwdDto
     * @return
     */
    ResultEntity rePwd(RePwdDto rePwdDto);
}
