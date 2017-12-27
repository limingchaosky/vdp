package cn.neiwang.omsjni;

import cn.goldencis.vdp.core.utils.PathConfig;

/**
 * 网络配置jni
 * @author Administrator
 *
 */
public class IFConfig {
    static {
        System.load(PathConfig.HOM_PATH + "/tomcat/bin/libomsjni.so");
    }

    public static native String getIFConfig();

    public static native boolean setIFConfig(String config);
}
