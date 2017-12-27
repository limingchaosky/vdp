package cn.goldencis.vdp.common.utils;

import org.springframework.context.ApplicationContext;

/**
 * @author mll
 * 2017年5月16日上午9:25:48
 */
public class ContextUtil {
    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    public static Object getBean(String beanId) {
        Object bean = context.getBean(beanId);
        if (bean == null) {
            return null;
        }
        return bean;
    }

    public static void setContext(ApplicationContext ctx) {
        context = ctx;
    }
}
