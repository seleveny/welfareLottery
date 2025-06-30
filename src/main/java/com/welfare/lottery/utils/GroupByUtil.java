package com.welfare.lottery.utils;

import cn.hutool.core.collection.CollectionUtil;

import java.util.*;
import java.util.function.Function;

/**
 * @author xuchengcheng
 * @since 2025/6/24
 */
public class GroupByUtil {

    /**
     * 手动实现 groupBy 功能
     *
     * @param list      原始数据列表
     * @param keyMapper 分组字段提取函数
     * @param <T>       数据类型
     * @param <K>       分组键类型
     * @return Map<K, List < T>>
     */
    public static <T, K> Map<K, List<T>> groupBy(Collection<T> list, Function<T, K> keyMapper) {
        Map<K, List<T>> resultMap = new HashMap<>();

        if (CollectionUtil.isEmpty(list)) {
            return resultMap;
        }

        for (T item : list) {
            K key = keyMapper.apply(item);
            resultMap.computeIfAbsent(key, k -> new ArrayList<>()).add(item);
        }

        return resultMap;
    }
}
