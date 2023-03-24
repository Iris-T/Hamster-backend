package cn.iris.hamster.mapper;

import cn.iris.hamster.bean.pojo.Permission;
import cn.iris.hamster.bean.pojo.User;
import cn.iris.hamster.bean.vo.UserVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author asus
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2022-12-27 14:40:20
* @Entity bean.pojo.cn.iris.hamster.User
*/

@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据uid获取对应权限信息
     * @param uid 用户ID
     * @return 权限key字符列表
     */
    List<String> getPermsByUid(String uid);

    /**
     * 删除用户角色
     * @param uid
     */
    void deleteU_R(Long uid);

    /**
     * 批量插入用户角色信息
     * @param uid
     * @param rid
     * @param status
     * @return
     */
    int insertU_R(Long uid, Long rid, String status);

    /**
     * 在co_user中查询指定用户的绑定状态
     * @param uid
     * @return
     */
    int isbind(Long uid);

    /**
     * 在co_user中绑定指定用户和企业信息
     * @param uid
     * @param cid
     * @return
     */
    int bind(Long uid, Long cid);

    /**
     * 取消指定用户的绑定信息
     * @param uid
     * @return
     */
    int disbind(Long uid);

    /**
     * 查看用户是否绑定指定企业
     * @param uid
     * @param cid
     */
    int isUserBindCo(Long uid, Long cid);

    /**
     * 获取当前用户绑定的企业ID
     * @param uid
     * @return
     */
    long getCurUserCoId(Long uid);

    /**
     * 获取uid对应的菜单展示组件
     * @param userId
     * @return
     */
    List<Permission> getMenuByUid(Long userId);

    /**
     * 获取当前页指定大小的用户列表
     * @param query 查询条件
     * @param start 开始下标
     * @return
     */
    List<UserVo> listByLimit(@Param("start") Integer start, @Param("query") User query);

    /**
     * 获取满足指定条件的用户总数
     * @param query 查询条件
     * @return
     */
    Integer getCountByLimit(@Param("query") User query);
}




