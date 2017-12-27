package cn.goldencis.vdp.core.dao;

import cn.goldencis.vdp.common.dao.BaseDao;
import cn.goldencis.vdp.core.entity.PermissionDO;
import cn.goldencis.vdp.core.entity.PermissionDOCriteria;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

@SuppressWarnings("rawtypes")
public interface PermissionDOMapper extends BaseDao {
    long countByExample(PermissionDOCriteria example);

    int deleteByExample(PermissionDOCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(PermissionDO record);

    int insertSelective(PermissionDO record);

    List<PermissionDO> selectByExampleWithRowbounds(PermissionDOCriteria example, RowBounds rowBounds);

    List<PermissionDO> selectByExample(PermissionDOCriteria example);

    PermissionDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PermissionDO record, @Param("example") PermissionDOCriteria example);

    int updateByExample(@Param("record") PermissionDO record, @Param("example") PermissionDOCriteria example);

    int updateByPrimaryKeySelective(PermissionDO record);

    int updateByPrimaryKey(PermissionDO record);
}