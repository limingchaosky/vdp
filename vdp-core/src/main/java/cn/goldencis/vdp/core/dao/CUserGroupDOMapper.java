package cn.goldencis.vdp.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.goldencis.vdp.common.annotation.Mybatis;
import cn.goldencis.vdp.core.entity.UserGroupDO;

@Mybatis
public interface CUserGroupDOMapper {

    void insertBatch(@Param("list") List<String> list, @Param("groupId") String groupId);

    void deleteBatch(@Param("list") List<String> list, @Param("groupId") String groupId);

    UserGroupDO selectByUserIdAndGroupId(@Param("userId") String userId, @Param("groupId") String groupId);

    List<UserGroupDO> selectUserGroupByGroupId(@Param("groupId") String groupId);

    void insert(@Param("userId") String userId, @Param("groupId") String groupId);
}
