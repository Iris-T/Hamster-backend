package cn.iris.hamster.common.utils;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 公共工具类
 *
 * @author Iris
 * @ClassName CommonUtils
 * @date 2022/12/30 16:52
 */

public class CommonUtils {
    private static final Long EPOCH = 1671984000000L;
    private static final int max12bit = 4095;
    private static final long max41bit= 1099511627775L;

    /**
     * 随机16位ID
     * @return Long类型ID
     */
   public static Long randId() {
       long time = System.currentTimeMillis() - EPOCH  + max41bit;
       // 二进制的 毫秒级时间戳
       String base = Long.toBinaryString(time);

       // 序列数
       String randomStr = StringUtils.leftPad(Integer.toBinaryString(new Random().nextInt(max12bit)),12,'0');

       //拼接
       String appendStr = base + randomStr;
       // 转化为十进制 返回
       BigInteger bi = new BigInteger(appendStr, 2);

       return Long.valueOf(bi.toString());
   }
}
