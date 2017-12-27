package cn.goldencis.vdp.core.utils;

public class NumberUtil {

    final static int[] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE };
    final static Integer maxSize = 8;

    public static int stringSize(int x) {
        for (int i = 0;; i++)
            if (x <= sizeTable[i])
                return i + 1;
    }

    public static String convertNumber(Integer id) {
        StringBuffer sb = new StringBuffer();
        int size = stringSize(id);
        for (int i = 0; i < maxSize - size; i++) {
            sb.append("0");
        }
        sb.append(id);
        return sb.toString();
    }
}
