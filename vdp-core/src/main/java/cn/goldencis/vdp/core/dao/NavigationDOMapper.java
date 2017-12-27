package cn.goldencis.vdp.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import cn.goldencis.vdp.common.dao.BaseDao;
import cn.goldencis.vdp.core.entity.NavigationDO;
import cn.goldencis.vdp.core.entity.NavigationDOCriteria;

@SuppressWarnings("rawtypes")
public interface NavigationDOMapper extends BaseDao {
    long countByExample(NavigationDOCriteria example);

    int deleteByExample(NavigationDOCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(NavigationDO record);

    int insertSelective(NavigationDO record);

    List<NavigationDO> selectByExampleWithRowbounds(NavigationDOCriteria example, RowBounds rowBounds);

    List<NavigationDO> selectByExample(NavigationDOCriteria example);

    NavigationDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NavigationDO record, @Param("example") NavigationDOCriteria example);

    int updateByExample(@Param("record") NavigationDO record, @Param("example") NavigationDOCriteria example);

    int updateByPrimaryKeySelective(NavigationDO record);

    int updateByPrimaryKey(NavigationDO record);
}