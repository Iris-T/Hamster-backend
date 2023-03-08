package cn.iris.hamster.common.mybatis.config;

import cn.iris.hamster.common.exception.BaseException;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis配置
 *
 * @author Iris
 * @ClassName MybatisExtConfig
 * @date 2023/3/8 10:00
 */
@Configuration
public class MybatisExtConfig implements InitializingBean {
    public static final Logger log = LoggerFactory.getLogger(MybatisExtConfig.class);
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;


    @Override
    public void afterPropertiesSet() throws BaseException {
        log.info("加载MybatisExtConfig....");
        sqlSessionTemplate.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
        sqlSessionTemplate.getConfiguration().setCacheEnabled(false);
        sqlSessionTemplate.getConfiguration().setLocalCacheScope(LocalCacheScope.STATEMENT);
    }
}
