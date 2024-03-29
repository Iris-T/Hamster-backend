package cn.iris.hamster.common.mybatis.config;

import cn.iris.hamster.common.utils.UserUtils;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 数据填充处理器
 *
 * @author Iris
 * @ClassName MybatisMetaObjectHandler
 * @date 2023/1/3 9:29
 */

@Component
public class MybatisMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Long uid = UserUtils.getUserId();
        long now = System.currentTimeMillis();
        metaObject.setValue("createTime", new Date(now));
        metaObject.setValue("updateTime", new Date(now));
        metaObject.setValue("createBy", uid);
        metaObject.setValue("updateBy", uid);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Long uid = UserUtils.getUserId();
        long now = System.currentTimeMillis();
        metaObject.setValue("updateTime", new Date(now));
        metaObject.setValue("updateBy", uid);
    }
}
