package cn.goldencis.vdp.common.utils;

import org.apache.log4j.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密算法工具类
 */
public class SHACoderUtil {
    private static Logger log = Logger.getLogger(SHACoderUtil.class);

    // 加密
    @SuppressWarnings("restriction")
    public static String getBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    // 解密
    @SuppressWarnings("restriction")
    public static String getFromBase64(String s) {

        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                byte[] b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * md5消息摘要算法
     * @throws NoSuchAlgorithmException
     */
    public static String encodeMd5(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("md5");
        byte[] digest = md.digest(data);
        return new HexBinaryAdapter().marshal(digest);
    }

    /**
     * SHA-1消息摘要算法
     * @throws NoSuchAlgorithmException
     */
    public static String encodeSHA(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA");
        byte[] digest = md.digest(data);
        return new HexBinaryAdapter().marshal(digest);
    }

    /**
     * SHA-256消息摘要算法
     * @throws NoSuchAlgorithmException
     */
    public static String encodeSHA256(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(data);
        return new HexBinaryAdapter().marshal(digest);
    }

    /**
     * SHA-256消息摘要算法加盐
     * @throws NoSuchAlgorithmException
     */
    public static String encodeSHA256withSalt(String submitPass, Object salt) throws NoSuchAlgorithmException {
        String addSalt = submitPass + "{" + salt + "}";
        log.debug("encodeSHA256withSalt addSalt:" + addSalt);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(addSalt.getBytes());
        return new HexBinaryAdapter().marshal(digest);
    }

    /**
     * SHA-384消息摘要算法
     * @throws NoSuchAlgorithmException
     */
    public static String encodeSHA384(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-384");
        byte[] digest = md.digest(data);
        return new HexBinaryAdapter().marshal(digest);
    }

    /**
     * SHA-512消息摘要算法
     * @throws NoSuchAlgorithmException
     */
    public static String encodeSHA512(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] digest = md.digest(data);
        return new HexBinaryAdapter().marshal(digest);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String testString = "123456";
        log.debug(SHACoderUtil.encodeSHA256withSalt(testString, "0000000020"));
        log.debug(SHACoderUtil.encodeMd5(testString.getBytes()));
        log.debug(SHACoderUtil.encodeSHA(testString.getBytes()));
        log.debug(SHACoderUtil.encodeSHA256(testString.getBytes()));
        log.debug(SHACoderUtil.encodeSHA384(testString.getBytes()));
        log.debug(SHACoderUtil.encodeSHA512(testString.getBytes()));
    }
}
