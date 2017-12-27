package cn.goldencis.vdp.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/blank")
public class BlankController {

    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("/blank/index");
    }
}
