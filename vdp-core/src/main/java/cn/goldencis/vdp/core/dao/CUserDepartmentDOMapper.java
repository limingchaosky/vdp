package cn.goldencis.vdp.core.dao;

import java.util.List;

import cn.goldencis.vdp.core.entity.UserDO;
import org.apache.ibatis.annotations.Param;

import cn.goldencis.vdp.common.annotation.Mybatis;
import cn.goldencis.vdp.core.entity.UserDepartmentDO;

@Mybatis
public interface CUserDepartmentDOMapper {

    void deleteBatch(@Param("list") List<String> list, @Param("departmentId") String departmentId);

    List<UserDO> selectUserListByDepartment(UserDepartmentDO udp);

}
