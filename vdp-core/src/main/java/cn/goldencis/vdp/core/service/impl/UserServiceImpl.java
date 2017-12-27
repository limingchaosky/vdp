package cn.goldencis.vdp.core.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import cn.goldencis.vdp.core.dao.*;
import cn.goldencis.vdp.core.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.goldencis.vdp.common.dao.BaseDao;
import cn.goldencis.vdp.common.service.impl.AbstractBaseServiceImpl;
import cn.goldencis.vdp.common.utils.DateUtil;
import cn.goldencis.vdp.common.utils.StringUtil;
import cn.goldencis.vdp.core.constants.ConstantsDto;
import cn.goldencis.vdp.core.service.IUserService;
import cn.goldencis.vdp.core.utils.GetLoginUser;

import com.alibaba.fastjson.JSONObject;

/**
 * 管理员service实现类
 *
 * @author Administrator
 */
@Component("userService")
public class UserServiceImpl extends AbstractBaseServiceImpl<UserDO, UserDOCriteria> implements IUserService {

    private final static int UPDATE_FLAG = 2;
    private final static int UPDATE_ROLE = 3;
    private final static int ADD_FLAG = 1;

    @Autowired
    private UserDOMapper mapper;

    @Autowired
    private DepartmentDOMapper dmapper;

    @Autowired
    private CustomizedMapper cmapper;

    @Autowired
    private UserDepartmentDOMapper userDepartmentDOMapper;

    @Autowired
    private CUserGroupDOMapper cuserGroupDOMapper;

    @Autowired
    private CUserDepartmentDOMapper cuserDepartmentDOMapper;

    @Autowired
    private CDepartmentDOMapper cdepartmentDOMapper;

    @SuppressWarnings("unchecked")
    @Override
    protected BaseDao<UserDO, UserDOCriteria> getDao() {
        return mapper;
    }

    @Transactional
    @Override
    public boolean addOrUpdateUser(UserDO user, String departmentListStr, String navigationListStr) {
        user.setStatus(11);
        if(StringUtil.isEmpty(user.getId())){
            user.setId(UUID.randomUUID().toString());
        }
        if (user.getDepartment() == null || user.getDepartment() ==0) {
            user.setDepartment(1);
        }
        String[] departmentArr = departmentListStr.split(",");
        if (user.getId() == null || "".equals(user.getId())) {
            if (cmapper.selectCountByUserName(user.getUserName()) != 0) {
                return false;
            }
            user.setId(UUID.randomUUID().toString());
            user.setCreateTime(new Date());
            mapper.insertSelective(user);
            cmapper.insertDeptRole(user.getId(), Arrays.asList(departmentArr));
        } else if (user.getId() != null && !"".equals(user.getId())) {
            //用户id存在，执行用户的选择性更新。
            mapper.updateByPrimaryKeySelective(user);

            //根据用户id获取数据库中的部门权限
//            UserDepartmentDOCriteria userDepartmentDOCriteria = new UserDepartmentDOCriteria();
//            userDepartmentDOCriteria.createCriteria().andUserIdEqualTo(user.getId());
//            List<UserDepartmentDO> userDepartmentDOList = userDepartmentDOMapper.selectByExample(userDepartmentDOCriteria);
//
//            //遍历数据库中的部门权限
//            for (UserDepartmentDO userDepartmentDO : userDepartmentDOList) {
//                String departmentId = String.valueOf(userDepartmentDO.getDepartmentId());
//
//                //与更新的权限进行对比
//                for (String departmentStr : departmentArr) {
//                    //如果匹配，当前部门权限不用做修改
//                    if (departmentId.equals(departmentStr));
//                }
//            }

            List<String> list = new ArrayList<String>();
            list.add(user.getId());
            cuserDepartmentDOMapper.deleteBatch(list, null);
            //cuserGroupDOMapper.deleteBatch(list, null);
            cmapper.insertDeptRole(user.getId(), Arrays.asList(departmentArr));

            Integer roleType = mapper.selectByPrimaryKey(user.getId()).getRoleType();
            if ( roleType != null && roleType != user.getRoleType() ) {
                mapper.updateByPrimaryKeySelective(user);
            }

        }
        return true;
    }

    @Cacheable(value = "loginUser", key = "#username")
    public UserDO getLoginUser(String username) {
        UserDOCriteria userexample = new UserDOCriteria();
        userexample.createCriteria().andUserNameEqualTo(username).andStatusEqualTo(11);
        UserDO user = super.getBy(userexample);
        return user;
    }

    @Cacheable(value = "superDepartment")
    @Override
    public DepartmentDO getSuperDepartment() {
        return dmapper.selectByPrimaryKey(ConstantsDto.SUPER_DEPARTMENT_ID);
    }

    @Override
    public List<DepartmentDO> getLoginDepartMent(String name) {
        UserDOCriteria userexample = new UserDOCriteria();
        userexample.createCriteria().andUserNameEqualTo(name).andStatusEqualTo(11);
        UserDO user = super.getBy(userexample);
        return cmapper.getUserDepartmentByUser(user.getId());
    }

    @Override
    public List<DepartmentDO> getUserRoleDepartmentByUserOptimised(String id) {
        List<DepartmentDO> list = cmapper.getUserDepartmentByUser(id);
        List<String> intList = new ArrayList<String>();
        for (DepartmentDO tmp : list) {
            intList.add(tmp.getId());
        }
        return cmapper.getUserRoleDepartmentByParentIdOptimised(intList);
    }

    @Override
    public Long countUserList(UserDO user) {
        return cmapper.countUserList(user);
    }

    @Override
    public List<UserDO> getUserList(UserDO user) {
        return cmapper.getUserList(user);
    }

    @Override
    public List<UserDO> getAllUser() {
        return cmapper.getAllUser();
    }

    @Override
    public List<UserDO> getUserListByType(UserDO user) {
        return cmapper.getUserListByType(user);
    }

    @Transactional
    @Override
    public void deleteUser(List<String> id) {
        cmapper.deleteBatchUserGroup(id);
        cuserDepartmentDOMapper.deleteBatch(id, null);
        cmapper.updateByBatchUser(id);
        //改为真实删除
        //cmapper.deleteByBatchUser(id);
    }

    @Override
    public UserDO getUser(String id) {
        UserDO temp = mapper.selectByPrimaryKey(id);
        String str = JSONObject.toJSONString(temp);
        UserDO record = JSONObject.parseObject(str, UserDO.class);
        UserDepartmentDOCriteria userexample = new UserDepartmentDOCriteria();
        userexample.createCriteria().andUserIdEqualTo(id);
        List<UserDepartmentDO> list = userDepartmentDOMapper.selectByExample(userexample);
        if (list.size() > 0) {
            record.setDepartmentList(list);
        }
        return record;
    }

    @Override
    public void deleteUserByDepartmentId(String departmentId, String userId) {
        List<String> list = new ArrayList<String>();
        String arrStr[] = userId.split(",");
        for (String temp : arrStr) {
            list.add(temp);
        }
        cuserDepartmentDOMapper.deleteBatch(list, departmentId);
        for (String temp : list) {
            UserDepartmentDOCriteria criteria = new UserDepartmentDOCriteria();
            criteria.createCriteria().andUserIdEqualTo(temp);
            List<UserDepartmentDO> tempList = userDepartmentDOMapper.selectByExample(criteria);
            if (tempList.size() == 0) {
                UserDepartmentDO record = new UserDepartmentDO();
                record.setDepartmentId(new Integer(ConstantsDto.DEPARTMENT_UNKOWN_GROUP));
                record.setUserId(temp);
                userDepartmentDOMapper.insert(record);
            }
        }
    }

    @Override
    public List<DepartmentDO> getUserListByDepartment(String userId) {
        boolean flag = false;
        if (StringUtil.isEmpty(userId)) {
            userId = GetLoginUser.getLoginUser().getId();
        }

        if (userId.equals(ConstantsDto.ADMIN_ID))
            flag = true;

        UserDepartmentDOCriteria userexample = new UserDepartmentDOCriteria();
        userexample.createCriteria().andUserIdEqualTo(userId);

        List<UserDepartmentDO> list = userDepartmentDOMapper.selectByExample(userexample);
        List<DepartmentDO> depList = cdepartmentDOMapper.selectNoParentDepartment();
        List<DepartmentDO> redepList = new ArrayList<DepartmentDO>();
        if (flag) {
            for (DepartmentDO temp1 : depList) {
                UserDepartmentDO udp = new UserDepartmentDO();
                udp.setDepartmentId(Integer.parseInt(temp1.getId()));
                udp.setUserId(ConstantsDto.ADMIN_ID);
                temp1.setUserList(cuserDepartmentDOMapper.selectUserListByDepartment(udp));
                redepList.add(temp1);
            }
        } else {
            for (DepartmentDO temp1 : depList) {
                for (UserDepartmentDO temp2 : list) {
                    if (temp2.getDepartmentId().toString().equals(temp1.getId())) {
                        temp1.setUserList(cuserDepartmentDOMapper.selectUserListByDepartment(temp2));
                        redepList.add(temp1);
                    }
                }
            }
        }
        return redepList;
    }

    @Override
    public Long selectUserCountByPermission(String permissionId) {
        UserDOCriteria example = new UserDOCriteria();
        example.createCriteria().andRoleTypeEqualTo(Integer.parseInt(permissionId));
        return mapper.countByExample(example);
    }

    @Override
    public List<UserDO> getUserListByName(UserDO user) {
        return cmapper.getUserListByName(user);
    }

    @Override
    public UserDO getLoginUserNoCache(String username) {
        UserDOCriteria userexample = new UserDOCriteria();
        userexample.createCriteria().andIdEqualTo(username);
        UserDO user = super.getBy(userexample);
        return user;
    }

    @Override
    public UserDO getLoginUserNoCacheByUserName(String username) {
        UserDOCriteria userexample = new UserDOCriteria();
        userexample.createCriteria().andUserNameEqualTo(username);
        UserDO user = super.getBy(userexample);
        return user;
    }


    @Override
    public void updateUser(UserDO user) {
        mapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<UserDO> queryUserExclude(String id) {
        return mapper.queryUserExclude(id);
    }

    @Override
    public int queryRefusePromptUser(String userId) {
        return mapper.queryRefusePromptUser(userId);
    }

    @Override
    public int insertRefusePromptUser(String userId) {
        return mapper.insertRefusePromptUser(userId);
    }

    @Override
    public synchronized int updateErrorLoginCount(String userId, boolean success) throws Exception {
        int flag = 0;
        //登录成功 清除错误记录
        if (success) {
            mapper.updateErrorLoginCount(userId, DateUtil.format(new Date(), DateUtil.FMT_DATE), flag);
            return flag;
        }
        Map<String, Object> map = mapper.queryErrorLoginInfo(userId);
        if (map != null) {
            int count = map.get("error_login_count") == null ? 0 : Integer.valueOf(map.get("error_login_count").toString());
            String lastLoginTimeStr = map.get("error_login_last_time") == null ? null : map.get("error_login_last_time").toString();
            if (count < 5) {
                flag = count + 1;
                mapper.updateErrorLoginCount(userId, DateUtil.format(new Date(), DateUtil.FMT_DATE), flag);
            } else {
                Date nowDate = new Date();
                Date lastLoginTime = DateUtil.strToDate(lastLoginTimeStr, DateUtil.FMT_DATE);
                flag = count + 1;
                if ((nowDate.getTime() - lastLoginTime.getTime()) > 10 * 60 * 1000) {
                    mapper.updateErrorLoginCount(userId, DateUtil.format(new Date(), DateUtil.FMT_DATE), flag);
                } else {
                    mapper.updateErrorLoginCount(userId, lastLoginTimeStr, flag);
                }
            }
        }
        return flag;
    }

    @Override
    public Map<String, Object> queryErrorLoginInfo(String userId) {
        return mapper.queryErrorLoginInfo(userId);
    }
}
