package cn.goldencis.vdp.core.dao;

import cn.goldencis.vdp.common.dao.BaseDao;
import cn.goldencis.vdp.core.entity.PermissionNavigationDO;
import cn.goldencis.vdp.core.entity.PermissionNavigationDOCriteria;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

@SuppressWarnings("rawtypes")
public interface PermissionNavigationDOMapper extends BaseDao {
    long countByExample(PermissionNavigationDOCriteria example);

    int deleteByExample(PermissionNavigationDOCriteria example);

    int insert(PermissionNavigationDO record);

    int insertSelective(PermissionNavigationDO record);

    List<PermissionNavigationDO> selectByExampleWithRowbounds(PermissionNavigationDOCriteria example, RowBounds rowBounds);

    List<PermissionNavigationDO> selectByExample(PermissionNavigationDOCriteria example);

    int updateByExampleSelective(@Param("record") PermissionNavigationDO record, @Param("example") PermissionNavigationDOCriteria example);

    int updateByExample(@Param("record") PermissionNavigationDO record, @Param("example") PermissionNavigationDOCriteria example);

    List<PermissionNavigationDO> selectByCondition(@Param("sid") String sid);
}