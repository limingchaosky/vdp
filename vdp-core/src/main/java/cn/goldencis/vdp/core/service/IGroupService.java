package cn.goldencis.vdp.core.service;

import java.util.List;

import cn.goldencis.vdp.core.entity.GroupDO;

public interface IGroupService {
    boolean addOrUpdateGroup(GroupDO group);

    boolean addUserByGroup(GroupDO group);

    boolean deleteUserByGroup(GroupDO group);

    boolean deleteGroupById(String id);

    List<GroupDO> selectList(String type);

    GroupDO getGroupById(String id);

    boolean selectByGroupName(String groupName);

    /**
     * 查询组内只有一人并且是传入的userId的list
     * @return
     */
    List<GroupDO> selectEqOnePerGroupByUserId(String userId);

    /**
     * 根据用户id获取用户所在的所有组
     * @param userId
     * @return
     */
    List<GroupDO> selectGroupListByUserId(String userId);
}
