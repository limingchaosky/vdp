package cn.goldencis.vdp.core.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DateSecret {

    private static String keyCode = "12345678";
    private static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };

    public static String encryptDES(String encryptString) throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(keyCode.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes("utf-8"));
        return Base64.encode(encryptedData);
    }

    public static String decryptDES(String decryptString) throws Exception {
        byte[] byteMi = Base64.decode(decryptString);
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(keyCode.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte decryptedData[] = cipher.doFinal(byteMi);

        return new String(decryptedData, "utf-8");
    }

    /**
         * @param buf
         * @return  String
    */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String testString = "{\"company\":\"济南市公安局\",\"beginDate\":\"2017-05-16\",\"endDate\":\"3000-01-01\",\"supportDate\":\"2018-05-16\",\"issueDate\":\"2017-05-15\",\"deviceUnique\":\"567960b9-1eb3-4658-a3bd-93749da1d540\",\"projectIdentification\": \"OMS\"}";
        String strOut = "";
        String strDecode = "";
        try {
            strOut = DateSecret.encryptDES(testString);//加密
            strDecode = DateSecret.decryptDES(strOut);//解密
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(strOut);
        System.out.println(strDecode);
    }
}