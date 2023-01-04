package cn.iris.hamster;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Iris
 * @date 2022/12/27
 */
@SpringBootApplication
@MapperScan("cn.iris.hamster.mapper")
public class HamsterApplication {
    private static final Logger log =LoggerFactory.getLogger(HamsterApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HamsterApplication.class, args);
    }

}
