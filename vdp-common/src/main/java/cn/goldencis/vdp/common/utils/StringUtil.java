package cn.goldencis.vdp.common.utils;

import java.util.List;

/**
 *
 * String工具类. <br>
 *

 */
public class StringUtil {

    /**
     * 功能：检查这个字符串是不是空字符串。<br/>
     * 如果这个字符串为null或者trim后为空字符串则返回true，否则返回false。
     *
     * @param chkStr
     *            被检查的字符串
     * @return boolean
     */
    public static boolean isEmpty(String chkStr) {
        if (chkStr == null) {
            return true;
        } else {
            return "".equals(chkStr.trim()) ? true : false;
        }
    }

    /**
     * 如果字符串没有超过最长显示长度返回原字符串，否则从开头截取指定长度并加...返回。
     *
     * @param str
     *            原字符串
     * @param length
     *            字符串最长显示的长度
     * @return 转换后的字符串
     */
    public static String trimString(String str, int length) {
        if (str == null) {
            return "";
        } else if (str.length() > length) {
            return str.substring(0, length - 3) + "...";
        } else {
            return str;
        }
    }

    /**
     * 根据绝对路径返回最后文件名称
     * @author mll
     * @param path
     * @return String
     */
    public static String subLastPath(String path) {
        int index = path.lastIndexOf("\\");
        char[] ch = path.toCharArray();
        String lastString = String.copyValueOf(ch, index + 1, ch.length - index - 1);
        return lastString;
    }

    /**
     * 将数组转化成字符
     * @param list
     * @param separator
     * @return
     */
    public static String listToString(List<String> list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

}