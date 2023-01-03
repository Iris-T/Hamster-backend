package cn.iris.hamster.config;

import cn.iris.hamster.common.utils.UserUtils;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 数据填充处理器
 *
 * @author Iris
 * @ClassName HamsterMetaObejctHandler
 * @date 2023/1/3 9:29
 */

@Component
public class HamsterMetaObejctHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        long now = System.currentTimeMillis();
        metaObject.setValue("createTime", new Date(now));
        metaObject.setValue("updateTime", new Date(now));
        metaObject.setValue("createBy", UserUtils.getUserId());
        metaObject.setValue("updateBy", UserUtils.getUserId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        long now = System.currentTimeMillis();
        metaObject.setValue("updateTime", new Date(now));
        metaObject.setValue("updateBy", UserUtils.getUserId());
    }
}
