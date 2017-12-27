package cn.goldencis.vdp.core.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import cn.goldencis.vdp.core.entity.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import cn.goldencis.vdp.core.constants.ConstantsDto;
import cn.goldencis.vdp.core.entity.NavigationDO;
import cn.goldencis.vdp.core.service.INavigationService;
import cn.goldencis.vdp.core.utils.AuthUtils;
import cn.goldencis.vdp.core.utils.GetLoginUser;

/**
 * 左侧菜单及头部
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/system/navigation")
public class NavigationController implements ServletContextAware {

    @Autowired
    private INavigationService navigationService;

    private ServletContext servletContext;

    /**
     * 左侧菜单
     *
     * @return
     */
    @RequestMapping(value = "/usernavigation", method = RequestMethod.GET)
    public ModelAndView usernavigation(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        UserDO user = GetLoginUser.getLoginUser();

        Map<String, Object> authInfo = AuthUtils.getAuthInfo(servletContext);
        List<NavigationDO> list = new ArrayList<>();

        if (!"".equals(authInfo.get("authmsg"))) {
            //只允许system用户显示页面
            if (ConstantsDto.ADMIN_ID.equals(user.getId())) {
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
        } else {
            list = navigationService.getUserNavigation(user);
        }

        model.addObject("navigationlist", list);
        model.setViewName("decorators/navigation");
        return model;
    }

    /**
     * 系统头部的获取
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/top", method = RequestMethod.GET)
    public ModelAndView getDecoratorTop() {
        ModelAndView model = new ModelAndView();
        model.setViewName("decorators/top");
        model.addObject("userId", GetLoginUser.getLoginUser().getId());
        // model.addObject("unreadalarm",alarmLogsService.countUnread(user.getUserName()));
        return model;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

}