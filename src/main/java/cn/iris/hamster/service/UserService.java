package cn.iris.hamster.service;

import cn.iris.hamster.bean.dto.RePwdDto;
import cn.iris.hamster.bean.dto.UserReProfileDto;
import cn.iris.hamster.common.bean.entity.ResultEntity;
import cn.iris.hamster.bean.entity.Permission;
import cn.iris.hamster.bean.entity.User;
import cn.iris.hamster.bean.vo.UserVo;
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

    /**
     * 获取当前用户展示的菜单
     * @return
     */
    List<Permission> getMenu();

    /**
     * 获取用户分页列表
     *
     * @param query 查询条件
     * @return
     */
    List<UserVo> listByLimit(User query);

    /**
     * 获取当前条件的用户总数
     * @param query 查询条件
     * @return
     */
    Integer getCountByLimit(User query);

    /**
     * 修改用户角色
     *
     * @param uid 用户ID
     * @param rid 角色ID
     */
    void changeUserRole(Long uid, Long rid);

    /**
     * 更新用户个人资料
     * @param user
     * @return
     */
    ResultEntity reProfile(UserReProfileDto user);
}
