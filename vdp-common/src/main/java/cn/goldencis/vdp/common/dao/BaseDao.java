package cn.goldencis.vdp.common.dao;

import cn.goldencis.vdp.common.entity.BaseEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

/**
 *
 * @param <T><br/>
 * @author liuzhh.
 */
public interface BaseDao<T extends BaseEntity, C> {

    long countByExample(C example);

    int deleteByExample(C example);

    int deleteByPrimaryKey(String primaryKey);

    /**
     *
     * @param record
     * @return
     */
    int insert(T record);

    int insertSelective(T record);

    List<T> selectByExampleWithRowbounds(C example, RowBounds rowBounds);

    List<T> selectByExampleWithBLOBsWithRowbounds(C example, RowBounds rowBounds);

    List<T> selectByExampleWithBLOBs(C example);

    List<T> selectByExample(C example);

    T selectByPrimaryKey(Object primaryKey);

    int updateByExampleSelective(@Param("record") T record, @Param("example") C example);

    int updateByExample(@Param("record") T record, @Param("example") C example);

    int updateByExampleWithBLOBs(@Param("record") T record, @Param("example") C example);

    int updateByPrimaryKeyWithBLOBs(T record);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    SqlSessionTemplate getSessionTemplate();

    SqlSession getSqlSession();

}
