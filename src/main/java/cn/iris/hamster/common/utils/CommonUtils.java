package cn.iris.hamster.common.utils;

import cn.hutool.core.util.StrUtil;
import cn.iris.hamster.common.constants.CommonConstants;
import cn.iris.hamster.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.math.BigInteger;
import java.util.Random;

/**
 * 公共工具类
 *
 * @author Iris
 * @ClassName CommonUtils
 * @date 2022/12/30 16:52
 */

@Slf4j
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

   public static String checkStatus(String status) {
       if (CommonConstants.STATUS_ENABLE.equals(status) || CommonConstants.STATUS_DISABLE.equals(status)) {
           return status;
       }
       return CommonConstants.STATUS_ENABLE;
   }

    public static boolean isRightStatus(String status) {
        return StrUtil.isNotEmpty(status) && (
                CommonConstants.STATUS_ENABLE.equals(status)
                        || CommonConstants.STATUS_DISABLE.equals(status));
    }

    public static <T> T getFieldFromObject(Object obj, String field, Class<T> target) {
       try {
           return (T) FieldUtils.readField(obj, field, true);
       } catch (IllegalAccessException e) {
           log.error("出错:实体类{}没有{}类型的{}字段",obj.getClass(), target, field);
           throw new BaseException(e);
       }
    }
}
