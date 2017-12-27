package cn.goldencis.vdp.core.utils;

import org.aspectj.lang.annotation.Aspect;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 系统日志，记录代码运行相关信息
 *
 * @author Administrator
 */
@Aspect
@Component
@Order(0)
public class AuthorizedUtil {

    /*//Controller层切点
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllerAspect() {
    }*/

    /**
     * controller执行完毕后输出执行日志
     * 按要求不强制退出系统 屏蔽磁珠
     * @param point
     * @param result
     */
    /*@Before("controllerAspect()")
    public void logBeforeExecution() {

        if ("1".equals(SysContext.getSession().getAttribute("unauthority"))) {
            Date logintime = (Date) SysContext.getSession().getAttribute("logintime");
            Date nowTime = new Date();
            if ((nowTime.getTime() - logintime.getTime()) > ConstantsDto.LIMIT_TIME) {
                SysContext.getSession().removeAttribute("unauthority");
                SysContext.getSession().removeAttribute("logintime");
                HttpServletRequest request = SysContext.getRequest();
                HttpServletResponse response = SysContext.getResponse();
                //判断是否存在tsa授权文件
                if (!AuthUtils.checkFileExsits(PathConfig.HOM_PATH, PathConfig.TSA_AUTH_FILE_NAME)) {
                    String path = request.getContextPath();
                    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
                            + request.getServerPort() + path + "/";
                    try {
                        response.sendRedirect(basePath + "logout");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        response.sendRedirect(PathConfig.TSA_URL);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }*/

}
