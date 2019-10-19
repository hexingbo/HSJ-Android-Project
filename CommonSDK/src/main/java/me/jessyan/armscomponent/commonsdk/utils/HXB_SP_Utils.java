package me.jessyan.armscomponent.commonsdk.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/8/2
 *     desc  : SP相关工具类
 * </pre>
 */
public class HXB_SP_Utils {

    private static String spName = "hxb_sp";
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;


    /**
     * SPUtils构造函数<p>在Application中初始化</p>
     *
     * @param context
     */
    public static void init(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        }
        if (editor == null) {
            editor = sp.edit();
            editor.apply();
        }
    }


    /**
     * SP中写入String类型value
     *
     * @param key   键
     * @param value 值
     */
    public static void putString(Context context, String key, String value) {
        init(context);
        editor.putString(key, value).apply();
    }

    /**
     * SP中读取String
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code null}
     */
    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }

    /**
     * SP中读取String
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public static String getString(Context context, String key, String defaultValue) {
        init(context);
        return sp.getString(key, defaultValue);
    }

    /**
     * SP中写入int类型value
     *
     * @param key   键
     * @param value 值
     */
    public static void putInt(Context context, String key, int value) {
        init(context);
        editor.putInt(key, value).apply();
    }

    /**
     * SP中读取int
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public static int getInt(Context context, String key) {
        return getInt(context, key, -1);
    }

    /**
     * SP中读取int
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public static int getInt(Context context, String key, int defaultValue) {
        init(context);
        return sp.getInt(key, defaultValue);
    }

    /**
     * SP中写入long类型value
     *
     * @param key   键
     * @param value 值
     */
    public static void putLong(Context context, String key, long value) {
        init(context);
        editor.putLong(key, value).apply();
    }

    /**
     * SP中读取long
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public static long getLong(Context context, String key) {
        init(context);
        return getLong(context, key, -1L);
    }

    /**
     * SP中读取long
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public static long getLong(Context context, String key, long defaultValue) {
        init(context);
        return sp.getLong(key, defaultValue);
    }

    /**
     * SP中写入float类型value
     *
     * @param key   键
     * @param value 值
     */
    public static void putFloat(Context context, String key, float value) {
        init(context);
        editor.putFloat(key, value).apply();
    }

    /**
     * SP中写入list类型value
     *
     * @param key  键
     * @param list 值
     */
    public static void putList(Context context,String key, List<String> list) {
        JSONArray value = new JSONArray(list);
        putString(context,key, value.toString());
    }

    /**
     * list
     *
     * @param key 键
     * @return 存在返回对应值
     */
    public static List<String> getList(Context context, String key) {
        init(context);
        String jsonstr = getString(context, key);
        if (jsonstr == null || jsonstr.isEmpty()) {
            return new ArrayList<>();
        } else {
            Gson gson = new Gson();
            return gson.fromJson(jsonstr, new TypeToken<List<String>>() {
            }.getType());
        }
    }

    /**
     * SP中读取float
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public static float getFloat(Context context, String key) {
        init(context);
        return getFloat(context, key, -1f);
    }

    /**
     * SP中读取float
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public static float getFloat(Context context, String key, float defaultValue) {
        init(context);
        return sp.getFloat(key, defaultValue);
    }

    /**
     * SP中写入boolean类型value
     *
     * @param key   键
     * @param value 值
     */
    public static void putBoolean(Context context, String key, boolean value) {
        init(context);
        editor.putBoolean(key, value).apply();
    }

    /**
     * SP中读取boolean
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code false}
     */
    public static boolean getBoolean(Context context, String key) {
        init(context);
        return getBoolean(context, key, false);
    }

    /**
     * SP中读取boolean
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        init(context);
        return sp.getBoolean(key, defaultValue);
    }

    /**
     * SP中获取所有键值对
     *
     * @return Map对象
     */
    public static Map<String, ?> getAll(Context context) {
        init(context);
        return sp.getAll();
    }

    /**
     * SP中移除该key
     *
     * @param key 键
     */
    public static void remove(Context context, String key) {
        init(context);
        editor.remove(key).apply();
    }

    /**
     * SP中是否存在该key
     *
     * @param key 键
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public static boolean contains(Context context,String key) {
        init(context);
        return sp.contains(key);
    }

    /**
     * SP中清除所有数据
     */
    public static void clear(Context context) {
        init(context);
        editor.clear().apply();
    }
}