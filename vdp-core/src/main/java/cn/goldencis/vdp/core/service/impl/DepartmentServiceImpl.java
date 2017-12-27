package cn.goldencis.vdp.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.goldencis.vdp.common.dao.BaseDao;
import cn.goldencis.vdp.common.service.impl.AbstractBaseServiceImpl;
import cn.goldencis.vdp.core.constants.ConstantsDto;
import cn.goldencis.vdp.core.dao.CustomizedMapper;
import cn.goldencis.vdp.core.dao.DepartmentDOMapper;
import cn.goldencis.vdp.core.dao.UserDepartmentDOMapper;
import cn.goldencis.vdp.core.entity.DepartmentDO;
import cn.goldencis.vdp.core.entity.DepartmentDOCriteria;
import cn.goldencis.vdp.core.entity.UserDepartmentDO;
import cn.goldencis.vdp.core.entity.UserDepartmentDOCriteria;
import cn.goldencis.vdp.core.service.IDepartmentService;
import cn.goldencis.vdp.core.utils.GetLoginUser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 部门管理service实现类
 * @author Administrator
 *
 */
@Component("departmentService")
public class DepartmentServiceImpl extends AbstractBaseServiceImpl<DepartmentDO, DepartmentDOCriteria> implements
        IDepartmentService {

    @Autowired
    private DepartmentDOMapper mapper;

    @Autowired
    private CustomizedMapper cmapper;

    @Autowired
    private UserDepartmentDOMapper udmapper;

    @SuppressWarnings("unchecked")
    @Override
    protected BaseDao<DepartmentDO, DepartmentDOCriteria> getDao() {
        return mapper;
    }

    /*
     * (non-Javadoc)
     * @see cn.goldencis.tsa.system.service.IDepartmentService#getManagerNodes()
     */
    public String getManagerNodes() {
        String zNodes = toTreeJson(cmapper.getDeptarMentList(null, null, null, null, null), false);
        return zNodes;
    }

    /*
     * 根据登录用户权限获取部门树json
     * @param ischeck 是否有未分组
     * @return
     */
    public String getNodesByLogin() {

        //String zNodes = "";
        List<DepartmentDO> roleDept = GetLoginUser.getDeptRole();

        if (roleDept != null && roleDept.size() > 0 && "1".equals(roleDept.get(0).getId())) {
            //如果有顶级部门权限返回所有
            return toTreeJson(cmapper.getDeptarMentList(null, null, null, null, null), false);
        } else {
            //如果没有顶级部门权限进行查询
            List<Integer> ids = new ArrayList<>();
            List<String> treePaths = new ArrayList<>();
            for (DepartmentDO dept : roleDept) {
                ids.add(Integer.parseInt(dept.getId()));
                treePaths.add(dept.getTreePath() + dept.getId() + ",%");
            }
            return toTreeJson(cmapper.getUserRoleDepartmentByUser(ids, treePaths, true), true);
        }

    }

    /*
     * (non-Javadoc)
     * @see cn.goldencis.tsa.system.service.IDepartmentService#getChecked(java.lang.Integer)
     */
    /*public String getChecked(Integer uid) {
    	List<DepartmentDO> clist=cmapper.getUserDepartmentByUser(uid);
    	StringBuilder dCheck=new StringBuilder();
    	for(DepartmentDO dept:clist){
    		dCheck.append(dept.getId()+",");
    	}
    	return dCheck.toString();
    }*/

    /*
     * (non-Javadoc)
     * @see cn.goldencis.tsa.system.service.IDepartmentService#updatedept(cn.goldencis.tsa.system.entity.DepartmentDO)
     */
    @Transactional
    public boolean updatedept(DepartmentDO bean) {
        bean.setStatus(1);
        mapper.updateByPrimaryKeySelective(bean);
        return true;
    }

    @Override
    public List<String> getAllRoleDept(Integer id) {
        List<DepartmentDO> roleDept = new ArrayList<>();
        List<String> rlist = new ArrayList<>();
        //避免权限为空时跳过条件而查询出所有，前端已限制不可输入未分组
        rlist.add("0");
        //未分组查询
        if (id != null && id.intValue() == 0) {
            return rlist;
            //查询单个(如果是1则查询全部)
        } else if (id != null && id.intValue() != 1) {
            roleDept.add(mapper.selectByPrimaryKey(id.toString()));
            //查询全部
        } else {
            roleDept = GetLoginUser.getDeptRole();
        }

        List<Integer> ids = new ArrayList<>();
        List<String> treePaths = new ArrayList<>();
        for (DepartmentDO dept : roleDept) {
            ids.add(Integer.parseInt(dept.getId()));
            treePaths.add(dept.getTreePath() + dept.getId() + ",%");
        }
        rlist.addAll(getIdlist(cmapper.getUserRoleDepartmentByUser(ids, treePaths, false)));
        return rlist;
    }

    /**
     * 获取部门名称
     * @param roledept
     * @return
     */
    public List<String> getIdlist(List<DepartmentDO> roledept) {
        List<String> rlist = new ArrayList<>();
        if (roledept != null) {
            for (DepartmentDO dept : roledept) {
                rlist.add(dept.getId());
            }
        }
        return rlist;
    }

    @Override
    public List<DepartmentDO> getDeptarMentList(Integer startNum, Integer pageSize, Integer pId, String treePath,
            String ordercase) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getDeptarMentCount(Integer pId, String treePath) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getChecked(Integer uid) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see cn.goldencis.tsa.system.service.IDepartmentService#deleteById(java.lang.Integer)
     */
    @Transactional
    public boolean deleteById(Integer id) {
        UserDepartmentDOCriteria udexample = new UserDepartmentDOCriteria();
        udexample.createCriteria().andDepartmentIdEqualTo(id);
        List<UserDepartmentDO> userList = udmapper.selectByExample(udexample);
        for (UserDepartmentDO temp : userList) {
            UserDepartmentDOCriteria tmpCriteria = new UserDepartmentDOCriteria();
            tmpCriteria.createCriteria().andUserIdEqualTo(temp.getUserId()).andDepartmentIdNotEqualTo(id);
            if (udmapper.selectByExample(tmpCriteria).size() == 0) {
                UserDepartmentDO record = new UserDepartmentDO();
                record.setDepartmentId(new Integer(ConstantsDto.DEPARTMENT_UNKOWN_GROUP));
                record.setUserId(temp.getUserId());
                udmapper.insert(record);
            }
        }
        udmapper.deleteByExample(udexample);
        mapper.deleteByPrimaryKey(id.toString());
        return true;
    }

    /**
     * 转化成ztree json
     * @param departments
     * @param ischeck
     * @return
     */
    private String toTreeJson(List<DepartmentDO> departments, boolean isedit) {
        JSONArray array = new JSONArray();
        JSONObject unknownGroup = new JSONObject();
        boolean hasUnknown = false;
        for (DepartmentDO department : departments) {
            JSONObject obj = new JSONObject();
            if (isedit && 1 == Integer.parseInt(department.getId())) {
                obj.put("checkdisable", true);
            }
            if (2 == Integer.parseInt(department.getId())) {
                unknownGroup.put("remark", department.getDepartmentRemark());
                unknownGroup.put("id", department.getId());
                unknownGroup.put("pId", department.getParentId());
                unknownGroup.put("name", department.getName());
                unknownGroup.put("iconSkin", "tDepartment");
                unknownGroup.put("ParentDepartmentId", department.getParentId());
                unknownGroup.put("open", true);
                unknownGroup.put("status", department.getStatus());
                hasUnknown = true;
            } else {
                obj.put("remark", department.getDepartmentRemark());
                obj.put("id", department.getId());
                obj.put("pId", department.getParentId());
                obj.put("name", department.getName());
                obj.put("iconSkin", "tDepartment");
                obj.put("ParentDepartmentId", department.getParentId());
                obj.put("open", true);
                obj.put("status", department.getStatus());
                array.add(obj);
            }
        }
        if (hasUnknown) {
            array.add(unknownGroup);
        }
        return array.toJSONString();
    }

    @Override
    public DepartmentDO getDepartmentById(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public String getFunctionNodesByLogin() {
        List<DepartmentDO> roleDept = GetLoginUser.getDeptRole();
        List<DepartmentDO> ids = new ArrayList<>();
        DepartmentDO department = null;
        if (roleDept.size() > 0) {
            for (DepartmentDO dept : roleDept) {
                department = new DepartmentDO();
                department.setId(dept.getId());
                dept.setTreePath(dept.getTreePath() == null ? "" : dept.getTreePath());
                department.setTreePath("%" + dept.getTreePath() + dept.getId() + ",%");
                ids.add(department);
            }
            return toTreeJson(cmapper.getFunctionNodesByLogin(ids), true);
        } else {
            List<DepartmentDO> departments = new ArrayList<DepartmentDO>();
            departments.add(getDepartmentById(ConstantsDto.SUPER_DEPARTMENT_ID));
            return toTreeJson(departments, true);
        }
    }
}