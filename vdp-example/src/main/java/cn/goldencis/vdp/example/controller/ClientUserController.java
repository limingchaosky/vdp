package cn.goldencis.vdp.example.controller;

import cn.goldencis.vdp.example.entity.ClientUser;
import cn.goldencis.vdp.example.entity.ClientUserCriteria;
import cn.goldencis.vdp.example.service.IClientUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * Created by limingchao on 2017/12/22.
 */
@Controller
@RequestMapping(value = "/clientUser")
public class ClientUserController implements ServletContextAware {

    @Autowired
    private IClientUserService clientUserService;

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @RequestMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/clientUser/index");

        ClientUser clientUser = clientUserService.getByPrimaryKey(String.valueOf(1));
        System.out.println(clientUser);

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "getAllClientUsers")
    public List<ClientUser> getAllClientUsers(int start, int length) {
        ClientUserCriteria example = new ClientUserCriteria();
        List<ClientUser> clientUsers = clientUserService.listPage(start, length, example);

        return clientUsers;
    }

}
