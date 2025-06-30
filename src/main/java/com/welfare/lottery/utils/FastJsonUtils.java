package com.welfare.lottery.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.writer.ObjectWriterProvider;

import java.util.List;
import java.util.Map;

/**
 * FastJSON 2.x 工具类封装
 */
public class FastJsonUtils {

    // 配置全局默认序列化特性
    private static final JSONWriter.Context ctx;

    static {
        ObjectWriterProvider provider = new ObjectWriterProvider();
        // 可以在这里注册自定义序列化器
        ctx = new JSONWriter.Context(provider, JSONWriter.Feature.WriteMapNullValue,
                JSONWriter.Feature.WriteNullListAsEmpty,
                JSONWriter.Feature.WriteNullNumberAsZero,
                JSONWriter.Feature.WriteNullBooleanAsFalse,
                JSONWriter.Feature.WriteNullStringAsEmpty);
    }

    /**
     * 将对象转换为 JSON 字符串（包含空字段）
     *
     * @param object 要序列化的对象
     * @return JSON 字符串
     */
    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, ctx);
    }

    /**
     * 将对象转换为 JSON 字符串（不带特殊配置）
     *
     * @param object 要序列化的对象
     * @return JSON 字符串
     */
    public static String toJSONNoFeatures(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * 将 JSON 字符串解析为通用对象
     *
     * @param text JSON 字符串
     * @return 解析后的对象
     */
    public static Object toBean(String text) {
        return JSON.parse(text);
    }

    /**
     * 将 JSON 字符串解析为指定类型对象
     *
     * @param text  JSON 字符串
     * @param clazz 目标类
     * @return 解析后的对象
     */
    public static <T> T toBean(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    /**
     * 将 JSON 字符串解析为 List
     *
     * @param text  JSON 字符串
     * @param clazz 列表项类型
     * @return 解析后的列表
     */
    public static <T> List<T> toList(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }

    /**
     * 将 JSON 字符串解析为 Map
     *
     * @param text JSON 字符串
     * @return 解析后的 Map
     */
    public static Map stringToCollect(String text) {
        return JSONObject.parseObject(text);
    }

    /**
     * 将 Map 转换为 JSON 字符串
     *
     * @param map 输入 Map
     * @return JSON 字符串
     */
    public static String collectToString(Map map) {
        return JSONObject.toJSONString(map);
    }
}
