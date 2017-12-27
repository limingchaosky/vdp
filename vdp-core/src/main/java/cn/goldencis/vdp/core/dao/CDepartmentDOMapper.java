package cn.goldencis.vdp.core.dao;

import java.util.List;

import cn.goldencis.vdp.common.annotation.Mybatis;
import cn.goldencis.vdp.core.entity.DepartmentDO;

@Mybatis
public interface CDepartmentDOMapper {
    List<DepartmentDO> selectNoParentDepartment();
}