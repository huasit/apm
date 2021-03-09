package com.huasit.apm.core.menu.controller;

import com.google.common.collect.ImmutableMap;
import com.huasit.apm.core.menu.entity.Menu;
import com.huasit.apm.core.menu.service.MenuService;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class MenuController {

    /**
     *
     */
    @ResponseBody
    @GetMapping("/list/")
    public ResponseEntity<Map<String, Object>> list(Long pid, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        List<Menu> list;
        if(pid == null) {
            list = this.menuService.getUserMenuTree(loginUser);
        } else {
            list = this.menuService.getUserMenuTreeWithParent(pid, loginUser.getId());
        }
        return new ResponseEntity<>(ImmutableMap.of("menus", list), HttpStatus.OK);
    }

    /**
     *
     */
    @Autowired
    MenuService menuService;

    /**
     *
     */
    @Autowired
    UserLoginService userLoginService;
}
