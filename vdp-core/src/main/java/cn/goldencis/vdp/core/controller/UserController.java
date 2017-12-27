package cn.goldencis.vdp.core.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.goldencis.vdp.core.entity.UserDO;
import cn.goldencis.vdp.core.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import cn.goldencis.vdp.common.cache.manager.GuavaCacheManager;
import cn.goldencis.vdp.common.utils.CommonPoiXsl;
import cn.goldencis.vdp.common.utils.DateUtil;
import cn.goldencis.vdp.common.utils.FileDownLoad;
import cn.goldencis.vdp.common.utils.StringUtil;
import cn.goldencis.vdp.core.constants.ConstantsDto;
import cn.goldencis.vdp.core.entity.DepartmentDO;
import cn.goldencis.vdp.core.utils.AuthUtils;
import cn.goldencis.vdp.core.utils.GetLoginUser;
import cn.goldencis.vdp.core.utils.JsonUtil;
import cn.goldencis.vdp.core.utils.PathConfig;
import cn.goldencis.vdp.core.utils.SpecialCharacherUtils;

import com.alibaba.fastjson.JSONArray;

/**
 * 管理员管理controller
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/user")
public class UserController implements ServletContextAware {

    @Autowired
    private IUserService userService;

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private INavigationService navigationService;

    @Autowired
    private IPermissionNavigationService permissionNavigationService;

    @Autowired
    private IGroupService groupService;

    @Autowired
    private GuavaCacheManager cacheManager;

    private static final String FILE_NAME = "用户信息";

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * 管理员主页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        model.setViewName("/user/user/index");
        String zNodes = departmentService.getNodesByLogin();
        model.addObject("zNodes", zNodes);
        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/addOrUpdateUser", method = RequestMethod.POST)
    public String addOrUpdateUser(UserDO user, String departmentListStr, String navigationListStr) {
        //判断新增用户是否超过最大用户数
        /*if (ADD_FLAG == user.getFlag()) {
            Long userCount = userService.countUserList(null, null, null, null);
            Long maxCustomerCnt = Long.valueOf(request.getSession().getAttribute("maxCustomerCnt").toString());
            if (userCount >= maxCustomerCnt) {
                return "exceedsLimit";
            }
        }*/
        if (!userService.addOrUpdateUser(user, departmentListStr, navigationListStr)) {
            return "failed";
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public String deleteUser(String id) {
        UserDO user = GetLoginUser.getLoginUser();
        List<String> list = new ArrayList<String>();
        if (!StringUtil.isEmpty(id)) {
            String strArr[] = id.split(",");
            for (String tmp : strArr) {
                if (user.getId().equals(tmp)) {
                    return "failed";
                }
                list.add(tmp);
            }
        }

        //这里校验是否可以删除该用户
        List<String> canDeleteUser1 = null;
        for (String userId : list) {
            if (groupService.selectEqOnePerGroupByUserId(userId).size() == 0) {
                canDeleteUser1 = new ArrayList<>();
                canDeleteUser1.add(userId);
                userService.deleteUser(canDeleteUser1);
            }
        }
        cacheManager.getCache("loginUser").clear();
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(UserDO user) {
        if (StringUtil.isEmpty(user.getId())) {
            return "NoExistId";
        }
        if (!StringUtil.isEmpty(user.getNewpwd()) && !StringUtil.isEmpty(user.getPassword())) {
            UserDO temp = userService.getLoginUserNoCache(GetLoginUser.getLoginUser().getId());
            if (temp.getPassword().equals(user.getPassword())) {
                user.setPassword(user.getNewpwd());
                userService.updateUser(user);
            } else {
                return "OldPwdError";
            }
        } else {
            user.setPassword(null); //以防万一,将pwd置为null
            userService.updateUser(user);
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public UserDO getUser(String id) {
        return userService.getUser(id);
    }

    @ResponseBody
    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    public List<UserDO> getUserList() {
        return userService.getUserList(null);
    }

    /**
     * 管理员列表
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/datalist", produces = "application/json", method = RequestMethod.GET)
    public Map<String, Object> datalist(UserDO user, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<>();
        if (!StringUtil.isEmpty(user.getSearchstr())) {
            user.setSearchstr(SpecialCharacherUtils.toMyString(user.getSearchstr()));
        }

        long count = userService.countUserList(user);
        List<UserDO> userList = userService.getUserList(user);

        model.put("recordsTotal", count);
        model.put("recordsFiltered", count);
        model.put("data", toUsersJson(userList));
        model.put("exportlength", user.getLength());
        model.put("exportstart", user.getStart());
        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/datalist1", produces = "application/json", method = RequestMethod.GET)
    public Map<String, Object> datalist1(UserDO user, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<>();
        user.setStatus(11);
        long count = userService.countUserList(user);
        List<UserDO> userList = userService.getUserListByType(user);

        model.put("recordsTotal", count);
        model.put("recordsFiltered", count);
        model.put("data", toUsersJson1(userList));
        model.put("exportlength", user.getLength());
        model.put("exportstart", user.getStart());
        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/datalistByName", produces = "application/json", method = RequestMethod.GET)
    public Map<String, Object> datalistByName(UserDO user, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<>();
        if (!StringUtil.isEmpty(user.getSearchstr())) {
            user.setSearchstr(SpecialCharacherUtils.toMyString(user.getSearchstr()));
        }
        user.setStatus(11);
        List<UserDO> userList = userService.getUserListByName(user);

        model.put("data", toUsersJson(userList));
        model.put("exportlength", user.getLength());
        model.put("exportstart", user.getStart());
        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/exportxslall", method = RequestMethod.GET)
    public void exportXslAll(UserDO user, HttpServletRequest request, HttpServletResponse response) {

        List<UserDO> dlist = userService.getUserList(user);
        CommonPoiXsl<UserDO> poiXsl = new CommonPoiXsl<UserDO>();
        String path = "";
        try {
            String key = DateUtil.format(new Date(), "yyyyMMddHHmmss");
            path = PathConfig.HOM_PATH + "/tomcat/webapps/export/";
            if (!(new File(path).isDirectory())) {
                new File(path).mkdirs();
            }
            path = path + FILE_NAME + key + ".xls";
            OutputStream out = new FileOutputStream(path);
            String[] headers = {"账户名称", "管辖部门", "角色", "真实姓名", "电话", "邮箱"};
            poiXsl.exportExcel("用户信息", headers, dlist, out, "yyyy-MM-dd");
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        FileDownLoad filedownload = new FileDownLoad();
        filedownload.download(response, request, path);
    }

    private JSONArray toUsersJson1(List<UserDO> userlist) {
        JSONArray array = new JSONArray();
        for (UserDO user : userlist) {
            JSONArray array1 = new JSONArray();
            array1.add(user.getId());
            array1.add("<span class='user'>" + user.getUserName() + "</span>");
            array1.add("<span class='user'>" + user.getName() + "</span>");

/*            if (StringUtil.isEmpty(user.getDepartmentList())) {
                array1.add("<span class='user'>--</span>");
            } else {
                array1.add("<span class='user'>" + user.getDepartmentList() + "</span>");
            }*/

            if (StringUtil.isEmpty(user.getRoleTypeName())) {
                array1.add("<span class='user'>--</span>");
            } else {
                array1.add("<span class='user'>" + user.getRoleTypeName() + "</span>");
            }
            if (StringUtil.isEmpty(user.getPhone())) {
                array1.add("<span class='user'>--</span>");
            } else {
                array1.add("<span class='user'>" + user.getPhone() + "</span>");
            }
            array1.add(user.getId());
            array.add(array1);
        }
        return array;
    }

    private JSONArray toUsersJson(List<UserDO> userlist) {
        JSONArray array = new JSONArray();
        if (userlist != null) {
            for (UserDO user : userlist) {
                JSONArray array1 = new JSONArray();
                array1.add(user.getId());
                array1.add("<span class='user'>" + user.getUserName() + "</span>");

/*                if (StringUtil.isEmpty(user.getDepartmentNames())) {
                    array1.add("<span class='user'>--</span>");
                } else {
                    array1.add("<span class='user'>" + user.getDepartmentNames() + "</span>");
                }*/

                if (StringUtil.isEmpty(user.getRoleTypeName())) {
                    array1.add("<span class='user'>--</span>");
                } else {
                    array1.add("<span class='user'>" + user.getRoleTypeName() + "</span>");
                }
                if (StringUtil.isEmpty(user.getName())) {
                    array1.add("<span class='user'>--</span>");
                } else {
                    array1.add("<span class='user'>" + user.getName() + "</span>");
                }
                if (StringUtil.isEmpty(user.getPhone())) {
                    array1.add("<span class='user'>--</span>");
                } else {
                    array1.add("<span class='user'>" + user.getPhone() + "</span>");
                }
                if (StringUtil.isEmpty(user.getEmail())) {
                    array1.add("<span class='user'>--</span>");
                } else {
                    array1.add("<span class='user'>" + user.getEmail() + "</span>");
                }
                array1.add(user.getId() + "," + user.getStatus());
                array.add(array1);
            }
        }
        return array;
    }

    /* private JSONArray toUsersJson2(List<UserDO> userlist) {
         JSONArray array = new JSONArray();
         for (UserDO user : userlist) {
             JSONArray array1 = new JSONArray();
             array1.add(user.getId() + "," + user.getStatus());
             array1.add("<span class='user'>" + user.getId() + "</span>");
             if (StringUtil.isEmpty(user.getDepartmentNames())) {
                 array1.add("<span class='user'>--</span>");
             } else {
                 array1.add("<span class='user'>" + user.getDepartmentNames() + "</span>");
             }
             if (StringUtil.isEmpty(user.getRoleTypeName())) {
                 array1.add("<span class='user'>--</span>");
             } else {
                 array1.add("<span class='user'>" + user.getRoleTypeName() + "</span>");
             }
             if (StringUtil.isEmpty(user.getFirst())) {
                 array1.add("<span class='user'>--</span>");
             } else {
                 array1.add("<span class='user'>" + user.getFirst() + "</span>");
             }
             if (StringUtil.isEmpty(user.getPhone())) {
                 array1.add("<span class='user'>--</span>");
             } else {
                 array1.add("<span class='user'>" + user.getPhone() + "</span>");
             }
             if (StringUtil.isEmpty(user.getEmail())) {
                 array1.add("<span class='user'>--</span>");
             } else {
                 array1.add("<span class='user'>" + user.getEmail() + "</span>");
             }

             array1.add(user.getId() + "," + user.getStatus());
             array.add(array1);
         }
         return array;
     }*/

    @ResponseBody
    @RequestMapping("/getUserListByDepartment")
    public List<DepartmentDO> getUserListByDepartment(String userId) {
        return userService.getUserListByDepartment(userId);
    }

    @RequestMapping("/validatOmsUser")
    public String validatOmsUser(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("name");
        String password = request.getParameter("password");
        Integer flag = ConstantsDto.CONST_FALSE;

        //判断是否过期或者没有授权 普通用户不让校验通过
        Map<String, Object> authInfo = AuthUtils.getAuthInfo(servletContext);
        if ("".equals(authInfo.get("authmsg")) || ConstantsDto.ADMIN_NAME.equals(userName)) {
            if (!StringUtil.isEmpty(userName) && !StringUtil.isEmpty(password)) {
            	UserDO userDO = userService.getLoginUserNoCacheByUserName(userName);
                if (userDO != null && password.equals(userDO.getPassword())) {
                    flag = ConstantsDto.CONST_TRUE;
                }
            }
        }

        //String callbackparam = request.getParameter("callbackparam");
        PrintWriter out;
        try {
            response.setCharacterEncoding("utf-8");
            out = response.getWriter();
            //前台更改跨域查询方式 后台更改下接口
            /*String result = callbackparam + "(" + flag + ")";
            System.out.println(result);*/
            out.print(flag);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/queryUserExclude", produces = "application/json", method = RequestMethod.GET)
    public Map<String, Object> queryUserExclude(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<>();
        List<UserDO> userList = userService.queryUserExclude(GetLoginUser.getLoginUser().getId());
        model.put("data", toUsersJson(userList));
        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/insertRefusePromptUser", produces = "application/json", method = RequestMethod.POST)
    public Map<String, Object> insertRefusePromptUser(HttpServletRequest request,
                                               HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultCode", ConstantsDto.CONST_FALSE);
        try {
            UserDO user = GetLoginUser.getLoginUser();
            userService.insertRefusePromptUser(user.getId());
            resultMap.put("resultCode", ConstantsDto.CONST_TRUE);
        } catch (Exception e) {
        }

        return resultMap;
    }
}
