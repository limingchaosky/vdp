package cn.goldencis.vdp.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.goldencis.vdp.core.annotation.MenuLimitAnnotation;

@Controller
@RequestMapping("/homepage")
public class HomepageController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @MenuLimitAnnotation
    public ModelAndView index() {
        return new ModelAndView("/homepage/index");
    }
}
