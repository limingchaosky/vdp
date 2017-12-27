package cn.goldencis.vdp.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import cn.goldencis.vdp.common.utils.StringUtil;

/**
 * 启动服务时获取准入服务器数据
 * @author Administrator
 *
 */
@Component
public class InitAuth implements ServletContextAware {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static boolean hasInited = false;

    public void setServletContext(ServletContext sc) {
        if (!hasInited) {
            initUnique(sc);
            hasInited = true;
            logger.info("InitAuth success");
        }
    }

    public void initUnique(ServletContext sc) {
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader brStat = null;
        try {
            String[] cmd = new String[] { "sh", "-c", PathConfig.DEVICE_UNIQUE_CMD };
            Process process = Runtime.getRuntime().exec(cmd);
            is = process.getInputStream();
            isr = new InputStreamReader(is);
            brStat = new BufferedReader(isr);
            String str = null;
            //int count = 0;
            str = brStat.readLine();
            logger.info("InitAuth success {}", str);
            if (!StringUtil.isEmpty(str)) {
                sc.setAttribute("deviceUnique", str);
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (brStat != null) {
                    brStat.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //sc.setAttribute("deviceUnique", "354a65e4-1302-4a6e-b8c2-4d3b56fd59a5");
    }
}
