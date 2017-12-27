package cn.goldencis.vdp.core.entity;

import cn.goldencis.vdp.common.entity.BaseEntity;

import java.io.Serializable;

public class SystemValidate extends BaseEntity implements Serializable {
    private Integer id;

    private String userName;

    private String password;

    private String systemId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    public String getPrimaryKey() {
        return null;
    }
}