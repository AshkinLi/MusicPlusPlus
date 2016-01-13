package com.ashkin.musicplusplus.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * String Util
 * 字符串工具类
 */
public class StringUtil {

    /**
     * 将毫秒数转化了格式化时间
     *
     * @param duration 毫秒数
     * @return 格式化时间
     */
    public static String durationToData(int duration) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("mm:ss");

        return mSimpleDateFormat.format(new Date(duration));
    }
}
