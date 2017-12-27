package cn.goldencis.vdp.core.utils;

import com.alibaba.druid.util.StringUtils;

public class SpecialCharacherUtils {
    public static String toMyString(String oldString) {
        if (!StringUtils.isEmpty(oldString) && (oldString.contains("_") || oldString.contains("%"))) {
            String repString = "\u002a";
            return oldString.replaceAll("\u005f", repString + "\u005f").replaceAll("\u0025", repString + "\u0025");
        } else {
            return oldString;
        }
    }
}
