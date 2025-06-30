package com.welfare.lottery.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.welfare.lottery.response.PageResult;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xuchengcheng
 * 分页封装转换插件
 * @since 2025/6/20
 */
public class PageUtils {

    public static <T> PageResult<T> convert(Page<T> page) {
        return convert(page, Function.identity());
    }

    /**
     * 分页转换
     *
     * @param page
     * @param converter
     * @param <F>       来源
     * @param <T>       目标
     * @return
     */
    public static <F, T> PageResult<T> convert(Page<F> page, Function<F, T> converter) {
        if (page == null) {
            return null;
        }
        PageResult<T> result = new PageResult<>();
        result.setCurrent(page.getCurrent());
        result.setSize(page.getSize());
        result.setTotal(page.getTotal());

        if (CollectionUtils.isNotEmpty(page.getRecords())) {
            // 使用传入的转换方法进行映射
            List<T> records = page.getRecords().stream()
                    .map(converter)
                    .collect(Collectors.toList());
            result.setRecords(records);
        }
        return result;
    }
}
