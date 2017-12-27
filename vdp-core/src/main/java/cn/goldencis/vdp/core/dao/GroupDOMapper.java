package cn.goldencis.vdp.core.dao;

import cn.goldencis.vdp.common.dao.BaseDao;
import cn.goldencis.vdp.core.entity.GroupDO;
import cn.goldencis.vdp.core.entity.GroupDOCriteria;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

@SuppressWarnings("rawtypes")
public interface GroupDOMapper extends BaseDao {
    long countByExample(GroupDOCriteria example);

    int deleteByExample(GroupDOCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(GroupDO record);

    int insertSelective(GroupDO record);

    List<GroupDO> selectByExampleWithRowbounds(GroupDOCriteria example, RowBounds rowBounds);

    List<GroupDO> selectByExample(GroupDOCriteria example);

    GroupDO selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GroupDO record, @Param("example") GroupDOCriteria example);

    int updateByExample(@Param("record") GroupDO record, @Param("example") GroupDOCriteria example);

    int updateByPrimaryKeySelective(GroupDO record);

    int updateByPrimaryKey(GroupDO record);

    int selectCount();
}