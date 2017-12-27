package cn.goldencis.vdp.core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.goldencis.vdp.common.utils.StringUtil;
import cn.goldencis.vdp.core.constants.ConstantsDto;
import cn.goldencis.vdp.core.entity.PermissionDO;
import cn.goldencis.vdp.core.service.INavigationService;
import cn.goldencis.vdp.core.service.IPermissionService;
import cn.goldencis.vdp.core.service.IUserService;

import com.alibaba.fastjson.JSONArray;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private INavigationService navigationService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/user/permission/index");
        mv.addObject("zNodes", navigationService.getNavigation());
        return mv;
    }

    @RequestMapping(value = "/addOrUpdatePermission", method = RequestMethod.POST)
    @ResponseBody
    public String addOrUpdatePermission(PermissionDO record) {
        permissionService.addOrUpdatePermission(record);
        return "success";
    }

    @RequestMapping(value = "/deletePermission", method = RequestMethod.POST)
    @ResponseBody
    public String deletePermission(String id) {
        //Long i = userService.selectUserCountByPermission(id);
        /* if (i != 0) {
             return "hasUser";
         }*/
        if (id.equals(ConstantsDto.DEFAULT_PERMISSION)) {
            return "failed";
        }
        permissionService.deletePermission(id);
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/getPermission", produces = "application/json", method = RequestMethod.GET)
    public PermissionDO getPermission(String id) {
        return permissionService.getPermission(id);
    }

    @ResponseBody
    @RequestMapping(value = "/getPermissionList", produces = "application/json", method = RequestMethod.GET)
    public List<PermissionDO> getPermissionList() {
        return permissionService.getPermissionListNoTable();
    }

    @ResponseBody
    @RequestMapping(value = "/datalist", produces = "application/json", method = RequestMethod.GET)
    public Map<String, Object> datalist(Integer length, Integer start, String searchstr, HttpServletRequest request,
            HttpServletResponse response) {
        Map<String, Object> model = new HashMap<>();

        long count = permissionService.countPermission(null);
        List<PermissionDO> list = permissionService.getPermissionList(start, length, searchstr);

        model.put("recordsTotal", count);
        model.put("recordsFiltered", count);
        model.put("data", toUsersJson(list));
        model.put("exportlength", length);
        model.put("exportstart", start);
        return model;
    }

    private JSONArray toUsersJson(List<PermissionDO> userlist) {
        JSONArray array = new JSONArray();
        for (PermissionDO per : userlist) {
            JSONArray array1 = new JSONArray();
            array1.add(per.getId());
            array1.add("<span class='user'>" + per.getId() + "</span>");
            if (StringUtil.isEmpty(per.getName())) {
                array1.add("<span class='user'>--</span>");
            } else {
                array1.add("<span class='user'>" + per.getName() + "</span>");
            }
            if (StringUtil.isEmpty(per.getNav())) {
                array1.add("<span class='user'>--</span>");
            } else {
                array1.add("<span class='user'>" + per.getNav() + "</span>");
            }
            if (StringUtil.isEmpty(per.getUser())) {
                array1.add("<span class='user'>--</span>");
            } else {
                array1.add("<span class='user'>" + per.getUser() + "</span>");
            }
            if (StringUtil.isEmpty(per.getRemark())) {
                array1.add("<span class='user'>--</span>");
            } else {
                array1.add("<span class='user'>" + per.getRemark() + "</span>");
            }
            array1.add(per.getId());
            array.add(array1);
        }
        return array;
    }
}
