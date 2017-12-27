package cn.goldencis.vdp.core.service;

import java.util.List;

import cn.goldencis.vdp.core.entity.NavigationDO;
import cn.goldencis.vdp.core.entity.UserDO;

public interface INavigationService {

    public List<NavigationDO> getUserNavigation(UserDO user);

    public String getNavigation();
}
