package cn.iris.hamster.common.utils;

import cn.iris.hamster.bean.pojo.User;
import cn.iris.hamster.common.constants.CommonConstants;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * 数据模拟工具类
 *
 * @author Iris
 * @ClassName MockUtils
 * @date 2022/12/30 16:49
 */

@Component
public class MockUtils {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Faker FAKER = new Faker(Locale.CHINA);
    private static final Random RANDOM = new Random();
    private static final String DEFAULT_PWD = "123456";

    public User getFakeUser() {
        long now = System.currentTimeMillis();
        User user = new User();
        user.setId(CommonUtils.randId());
        user.setName(FAKER.name().fullName());
        user.setUsername(FAKER.funnyName().name());
        user.setGender(RANDOM.nextBoolean() ? CommonConstants.GENDER_FEMALE : CommonConstants.GENDER_MALE);
        user.setPassword(passwordEncoder.encode(DEFAULT_PWD));
        user.setAddress(FAKER.address().fullAddress());
        user.setPhone(FAKER.phoneNumber().cellPhone());
        user.setCreateTime(new Date(now));
        user.setUpdateTime(new Date(now));
        return user;
    }
}
