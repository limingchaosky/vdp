package cn.goldencis.vdp.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/noPermission")
public class NoPermissionController {

    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("/noPermission/index");
    }
}
