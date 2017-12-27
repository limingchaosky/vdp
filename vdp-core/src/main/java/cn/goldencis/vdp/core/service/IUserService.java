package cn.goldencis.vdp.core.service;

import java.util.List;
import java.util.Map;

import cn.goldencis.vdp.common.service.BaseService;
import cn.goldencis.vdp.core.entity.DepartmentDO;
import cn.goldencis.vdp.core.entity.UserDO;
import cn.goldencis.vdp.core.entity.UserDOCriteria;

/**
 * 管理员service
 *
 * @author Administrator
 */
public interface IUserService extends BaseService<UserDO, UserDOCriteria> {

    List<UserDO> getUserList(UserDO user);

    List<UserDO> getAllUser();

    UserDO getLoginUser(String username);

    UserDO getLoginUserNoCache(String username);

    Long countUserList(UserDO user);

    /**
     * 获取顶级部门
     *
     * @return
     */
    DepartmentDO getSuperDepartment();

    /**
     * 根据登录用户获取权限部门
     *
     * @param name
     * @return
     */
    List<DepartmentDO> getLoginDepartMent(String name);

    List<DepartmentDO> getUserRoleDepartmentByUserOptimised(String id);

    boolean addOrUpdateUser(UserDO user, String departmentListStr, String navigationListStr);

    void deleteUser(List<String> list);

    UserDO getUser(String id);

    List<UserDO> getUserListByType(UserDO user);

    void deleteUserByDepartmentId(String departmentId, String userId);

    List<DepartmentDO> getUserListByDepartment(String userId);

    Long selectUserCountByPermission(String permissionId);

    List<UserDO> getUserListByName(UserDO user);

    void updateUser(UserDO user);

    List<UserDO> queryUserExclude(String id);

    /**
     * 查询用户是否不想显示提示
     *
     * @param userId
     * @return
     */
    int queryRefusePromptUser(String userId);

    /**
     * 用户不想显示提示 保存
     *
     * @param userId
     * @return
     */
    int insertRefusePromptUser(String userId);

    int updateErrorLoginCount(String userId, boolean success) throws Exception;

    Map<String, Object> queryErrorLoginInfo(String userId);

	UserDO getLoginUserNoCacheByUserName(String userName);
}
