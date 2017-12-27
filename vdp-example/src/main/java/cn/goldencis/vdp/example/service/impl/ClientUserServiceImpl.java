package cn.goldencis.vdp.example.service.impl;

import cn.goldencis.vdp.common.dao.BaseDao;
import cn.goldencis.vdp.common.service.impl.AbstractBaseServiceImpl;
import cn.goldencis.vdp.example.dao.ClientUserMapper;
import cn.goldencis.vdp.example.entity.ClientUser;
import cn.goldencis.vdp.example.entity.ClientUserCriteria;
import cn.goldencis.vdp.example.service.IClientUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by limingchao on 2017/12/22.
 */
@Service
public class ClientUserServiceImpl extends AbstractBaseServiceImpl<ClientUser , ClientUserCriteria> implements IClientUserService {

    @Autowired
    private ClientUserMapper clientUserMapper;

    @Override
    protected BaseDao<ClientUser, ClientUserCriteria> getDao() {
        return clientUserMapper;
    }

    @Override
    public ClientUser getByPrimaryKey(String primaryKeyStr) {
        int primaryKey = Integer.parseInt(primaryKeyStr);
        return getDao().selectByPrimaryKey(primaryKey);
    }

}
