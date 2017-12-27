package cn.goldencis.vdp.core.dao;

import cn.goldencis.vdp.common.annotation.Mybatis;
import cn.goldencis.vdp.common.dao.BaseDao;
import cn.goldencis.vdp.core.entity.TChangeAssignee;
import cn.goldencis.vdp.core.entity.TChangeAssigneeCriteria;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

@SuppressWarnings("rawtypes")
@Mybatis
public interface TChangeAssigneeMapper extends BaseDao {
    long countByExample(TChangeAssigneeCriteria example);

    int deleteByExample(TChangeAssigneeCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(TChangeAssignee record);

    int insertSelective(TChangeAssignee record);

    List<TChangeAssignee> selectByExampleWithRowbounds(TChangeAssigneeCriteria example, RowBounds rowBounds);

    List<TChangeAssignee> selectByExample(TChangeAssigneeCriteria example);

    TChangeAssignee selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TChangeAssignee record, @Param("example") TChangeAssigneeCriteria example);

    int updateByExample(@Param("record") TChangeAssignee record, @Param("example") TChangeAssigneeCriteria example);

    int updateByPrimaryKeySelective(TChangeAssignee record);

    int updateByPrimaryKey(TChangeAssignee record);
}