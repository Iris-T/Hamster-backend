package cn.iris.hamster.common.utils;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.iris.hamster.common.bean.entity.BaseEntity;
import cn.iris.hamster.common.constants.CommonConstants;
import cn.iris.hamster.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Random;

import static cn.iris.hamster.common.constants.CommonConstants.DEFAULT_PAGE_SIZE;

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
    private static final int MAX_12_BIT = 4095;
    private static final long MAX_41_BIT = 1099511627775L;
    private static final Logger log =  LoggerFactory.getLogger(CommonUtils.class);

    /**
     * 随机16位ID
     * @return Long类型ID
     */
   public static Long randId() {
       long time = System.currentTimeMillis() - EPOCH  + MAX_41_BIT;
       // 二进制的 毫秒级时间戳
       String base = Long.toBinaryString(time);

       // 序列数
       String randomStr = StringUtils.leftPad(Integer.toBinaryString(new Random().nextInt(MAX_12_BIT)),12,'0');

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
        return StrUtil.isNotEmpty(status) && (CommonConstants.STATUS_ENABLE.equals(status) || CommonConstants.STATUS_DISABLE.equals(status));
    }

    /**
     * 设置继承于BaseEntity类的分页参数,未继承该类则抛出异常
     * @see BaseEntity
     * @exception cn.iris.hamster.common.exception.BaseException 提示信息
     * @param o 类
     */
    public static <T extends BaseEntity> void setPageParam(T o) {
       // instance模式匹配
       if (ObjectUtil.isEmpty(o.getCur()) || o.getCur() < 1) {
           o.setCur(1);
       }
       if (ObjectUtil.isEmpty(o.getSize())) {
           o.setSize(DEFAULT_PAGE_SIZE);
       }
    }

    public static <T> T getFieldFromObject(Object obj, String field, Class<T> target) {
       try {
           return (T) FieldUtils.readField(obj, field, true);
       } catch (IllegalAccessException e) {
           log.error("出错:实体类{}没有{}类型的{}字段",obj.getClass(), target, field);
           throw new BaseException(e);
       }
    }

    public static Boolean isExcel(InputStream in) {
        try {
            FileMagic magic = FileMagic.valueOf(in);
            if (Objects.equals(magic, FileMagic.OLE2) || Objects.equals(magic, FileMagic.OOXML)) {
                return true;
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new BaseException("异常文件类型");
        }
        return false;
    }
}
