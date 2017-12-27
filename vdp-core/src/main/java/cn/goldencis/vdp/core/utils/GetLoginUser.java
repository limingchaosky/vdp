/**
 *
 */
package cn.goldencis.vdp.core.utils;

import java.util.List;

import javax.annotation.PostConstruct;

import cn.goldencis.vdp.core.entity.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import cn.goldencis.vdp.core.entity.DepartmentDO;
import cn.goldencis.vdp.core.service.IUserService;

/**
 * 获取当前登录用户信息
 * @author Administrator
 *
 */
@Component
public class GetLoginUser {
    @Autowired
    private IUserService userService;
    private static GetLoginUser getLoginUser;

    public void setUserInfo(IUserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        getLoginUser = this;
        getLoginUser.userService = this.userService;
    }

    /**
     * 获取当前登录用户
     * @return
     */
    public static UserDO getLoginUser() {
        String user_name = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            user_name = ((User) principal).getUsername();
        }
        UserDO user = getLoginUser.userService.getLoginUser(user_name);
        return user;
    }

    /**
     * 获取部门权限
     * @return
     */
    public static DepartmentDO getSuperDept() {

        DepartmentDO superDept = getLoginUser.userService.getSuperDepartment();
        return superDept;

    }

    /**
     * 获取部门权限
     * @return
     */
    public static List<DepartmentDO> getDeptRole() {

        String user_name = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            user_name = ((User) principal).getUsername();
        }
        List<DepartmentDO> roleDept = getLoginUser.userService.getLoginDepartMent(user_name);
        return roleDept;

    }

    /**
     * 获取当前用户角色所管部门信息
     * @author mll
     * @return List<DepartmentDO>
     */
    public static List<DepartmentDO> getDepartmentByUser() {
        UserDO user = GetLoginUser.getLoginUser();
        return getLoginUser.userService.getUserRoleDepartmentByUserOptimised(user.getId());
    }

}
