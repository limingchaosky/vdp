package cn.goldencis.vdp.core.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.goldencis.vdp.core.entity.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cn.goldencis.vdp.common.utils.StringUtil;
import cn.goldencis.vdp.core.constants.ConstantsDto;
import cn.goldencis.vdp.core.entity.NavigationDO;
import cn.goldencis.vdp.core.service.INavigationService;
import cn.goldencis.vdp.core.service.IUserService;
import cn.goldencis.vdp.core.utils.AuthUtils;
import cn.goldencis.vdp.core.utils.GetLoginUser;
import cn.goldencis.vdp.core.utils.PathConfig;

/**
 * 登录
 * @author Administrator
 *
 */
@Controller
public class LoginController implements ServletContextAware {

    private ModelAndView model = null;
    @Autowired
    private INavigationService navigationService;
    @Autowired
    private IUserService userService;

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error, @RequestParam(
            value = "logout", required = false) String logout, HttpServletRequest request, HttpServletResponse response) {
        //tsa授权文件存在则跳转到tsa页面   这里只判断文件是否存在
        if (logout != null && AuthUtils.checkFileExsits(PathConfig.HOM_PATH, PathConfig.TSA_AUTH_FILE_NAME)) {
            model = new ModelAndView(new RedirectView(PathConfig.TSA_URL));
            return model;
        }
        model = new ModelAndView();
        response.setHeader("isRedirect", "yes");
        model.setViewName("../../index");
        return model;
    }

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/loginsuccess", method = RequestMethod.GET)
    public void loginsuccess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        /* String realPath1 = "http://" + request.getServerName() + ":" + request.getServerPort()
                 + request.getContextPath();
        */
        //获取当前登录用户
        UserDO user = GetLoginUser.getLoginUser();
        //获取当前登录用户对应权限的对应导航栏
        List<NavigationDO> list = navigationService.getUserNavigation(user);

        //获取授权文件的信息
        Map<String, Object> authInfo = AuthUtils.getAuthInfo(servletContext);

        if (authInfo.get("promptMsg") != null && !"".equals(authInfo.get("promptMsg"))
                && userService.queryRefusePromptUser(user.getId()) == 0) {
            //request.getSession().setAttribute("promptMsg", authInfo.get("promptMsg"));
            Cookie cookie = new Cookie("promptMsg", URLEncoder.encode(URLEncoder.encode(authInfo.get("promptMsg")
                    .toString(), "utf-8")));
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        request.getSession().setAttribute("maxCustomerCnt",
                authInfo.get("maxCustomerCnt") == null ? "0" : authInfo.get("maxCustomerCnt").toString());

        if (!"".equals(authInfo.get("authmsg"))) {
            request.getSession().setAttribute("unauthority", "1");
            request.getSession().setAttribute("logintime", new Date());
            request.getSession().setAttribute("authmsg", authInfo.get("authmsg"));

            /*//时间限制 客户增加用户数
            request.getSession().setAttribute("maxCustomerCnt", "0");*/

            //超期或者有其他问题则不让普通用户登录
            if (!ConstantsDto.ADMIN_ID.equals(user.getId())) {
                redirectStrategy.sendRedirect(request, response, "/login?logout");
                return;
            } else {
                //如果没有授权 或者 过期 只让system登录关于页面
                NavigationDO navigationDO = new NavigationDO();
                navigationDO.setIconUrl("nav_about");
                navigationDO.setTitle("关于");
                navigationDO.setCompositor(7);
                navigationDO.setUrl("/about/index");
                navigationDO.setnLevel(1);
                navigationDO.setId("7");
                list = new ArrayList<NavigationDO>();
                list.add(navigationDO);
            }
        }/* else {
            Long userCount = userService.countUserList(null, null, null, null);
            if (userCount > Long.valueOf(authInfo.get("maxCustomerCnt") == null ? "0" : authInfo.get("maxCustomerCnt")
                    .toString())) {
                request.getSession().setAttribute("authmsg", "已超出最大用户数");
            }
         }*/

        if (AuthUtils.checkFileExsits(PathConfig.HOM_PATH, PathConfig.TSA_AUTH_FILE_NAME)) {
            request.getSession().setAttribute("tsaMenu", "1");
            request.getSession().setAttribute("tsaLoginUrl", PathConfig.TSA_LOGIN_URL);
        } else {
            request.getSession().setAttribute("tsaMenu", "0");
        }

        String url = "";
        //登录第一个url
        if (list != null && list.size() > 0) {
            for (NavigationDO nd : list) {
                if (nd.getnLevel() == 1 && !StringUtil.isEmpty(nd.getUrl())) {
                    url = nd.getUrl();
                } else if (nd.getnLevel() == 1) {
                    for (NavigationDO nd1 : list) {
                        if (!StringUtil.isEmpty(nd.getId()) && Integer.valueOf(nd.getId()) == nd1.getParentId()) {
                            url = nd1.getUrl();
                            break;
                        }
                    }
                }
                if (!StringUtil.isEmpty(url)) {
                    break;
                }
            }
        }

        //空白页面
        if (StringUtil.isEmpty(url)) {
            url = "/blank/index";
        }

        redirectStrategy.sendRedirect(request, response, url);
    }

}