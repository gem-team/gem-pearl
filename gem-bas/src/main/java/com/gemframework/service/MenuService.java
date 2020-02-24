package com.gemframework.service;

import com.gemframework.common.enums.MenuType;
import com.gemframework.model.po.Menu;
import com.gemframework.model.vo.MenuVo;
import com.gemframework.model.vo.RoleVo;
import com.gemframework.model.vo.response.PageInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MenuService {

    boolean exist(MenuVo vo);

    MenuVo save(MenuVo vo);

    List<MenuVo> findListAll();

    List<MenuVo> findListAllByType(MenuType type);

    List<MenuVo> findListByParams(MenuVo vo);

    List<MenuVo> findListByRoleId(Long roleId);

    List<MenuVo> findListByRoles(List<RoleVo> roles);

    List<MenuVo> findTreeByRoles(List<RoleVo> roles);

    PageInfo findPageByParams(MenuVo vo, Pageable pageable);

    void delete(Long id);

    MenuVo getById(Long id);

}
