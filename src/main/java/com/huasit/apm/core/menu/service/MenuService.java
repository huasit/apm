package com.huasit.apm.core.menu.service;

import com.huasit.apm.core.menu.entity.Menu;
import com.huasit.apm.core.menu.entity.MenuRepository;
import com.huasit.apm.core.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    /**
     *
     */
    public List<Menu> getUserMenuTree(User user) {
        List<Integer> auths = new ArrayList<>();
        if (user.isAdmin()) {
            auths.add(0);
            auths.add(1);
            auths.add(2);
            auths.add(3);
        } else if (user.getType() == User.Type.THIRDPARTY) {
            auths.add(0);
            auths.add(3);
        } else {
            auths.add(0);
            auths.add(2);
        }
        List<Menu> menus = this.menuRepository.findByAuth(auths);
        if (menus == null) {
            return null;
        }
        List<Menu> list = new ArrayList<>();
        for (Menu menu : menus) {
            if (menu.getPid() == 0L) {
                list.add(this.getUserMenuTree(menu, menus));
            }
        }
        return list;
    }

    /**
     *
     */
    public List<Menu> getUserMenuTreeWithParent(Long parentId, Long userId) {
        List<Menu> menus = this.menuRepository.findAll();
        if (menus == null) {
            return null;
        }
        Menu menu = this.menuRepository.findMenuById(parentId);
        if (menu == null) {
            return null;
        }
        return menu.getChildrens();
    }

    /**
     *
     */
    private Menu getUserMenuTree(Menu menu, List<Menu> menus) {
        for (Menu m : menus) {
            if (menu.getId().equals(m.getPid())) {
                List<Menu> childrens = menu.getChildrens();
                if (childrens == null) {
                    childrens = new ArrayList<>();
                }
                childrens.add(this.getUserMenuTree(m, menus));
                menu.setChildrens(childrens);
            }
        }
        return menu;
    }

    /**
     *
     */
    @Autowired
    MenuRepository menuRepository;
}