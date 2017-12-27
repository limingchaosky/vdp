package cn.goldencis.vdp.core.entity;

import java.io.Serializable;
import java.util.List;

import cn.goldencis.vdp.common.entity.BaseEntity;

public class PermissionDO extends BaseEntity implements Serializable {
    private String id;
    private Integer intId;
    private String name;

    private Integer visible;

    private String remark;
    private String nav;
    private String user;

    private String userCount;

    private List<PermissionNavigationDO> list;

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

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPrimaryKey() {
        return getId();
    }

    public List<PermissionNavigationDO> getList() {
        return list;
    }

    public void setList(List<PermissionNavigationDO> list) {
        this.list = list;
    }

    public String getNav() {
        return nav;
    }

    public void setNav(String nav) {
        this.nav = nav;
    }

    public String getUserCount() {
        return userCount;
    }

    public void setUserCount(String userCount) {
        this.userCount = userCount;
    }

    public Integer getIntId() {
        return intId;
    }

    public void setIntId(Integer intId) {
        this.intId = intId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}