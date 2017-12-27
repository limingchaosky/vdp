package cn.goldencis.vdp.core.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import sun.misc.BASE64Decoder;

@SuppressWarnings("restriction")
public class Base64 {
    private static final char[] legalChars = "ghijk67stuJKLM89rvwxyzAlmnOUV+/abcdefPQRSTopqCDEFG012345BHINWXYZ"
            .toCharArray();
    private static HashMap<Character, Integer> hashDecode = new HashMap<Character, Integer>();

    /**
     * 字符串转Base64编码
     * @param s
     * @return
     */
    public static String getBASE64(String s) {
        if (s == null)
            return null;
        return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
    }

    /**
     * 将 字符数组 进行 BASE64 编码
     * @param s
     * @return
     */
    public static String getBASE64Byte(byte[] s) {
        if (s == null)
            return null;
        return (new sun.misc.BASE64Encoder()).encode(s);
    }

    // 将 BASE64 编码的字符串 s 进行解码
    public static String getFromBASE64(String s) {
        if (s == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b);
        } catch (Exception e) {
            return null;
        }
    }

    public static String encode(byte[] data) {
        int start = 0;
        int len = data.length;
        StringBuffer buf = new StringBuffer(data.length * 3 / 2);

        int end = len - 3;
        int i = start;
        int n = 0;

        while (i <= end) {
            int d = ((((int) data[i]) & 0x0ff) << 16) | ((((int) data[i + 1]) & 0x0ff) << 8)
                    | (((int) data[i + 2]) & 0x0ff);

            buf.append(legalChars[(d >> 18) & 63]);
            buf.append(legalChars[(d >> 12) & 63]);
            buf.append(legalChars[(d >> 6) & 63]);
            buf.append(legalChars[d & 63]);

            i += 3;

            if (n++ >= 14) {
                n = 0;
                buf.append(" ");
            }
        }

        if (i == start + len - 2) {
            int d = ((((int) data[i]) & 0x0ff) << 16) | ((((int) data[i + 1]) & 255) << 8);

            buf.append(legalChars[(d >> 18) & 63]);
            buf.append(legalChars[(d >> 12) & 63]);
            buf.append(legalChars[(d >> 6) & 63]);
            buf.append("=");
        } else if (i == start + len - 1) {
            int d = (((int) data[i]) & 0x0ff) << 16;

            buf.append(legalChars[(d >> 18) & 63]);
            buf.append(legalChars[(d >> 12) & 63]);
            buf.append("==");
        }

        return buf.toString();
    }

    public static byte[] decode(String s) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] decodedBytes = null;
        try {
            decode(s, bos);
            decodedBytes = bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }
        return decodedBytes;
    }

    private static void decode(String s, OutputStream os) throws IOException {
        int i = 0;

        int len = s.length();

        while (true) {
            while (i < len && s.charAt(i) <= ' ')
                i++;

            if (i == len)
                break;

            int tri = (decode(s.charAt(i)) << 18) + (decode(s.charAt(i + 1)) << 12) + (decode(s.charAt(i + 2)) << 6)
                    + (decode(s.charAt(i + 3)));

            os.write((tri >> 16) & 255);
            if (s.charAt(i + 2) == '=')
                break;
            os.write((tri >> 8) & 255);
            if (s.charAt(i + 3) == '=')
                break;
            os.write(tri & 255);

            i += 4;
        }
    }

    private static int decode(char c) {

        if (hashDecode.size() == 0) {
            for (int i = 0; i < 64; i++) {
                char ch = legalChars[i];
                hashDecode.put(ch, i);
            }
        }
        if (hashDecode.containsKey(c)) {
            return hashDecode.get(c);
        } else if (c == '=')
            return 0;
        else
            throw new RuntimeException("unexpected code: " + c);
    }
}