package cn.goldencis.vdp.core.dao;

import cn.goldencis.vdp.common.dao.BaseDao;
import cn.goldencis.vdp.core.entity.UserDO;
import cn.goldencis.vdp.core.entity.UserDOCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public interface UserDOMapper extends BaseDao {
    long countByExample(UserDOCriteria example);

    int deleteByExample(UserDOCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    List<UserDO> selectByExampleWithRowbounds(UserDOCriteria example, RowBounds rowBounds);

    List<UserDO> selectByExample(UserDOCriteria example);

    UserDO selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserDO record, @Param("example") UserDOCriteria example);

    int updateByExample(@Param("record") UserDO record, @Param("example") UserDOCriteria example);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);

    List<UserDO> queryUserExclude(@Param("id") String id);

    int queryRefusePromptUser(@Param("userId") String userId);

    int insertRefusePromptUser(@Param("userId") String userId);

    Map<String, Object> queryErrorLoginInfo(@Param("userId") String userId);

    void updateErrorLoginCount(@Param("userId") String userId, @Param("lastDate") String lastDate,
                               @Param("errorCount") int errorCount);
}