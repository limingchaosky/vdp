package cn.goldencis.vdp.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.goldencis.vdp.common.annotation.Mybatis;
import cn.goldencis.vdp.core.entity.TDictionary;

@Mybatis
public interface CTDictionaryMapper {

    @Select({ "<script>", "SELECT id,type,value,description FROM t_dictionary",
            "WHERE type=#{type} limit #{start},#{length} ", "</script>" })
    List<TDictionary> queryPage(@Param(value = "type") String type, @Param(value = "start") Integer start, @Param(value = "length") Integer length);
}
