package cn.goldencis.vdp.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.goldencis.vdp.core.entity.GroupDO;
import cn.goldencis.vdp.core.service.IGroupService;

@Controller
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private IGroupService groupService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("user/group/index");
        return mv;
    }

    @RequestMapping(value = "/getGroupList", method = RequestMethod.GET)
    @ResponseBody
    public List<GroupDO> getGroupList(String type) {
        return groupService.selectList(type);
    }

    @RequestMapping(value = "/getGroupById", method = RequestMethod.GET)
    @ResponseBody
    public GroupDO getGroupById(String id) {
        return groupService.getGroupById(id);
    }

    @RequestMapping(value = "/addOrUpdateGroup", method = RequestMethod.POST)
    @ResponseBody
    public String addOrUpdateGroup(GroupDO group) {
        if (!groupService.selectByGroupName(group.getName())) {
            return "exist";
        }
        if (!groupService.addOrUpdateGroup(group)) {
            return "failed";
        }
        return "success";
    }

    @RequestMapping(value = "/addUserByGroup", method = RequestMethod.POST)
    @ResponseBody
    public String addUserByGroup(GroupDO group) {
        if (!groupService.addUserByGroup(group)) {
            return "failed";
        }
        return "success";
    }

    @RequestMapping(value = "/deleteGroupById", method = RequestMethod.POST)
    @ResponseBody
    public String deleteGroupById(String id) {
        groupService.deleteGroupById(id);
        return "success";
    }

    @RequestMapping(value = "/deleteUserByGroup", method = RequestMethod.POST)
    @ResponseBody
    public String deleteUserByGroup(GroupDO group) {
        if (groupService.deleteUserByGroup(group)) {
            return "success";
        }
        return "failed";
    }
}
