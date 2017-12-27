package cn.goldencis.vdp.core.dao;

import cn.goldencis.vdp.common.dao.BaseDao;
import cn.goldencis.vdp.core.entity.TDictionary;
import cn.goldencis.vdp.core.entity.TDictionaryCriteria;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

@SuppressWarnings("rawtypes")
public interface TDictionaryMapper extends BaseDao {
    long countByExample(TDictionaryCriteria example);

    int deleteByExample(TDictionaryCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(TDictionary record);

    int insertSelective(TDictionary record);

    List<TDictionary> selectByExampleWithRowbounds(TDictionaryCriteria example, RowBounds rowBounds);

    List<TDictionary> selectByExample(TDictionaryCriteria example);

    TDictionary selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TDictionary record, @Param("example") TDictionaryCriteria example);

    int updateByExample(@Param("record") TDictionary record, @Param("example") TDictionaryCriteria example);

    int updateByPrimaryKeySelective(TDictionary record);

    int updateByPrimaryKey(TDictionary record);

    int checkDataRepeat(@Param(value = "id") Integer id, @Param(value = "name") String name);
}