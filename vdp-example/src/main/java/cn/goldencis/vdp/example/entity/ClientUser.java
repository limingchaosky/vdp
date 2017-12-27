package cn.goldencis.vdp.example.entity;

import cn.goldencis.vdp.common.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class ClientUser extends BaseEntity implements Serializable {
    private Integer id;

    private String guid;

    private String username;

    private String password;

    private String truename;

    private String deptguid;

    private String computerguid;

    private String computername;

    private String ip;

    private Date regtime;

    private Integer policyid;

    private String online;

    private String remark;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename == null ? null : truename.trim();
    }

    public String getDeptguid() {
        return deptguid;
    }

    public void setDeptguid(String deptguid) {
        this.deptguid = deptguid == null ? null : deptguid.trim();
    }

    public String getComputerguid() {
        return computerguid;
    }

    public void setComputerguid(String computerguid) {
        this.computerguid = computerguid == null ? null : computerguid.trim();
    }

    public String getComputername() {
        return computername;
    }

    public void setComputername(String computername) {
        this.computername = computername == null ? null : computername.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public Integer getPolicyid() {
        return policyid;
    }

    public void setPolicyid(Integer policyid) {
        this.policyid = policyid;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online == null ? null : online.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPrimaryKey() {
        return String.valueOf(getId());
    }
}