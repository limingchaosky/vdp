package cn.goldencis.vdp.core.dao;

import cn.goldencis.vdp.common.dao.BaseDao;
import cn.goldencis.vdp.core.entity.SystemValidate;
import cn.goldencis.vdp.core.entity.SystemValidateCriteria;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

@SuppressWarnings("rawtypes")
public interface SystemValidateMapper extends BaseDao {
    long countByExample(SystemValidateCriteria example);

    int deleteByExample(SystemValidateCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(SystemValidate record);

    int insertSelective(SystemValidate record);

    List<SystemValidate> selectByExampleWithRowbounds(SystemValidateCriteria example, RowBounds rowBounds);

    List<SystemValidate> selectByExample(SystemValidateCriteria example);

    SystemValidate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SystemValidate record, @Param("example") SystemValidateCriteria example);

    int updateByExample(@Param("record") SystemValidate record, @Param("example") SystemValidateCriteria example);

    int updateByPrimaryKeySelective(SystemValidate record);

    int updateByPrimaryKey(SystemValidate record);
}