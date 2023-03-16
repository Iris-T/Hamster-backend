package cn.iris.hamster.common.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.iris.hamster.common.bean.entity.BaseEntity;
import cn.iris.hamster.common.exception.BaseException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 集合转换工具类
 *
 * @author Iris
 * @ClassName ListUtils
 * @date 2023/3/1 11:14
 */
public class ListUtils {
    private static final Logger logger = LoggerFactory.getLogger(ListUtils.class);

    /**
     * 把List 转为Map
     * 类似 {@link java.util.stream.Collectors#toMap(Function, Function)}
     *
     * @param collection  list 数据
     * @param keyMapper   用于生成Key的映射函数
     * @param valueMapper 用于生成Value的映射函数
     * @param <K>         Map key值类型
     * @param <V>         Map value 值类型
     * @param <T>         List 数据类型
     * @return 返回处理后的map
     * @author malei
     * @date 2018/10/29
     */
    public static <K, V, T> Map<K, V> listToMap(Collection<T> collection,
                                                Function<? super T, ? extends K> keyMapper,
                                                Function<? super T, ? extends V> valueMapper) {
        Map<K, V> map = Maps.newHashMap();
        if (CollectionUtils.isEmpty(collection)) {
            return map;
        }
        for (T t : collection) {
            if (t == null) {
                continue;
            }
            map.put(keyMapper.apply(t), valueMapper.apply(t));
        }
        return map;
    }


    /**
     * 把List 转为Map
     * 类似 {@link java.util.stream.Collectors#toMap(Function, Function)}
     *
     * @param collection list 数据
     * @param keyMapper  用于生成Key的映射函数
     * @param <K>        Map key值类型
     * @param <T>        List 数据类型
     * @return 返回处理后的map
     * @author malei
     * @date 2018/10/29
     */
    public static <K, T> Map<K, T> listToMap(Collection<T> collection,
                                             Function<? super T, K> keyMapper) {
        Map<K, T> map = Maps.newHashMap();
        for (T t : collection) {
            map.put(keyMapper.apply(t), t);
        }
        return map;
    }


    /**
     * 把List 转为Map
     * 类似 {@link java.util.stream.Collectors#toMap(Function, Function)}
     *
     * @param collection list 数据
     * @param keyMapper  用于生成Key的映射函数
     * @param <K>        Map key值类型
     * @param <T>        List 数据类型
     * @return 返回处理后的map
     * @author malei
     * @date 2018/10/29
     */
    public static <K, T> List<K> listToKeys(Collection<T> collection,
                                            Function<? super T, ? extends K> keyMapper) {
        List<K> list = Lists.newArrayList();

        for (T t : collection) {
            list.add(keyMapper.apply(t));
        }
        return list;
    }


    /**
     * 把List 转为Map
     * 类似 {@link java.util.stream.Collectors#toMap(Function, Function)}
     *
     * @param collection  list 数据
     * @param keyMapper   用于生成Key的映射函数
     * @param valueMapper 用于生成Value的映射函数
     * @param <K>         Map key值类型
     * @param <V>         Map value 值类型
     * @param <T>         List 数据类型
     * @return 返回处理后的map
     * @author malei
     * @date 2018/10/29
     */
    public static <K, V, T> Map<K, List<V>> groupBy(Collection<T> collection,
                                                    Function<? super T, ? extends K> keyMapper,
                                                    Function<? super T, ? extends V> valueMapper) {
        Map<K, List<V>> map = Maps.newLinkedHashMap();
        for (T t : collection) {
            map.computeIfAbsent(keyMapper.apply(t),
                    key -> Lists.newArrayList()).add(valueMapper.apply(t));
        }
        return map;
    }

    /**
     * 把List 转为Map
     * 类似 {@link java.util.stream.Collectors#toMap(Function, Function)}
     *
     * @param collection list 数据
     * @param keyMapper  用于生成Key的映射函数
     * @param <K>        Map key值类型
     * @param <T>        List 数据类型
     * @return 返回处理后的map
     * @author malei
     * @date 2018/10/29
     */
    public static <K, T> Map<K, List<T>> groupBy(Collection<T> collection,
                                                 Function<? super T, ? extends K> keyMapper) {
        Map<K, List<T>> map = Maps.newLinkedHashMap();
        for (T t : collection) {
            map.computeIfAbsent(keyMapper.apply(t),
                    key -> Lists.newArrayList()).add(t);
        }
        return map;
    }

    /**
     * 把多个list组合成一个新的list
     *
     * @param list 集合
     * @param <T>
     * @return 组合成的list
     */
    @SafeVarargs
    public static <T> List<T> add(List<T>... list) {
        List<T> result = Lists.newArrayList();

        for (List<T> item : list) {
            if (item != null) {
                result.addAll(item);
            }
        }
        return result;
    }

    public static <K, T extends BaseEntity> List<T> listToAnotherList(Collection<K> source, Class<T> target) {
        List<T> res = Lists.newArrayList();
        source.forEach(s -> {
            try {
                T t = target.newInstance();
                BeanUtil.copyProperties(s, t);
                res.add(t);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new BaseException("数据转换失败", e);
            }
        });
        return res;
    }
}
