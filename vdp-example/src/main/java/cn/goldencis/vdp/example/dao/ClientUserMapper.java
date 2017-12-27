package cn.goldencis.vdp.example.dao;

import cn.goldencis.vdp.common.dao.BaseDao;
import cn.goldencis.vdp.example.entity.ClientUser;
import cn.goldencis.vdp.example.entity.ClientUserCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ClientUserMapper extends BaseDao {
    long countByExample(ClientUserCriteria example);

    int deleteByExample(ClientUserCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(ClientUser record);

    int insertSelective(ClientUser record);

    List<ClientUser> selectByExampleWithRowbounds(ClientUserCriteria example, RowBounds rowBounds);

    List<ClientUser> selectByExample(ClientUserCriteria example);

    ClientUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ClientUser record, @Param("example") ClientUserCriteria example);

    int updateByExample(@Param("record") ClientUser record, @Param("example") ClientUserCriteria example);

    int updateByPrimaryKeySelective(ClientUser record);

    int updateByPrimaryKey(ClientUser record);
}