package cn.goldencis.vdp.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.goldencis.vdp.common.annotation.Mybatis;
import cn.goldencis.vdp.core.entity.GroupDO;

@Mybatis
public interface CGroupDOMapper {

    Integer selectGroupCount(@Param("name") String name);

    List<GroupDO> selectList();

    Integer selectGroupMax();

    List<GroupDO> selectEqOnePerGroupByUserId(String userId);

    List<GroupDO> selectGroupListByUserId(String userId);
}
