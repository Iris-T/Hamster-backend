package cn.iris.hamster;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Iris
 * @date 2022/12/27
 */
@SpringBootApplication
@MapperScan("cn.iris.hamster.mapper")
public class HamsterApplication {

    public static void main(String[] args) {
        SpringApplication.run(HamsterApplication.class, args);
    }

}
