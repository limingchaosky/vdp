package cn.goldencis.vdp.core.dao;

import cn.goldencis.vdp.common.dao.BaseDao;
import cn.goldencis.vdp.core.entity.UserGroupDO;
import cn.goldencis.vdp.core.entity.UserGroupDOCriteria;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

@SuppressWarnings("rawtypes")
public interface UserGroupDOMapper extends BaseDao {
    long countByExample(UserGroupDOCriteria example);

    int deleteByExample(UserGroupDOCriteria example);

    int insert(UserGroupDO record);

    int insertSelective(UserGroupDO record);

    List<UserGroupDO> selectByExampleWithRowbounds(UserGroupDOCriteria example, RowBounds rowBounds);

    List<UserGroupDO> selectByExample(UserGroupDOCriteria example);

    int updateByExampleSelective(@Param("record") UserGroupDO record, @Param("example") UserGroupDOCriteria example);

    int updateByExample(@Param("record") UserGroupDO record, @Param("example") UserGroupDOCriteria example);
}