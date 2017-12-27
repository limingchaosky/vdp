package cn.goldencis.vdp.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.goldencis.vdp.common.dao.BaseDao;
import cn.goldencis.vdp.common.service.impl.AbstractBaseServiceImpl;
import cn.goldencis.vdp.core.dao.PermissionNavigationDOMapper;
import cn.goldencis.vdp.core.entity.PermissionNavigationDO;
import cn.goldencis.vdp.core.entity.PermissionNavigationDOCriteria;
import cn.goldencis.vdp.core.service.IPermissionNavigationService;

/**
 * 角色对应权限service实现类
 * @author Administrator
 *
 */
@Component("permissionNavigationService")
public class PermissionNavigationServiceImpl extends
        AbstractBaseServiceImpl<PermissionNavigationDO, PermissionNavigationDOCriteria> implements
        IPermissionNavigationService {

    @Autowired
    private PermissionNavigationDOMapper mapper;

    @SuppressWarnings("unchecked")
    @Override
    protected BaseDao<PermissionNavigationDO, PermissionNavigationDOCriteria> getDao() {
        return mapper;
    }

}
