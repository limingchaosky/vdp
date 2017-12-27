/**
 *
 */
package cn.goldencis.vdp.core.utils;

import java.util.ResourceBundle;

/**
 * 读取配置文件
 * @author Administrator
 *
 */
public class PathConfig {
    private static ResourceBundle bundle;
    public final static String HOM_PATH;
    public final static String JEDIS_HOST;
    public final static Integer JEDIS_PORT;
    public final static String JEDIS_OPEN;
    public final static String DEVICE_UNIQUE_CMD; //设备唯一码查询命令
    public final static String TSA_AUTH_FILE_NAME; //TSA授权文件名
    public final static String TSA_URL; //TSA统一登录页面
    public final static String TSA_LOGIN_URL;//TSA单一登录页面
    public final static String READ_AUTH_PATH;//解密授权信息文件

    static {
        bundle = ResourceBundle.getBundle("vdp-common");

        HOM_PATH = bundle.getString("homepath");
        JEDIS_HOST = bundle.getString("jedis.host");
        JEDIS_PORT = Integer.parseInt(bundle.getString("jedis.port"));
        JEDIS_OPEN = bundle.getString("jedis.open");
        DEVICE_UNIQUE_CMD = bundle.getString("device.unique.cmd");
        TSA_AUTH_FILE_NAME = bundle.getString("tsa_auth_file_name");
        TSA_URL = bundle.getString("tsa_url");
        TSA_LOGIN_URL = bundle.getString("tsa_login_url");
        READ_AUTH_PATH = bundle.getString("readauthpath");
    }

}
