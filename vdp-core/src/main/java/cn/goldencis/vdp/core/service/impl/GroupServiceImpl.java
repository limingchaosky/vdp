package cn.goldencis.vdp.core.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.goldencis.vdp.common.utils.StringUtil;
import cn.goldencis.vdp.core.dao.CGroupDOMapper;
import cn.goldencis.vdp.core.dao.CUserGroupDOMapper;
import cn.goldencis.vdp.core.dao.GroupDOMapper;
import cn.goldencis.vdp.core.entity.GroupDO;
import cn.goldencis.vdp.core.entity.GroupDOCriteria;
import cn.goldencis.vdp.core.entity.UserGroupDO;
import cn.goldencis.vdp.core.service.IGroupService;

@Service
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private GroupDOMapper groupDOMapper;

    @Autowired
    private CGroupDOMapper cgroupDOMapper;

    @Autowired
    private CUserGroupDOMapper cuserGroupDOMapper;

    private final static int UPDATE_FLAG = 2;
    private final static int ADD_FLAG = 1;

    @Override
    public List<GroupDO> selectGroupListByUserId(String userId) {
        return cgroupDOMapper.selectGroupListByUserId(userId);
    }

    @Override
    public boolean addOrUpdateGroup(GroupDO group) {
        if (ADD_FLAG == group.getFlag()) {
            if (cgroupDOMapper.selectGroupCount(group.getName()) != 0) {
                return false;
            }
            Integer i = cgroupDOMapper.selectGroupMax();
            if (i == null) {
                i = 0;
            }
            group.setId((++i).toString());
            group.setGroupName(group.getId());
            groupDOMapper.insertSelective(group);
        } else if (UPDATE_FLAG == group.getFlag()) {
            groupDOMapper.updateByPrimaryKey(group);
        }

        return true;
    }

    @Override
    public boolean deleteGroupById(String id) {
        cuserGroupDOMapper.deleteBatch(null, id);
        groupDOMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean addUserByGroup(GroupDO group) {
        List<String> list = null;
        if (!StringUtil.isEmpty(group.getUserId())) {
            String users[] = group.getUserId().split(",");
            list = Arrays.asList(users);
        }
        for (String tmp : list) {
            UserGroupDO ug = cuserGroupDOMapper.selectByUserIdAndGroupId(tmp, group.getId());
            if (ug == null) {
                cuserGroupDOMapper.insert(tmp, group.getId());
            }
        }
        return true;
    }

    @Override
    public boolean deleteUserByGroup(GroupDO group) {
        List<String> list = null;
        if (!StringUtil.isEmpty(group.getUserId())) {
            String users[] = group.getUserId().split(",");
            list = Arrays.asList(users);
        }
        if (list.size() == cuserGroupDOMapper.selectUserGroupByGroupId(group.getGroupId()).size() || cuserGroupDOMapper.selectUserGroupByGroupId(group.getGroupId()).size() == 1) {
            return false;
        }
        cuserGroupDOMapper.deleteBatch(list, group.getGroupId());
        return true;
    }

    @Override
    public List<GroupDO> selectList(String type) {
        List<GroupDO> relist = cgroupDOMapper.selectList();
        if (!"flow".equals(type)) {
            return relist;
        }
        List<GroupDO> list = new ArrayList<GroupDO>();
        for (GroupDO group : relist) {
            if (0 < cuserGroupDOMapper.selectUserGroupByGroupId(group.getId()).size()) {
                list.add(group);
            }
        }
        return list;
    }

    @Override
    public GroupDO getGroupById(String id) {
        return groupDOMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean selectByGroupName(String groupName) {
        if (StringUtil.isEmpty(groupName)) {
            return false;
        }
        GroupDOCriteria example = new GroupDOCriteria();
        example.createCriteria().andNameEqualTo(groupName);
        List<GroupDO> list = groupDOMapper.selectByExample(example);
        if (list.size() > 0) {
            return false;
        }
        return true;
    }

    @Override
    public List<GroupDO> selectEqOnePerGroupByUserId(String userId) {
        return cgroupDOMapper.selectEqOnePerGroupByUserId(userId);
    }
}
