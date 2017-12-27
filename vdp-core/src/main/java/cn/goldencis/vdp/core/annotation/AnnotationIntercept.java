package cn.goldencis.vdp.core.annotation;

import java.lang.reflect.Method;
import java.util.List;

import cn.goldencis.vdp.core.entity.UserDO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.goldencis.vdp.common.utils.StringUtil;
import cn.goldencis.vdp.core.entity.NavigationDO;
import cn.goldencis.vdp.core.service.INavigationService;
import cn.goldencis.vdp.core.utils.GetLoginUser;

@Aspect
@Component
public class AnnotationIntercept {

    @Autowired
    private INavigationService navigationService;

    @Pointcut("execution(* cn.goldencis.vdp.*.controller.*.*(..))")
    private void anyMethod() {
    }//定义一个切入点

    @Around("anyMethod() && @annotation(menuLimitAnnotation) ")
    public Object aroundMethod(ProceedingJoinPoint pjp, MenuLimitAnnotation menuLimitAnnotation) {
        Object result = null;
        try {
            result = pjp.proceed();
            //判断是否有权限查看该页面
            if (result != null && result instanceof ModelAndView) {
                String url = !StringUtil.isEmpty(menuLimitAnnotation.url()) ? menuLimitAnnotation.url() : queryUrl(pjp);
                UserDO user = GetLoginUser.getLoginUser();
                List<NavigationDO> list = navigationService.getUserNavigation(user);

                boolean flag = false;
                if (list != null) {
                    for (NavigationDO nd : list) {
                        if (!StringUtil.isEmpty(nd.getUrl()) && nd.getUrl().startsWith(url)) {
                            flag = true;
                            break;
                        }
                    }
                }

                if (!flag) {
                    //没有权限登录一个新页面
                    ModelAndView limit = new ModelAndView("/noPermission/index");
                    return limit;
                }
            }
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    /**
     * 查询方法mapping地址
     * @param pjp
     * @return
     */
    private String queryUrl(ProceedingJoinPoint pjp) {
        String url = "";
        try {
            String methodName = pjp.getSignature().getName();
            Class<?> classTarget = pjp.getTarget().getClass();
            Class<?>[] par = ((MethodSignature) pjp.getSignature()).getParameterTypes();
            Method objMethod = classTarget.getMethod(methodName, par);
            RequestMapping methodUrl = objMethod.getAnnotation(RequestMapping.class);
            RequestMapping classUrl = classTarget.getAnnotation(RequestMapping.class);

            if (classUrl != null) {
                url += classUrl.value() != null ? coverteListToString(classUrl.value()) : "";
            }
            if (methodUrl != null) {
                url += methodUrl.path() != null ? coverteListToString(methodUrl.value()) : "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return url;
    }

    private String coverteListToString(String[] str) {
        if (str == null || str.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            sb.append(str[i]);
        }
        return sb.toString();
    }
}
