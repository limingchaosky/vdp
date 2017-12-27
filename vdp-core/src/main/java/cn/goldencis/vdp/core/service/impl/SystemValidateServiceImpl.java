package cn.goldencis.vdp.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.goldencis.vdp.core.dao.SystemValidateMapper;
import cn.goldencis.vdp.core.entity.SystemValidate;
import cn.goldencis.vdp.core.service.ISystemValidateService;

@Service
public class SystemValidateServiceImpl implements ISystemValidateService {

    @Autowired
    private SystemValidateMapper systemValidateMapper;

    @Override
    public List<SystemValidate> selectSystemValidateList() {
        return systemValidateMapper.selectByExample(null);
    }
}
