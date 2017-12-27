package cn.goldencis.vdp.core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.goldencis.vdp.core.entity.UserDO;
import cn.goldencis.vdp.core.entity.UserDOCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.goldencis.vdp.common.utils.StringUtil;
import cn.goldencis.vdp.core.constants.ConstantsDto;
import cn.goldencis.vdp.core.entity.DepartmentDO;
import cn.goldencis.vdp.core.entity.DepartmentDOCriteria;
import cn.goldencis.vdp.core.service.IDepartmentService;
import cn.goldencis.vdp.core.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 部门管理controller
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IUserService userService;

    /**
     * 部门管理主页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
//        String zNodes = departmentService.getManagerNodes();
//        model.addObject("zNodes", zNodes);
        model.setViewName("user/department/index");
        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/getDepartmentById", produces = "application/json", method = RequestMethod.GET)
    public DepartmentDO getDepartmentById(String id) {
        return departmentService.getDepartmentById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/getDepartmentTreelist")
    public String getDepartmentTreelist() {
        String zNodes = departmentService.getManagerNodes();
        return zNodes;
    }

    /**
     * 下级部门列表
     * @param id
     * @param draw
     * @param length
     * @param start
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/datalist", produces = "application/json", method = RequestMethod.GET)
    public Map<String, Object> datalist(@RequestParam("id") Integer id, @RequestParam("draw") String draw,
                                        @RequestParam("length") int length, @RequestParam("start") int start, HttpServletRequest request, HttpServletResponse response) {
        //如果开始小于1默认为1
        Map<String, Object> model = new HashMap<>();

        String orderby = request.getParameter("order[0][dir]");
        String orderType = request.getParameter("order[0][column]");
        if (orderType != null) {
            if ("0".equals(orderType)) {
                orderType = "d.name";
            } else if ("2".equals(orderType)) {
                orderType = "d.ip_part";
            } else {
                orderType = "d.id";
            }
        } else {
            orderType = "d.id";
        }

        String orderCase = orderType + " " + orderby;
        DepartmentDO pbean = departmentService.getByPrimaryKey(String.valueOf(id));
        String treePath = pbean.getTreePath() + pbean.getId() + ",%";
        long count = departmentService.getDeptarMentCount(id, treePath);
        List<DepartmentDO> departmentU = departmentService.getDeptarMentList(start, length, id, treePath, orderCase);

        model.put("draw", draw);
        model.put("recordsTotal", count);
        model.put("recordsFiltered", count);
        model.put("data", departmentU);

        model.put("exportlength", length);
        model.put("exportstart", start);
        model.put("exportorder", orderCase);
        return model;

    }


    /**
     * 保存接口
     *
     * @param bean
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", produces = "application/json", method = RequestMethod.POST)
    public String add(@ModelAttribute DepartmentDO bean) {
        DepartmentDO pdept = departmentService.getByPrimaryKey(String.valueOf(bean.getParentId()));
        if (StringUtil.isEmpty(pdept.getTreePath())) {
            pdept.setTreePath(",");
        }
        bean.setTreePath(pdept.getTreePath() + pdept.getId() + ",");
        bean.setStatus(1);
        departmentService.create(bean);
        return "success";
    }

    /**
     * 修改接口
     *
     * @param bean
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateDept", produces = "application/json", method = RequestMethod.POST)
    public String updateDept(@ModelAttribute DepartmentDO bean) {
        departmentService.updatedept(bean);
        return "success";
    }

    /**
     * 删除验证接口
     *
     * @param ip
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deletecheck", produces = "application/json", method = RequestMethod.GET)
    public String deletecheck(@RequestParam(value = "id") Integer id) {
        DepartmentDOCriteria example = new DepartmentDOCriteria();
        example.createCriteria().andParentIdEqualTo(id);
        List<DepartmentDO> clist = departmentService.listBy(example);
        if (clist != null && !clist.isEmpty()) {
            return "parentLevel";
        }
        UserDOCriteria uexample = new UserDOCriteria();
        uexample.createCriteria().andDepartmentEqualTo(id);
        List<UserDO> ulist = userService.listBy(uexample);
        if (ulist != null && !ulist.isEmpty()) {
            return "hasUser";
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteUserByDepartmentId", produces = "application/json", method = RequestMethod.POST)
    public String deleteUserByDepartmentId(String departmentId, String userId) {
        userService.deleteUserByDepartmentId(departmentId, userId);
        return "success";
    }

    /**
     * 删除接口
     *
     * @param ip
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", produces = "application/json", method = RequestMethod.POST)
    public String delete(@RequestParam(value = "id") Integer id) {
        if (id == null || id == 1 || id == Integer.parseInt(ConstantsDto.DEPARTMENT_UNKOWN_GROUP)) {
            return "failed";
        }
        departmentService.deleteById(id);
        return "success";
    }

    /**
     * 名称校验
     *
     * @param name
     * @param oldName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/namecheck", produces = "application/json", method = RequestMethod.GET)
    public boolean ipcheck(@RequestParam(value = "name") String name, String oldName) {
        DepartmentDOCriteria example = new DepartmentDOCriteria();
        example.createCriteria().andNameEqualTo(name.trim());
        DepartmentDO bean = departmentService.getBy(example);
        // 添加校验
        if (bean != null) {
            // 修改校验
            if (oldName != null && oldName.equals(name)) {
                return true;
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * 刷新树json
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getnodes", produces = "application/json", method = RequestMethod.GET)
    public String getnodes() {
        return departmentService.getManagerNodes();
    }

    @RequestMapping(value = "/getDepartment", produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody
    String getDepartment() {
        return departmentService.getFunctionNodesByLogin();
    }

}
