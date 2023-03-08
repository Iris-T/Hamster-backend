package cn.iris.hamster.service;

import cn.iris.hamster.bean.entity.SystemField;
import cn.iris.hamster.bean.vo.SystemFieldVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author asus
* @description 针对表【system_field(系统字段表)】的数据库操作Service
* @createDate 2023-02-02 15:21:30
*/
public interface SystemFieldService extends IService<SystemField> {

    /**
     * 按限制条件返回分页数据
     * @param query
     * @return
     */
    List<SystemFieldVo> listByLimit(SystemField query);

    /**
     * 按限制条件返回符合记录的总数
     * @param query
     * @return
     */
    Integer getCountByLimit(SystemField query);
}
