package cn.goldencis.vdp.core.entity;

import cn.goldencis.vdp.common.entity.BaseEntity;
import cn.goldencis.vdp.common.annotation.MyFieldAnnotation;
import cn.goldencis.vdp.common.utils.StringUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserDO extends BaseEntity implements Serializable {
    private String id;
    @MyFieldAnnotation(order = 0, desc = "export")
    private String userName;

    private String password;

    private String newpwd;

    private Integer department;

    private DepartmentDO departmentBean;

    private String departmentName;

    private String departmentTel;

    @MyFieldAnnotation(order = 2, desc = "export")
    private String name;

    private Integer sex;

    private Integer visible;

    @MyFieldAnnotation(order = 6, desc = "export")
    private String email;

    @MyFieldAnnotation(order = 5, desc = "export")
    private String phone;

    private String address;

    private Integer status;

    private String skin;

    private boolean isalert;

    private boolean isemail;

    private boolean ismsg;

    private String value;

    private Integer startNum;

    private Integer pageSize;

    private Integer length;

    private Integer start;

    private String searchstr;

    @MyFieldAnnotation(order = 4, desc = "export")
    private List<UserDepartmentDO> departmentList;

    @MyFieldAnnotation(order = 3, desc = "export")
    private String navigationList;

    @MyFieldAnnotation(order = 1, desc = "export")
    private String roleTypeName;

    private Integer roleType;


    private Integer errorLoginCount;

    private Date errorLoginLastTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public DepartmentDO getDepartmentBean() {
        return departmentBean;
    }

    public void setDepartmentBean(DepartmentDO departmentBean) {
        this.departmentBean = departmentBean;
    }

    public String getDepartmentName() {
        if (departmentBean != null) {
            return departmentBean.getName();
        } else {
            return departmentName;
        }
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentTel() {
        if (departmentBean != null) {
            return departmentBean.getDepartmentTel();
        } else {
            return departmentTel;
        }
    }

    public void setDepartmentTel(String departmentTel) {
        this.departmentTel = departmentTel;
    }

    public String getRoleTypeName() {
        if (roleType == null) {

        } else if (roleType.intValue() == 0) {
            return "超级管理员";
        } else if (roleType.intValue() == 1) {
            return "管理员";
        } else if (roleType.intValue() == 2) {
            return "操作员";
        } else if (roleType.intValue() == 3) {
            return "审计员";
        }
        return "未知";

    }

    public void setRoleTypeName(String roleTypeName) {
        this.roleTypeName = roleTypeName;
    }

    public boolean getIsalert() {
        if (status != null) {
            String str = status.toString();
            if (str.length() > 1 && "1".equals(str.substring(str.length() - 2, str.length() - 1))) {
                return true;
            }
        }
        return isalert;
    }

    public void setIsalert(boolean isalert) {
        this.isalert = isalert;
    }

    public boolean getIsemail() {
        if (status != null) {
            String str = status.toString();
            if (str.length() > 2 && "1".equals(str.substring(str.length() - 3, str.length() - 2))) {
                return true;
            }
        }
        return isemail;
    }

    public void setIsemail(boolean isemail) {
        this.isemail = isemail;
    }

    public boolean getIsmsg() {
        if (status != null) {
            String str = status.toString();
            if (str.length() > 3 && "1".equals(str.substring(str.length() - 4, str.length() - 3))) {
                return true;
            }
        }

        return ismsg;
    }

    public void setIsmsg(boolean ismsg) {
        this.ismsg = ismsg;
    }

    public List<UserDepartmentDO> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<UserDepartmentDO> departmentList) {
        this.departmentList = departmentList;
    }

    public String getNavigationList() {
        return navigationList;
    }

    public void setNavigationList(String navigationList) {
        this.navigationList = navigationList;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public Integer getErrorLoginCount() {
        return errorLoginCount;
    }

    public void setErrorLoginCount(Integer errorLoginCount) {
        this.errorLoginCount = errorLoginCount;
    }

    public Date getErrorLoginLastTime() {
        return errorLoginLastTime;
    }

    public void setErrorLoginLastTime(Date errorLoginLastTime) {
        this.errorLoginLastTime = errorLoginLastTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPrimaryKey() {
        return getId();
    }

    public String getOperationLog() {
        StringBuilder sb = new StringBuilder();
        sb.append("用户名：").append(userName);
        if (roleType != null) {
            sb.append("，用户角色编号：").append(roleType);
        }
        if (!StringUtil.isEmpty(name )) {
            sb.append("，真实姓名：").append(name);
        }
        if (!StringUtil.isEmpty(departmentTel )) {
            sb.append("，部门电话：").append(departmentTel);
        }
        if (!StringUtil.isEmpty(departmentName )) {
            sb.append("，部门名称：").append(departmentName);
        }
        return sb.toString();
    }

    public String getNewpwd() {
        return newpwd;
    }

    public void setNewpwd(String newpwd) {
        this.newpwd = newpwd;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getStartNum() {
        return startNum;
    }

    public void setStartNum(Integer startNum) {
        this.startNum = startNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
        this.pageSize = length;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
        this.startNum = start;
    }

    public String getSearchstr() {
        return searchstr;
    }

    public void setSearchstr(String searchstr) {
        this.searchstr = searchstr == "" ? null : searchstr.trim();
    }
}