package cn.goldencis.vdp.core.service;

import java.util.List;

import cn.goldencis.vdp.core.entity.PermissionDO;

public interface IPermissionService {

    boolean addOrUpdatePermission(PermissionDO record);

    List<PermissionDO> getPermissionList(int start, int length, String searchstr);

    PermissionDO getPermission(String id);

    Integer countPermission(String searchstr);

    void deletePermission(String id);

    List<PermissionDO> getPermissionListNoTable();
}
