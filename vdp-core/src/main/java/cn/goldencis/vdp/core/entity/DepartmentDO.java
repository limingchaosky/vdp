package cn.goldencis.vdp.core.entity;

import java.io.Serializable;
import java.util.List;

import cn.goldencis.vdp.common.entity.BaseEntity;

public class DepartmentDO extends BaseEntity implements Serializable {
    private List<UserDO> userList;

    private String id;

    private String name;

    private Integer parentId;

    private String departmentRemark;

    private String owner;

    private String departmentTel;

    private String ipPart;

    private String treePath;

    private Integer status;

    private String remark;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getDepartmentRemark() {
        return departmentRemark;
    }

    public void setDepartmentRemark(String departmentRemark) {
        this.departmentRemark = departmentRemark == null ? null : departmentRemark.trim();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    public String getDepartmentTel() {
        return departmentTel;
    }

    public void setDepartmentTel(String departmentTel) {
        this.departmentTel = departmentTel == null ? null : departmentTel.trim();
    }

    public String getIpPart() {
        return ipPart;
    }

    public void setIpPart(String ipPart) {
        this.ipPart = ipPart == null ? null : ipPart.trim();
    }

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath == null ? null : treePath.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPrimaryKey() {
        return this.id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<UserDO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDO> userList) {
        this.userList = userList;
    }

}