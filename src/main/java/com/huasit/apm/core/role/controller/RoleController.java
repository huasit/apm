package com.huasit.apm.core.role.controller;

import com.google.common.collect.ImmutableMap;
import com.huasit.apm.core.role.entity.Role;
import com.huasit.apm.core.role.service.RoleService;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.service.UserLoginService;
import com.huasit.apm.system.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {

    /**
     *
     */
    @ResponseBody
    @GetMapping("/get/")
    public ResponseEntity<Map<String, Object>> get(Long id, HttpServletRequest request) {
        Role role = this.roleService.getRoleById(id);
        return new ResponseEntity<>(ImmutableMap.of("role", role), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @GetMapping("/list/")
    public ResponseEntity<Map<String, Object>> list(Role form, @RequestParam("page") int page, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        Page<Role> list = this.roleService.list(form, page, pageSize, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("list", list), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @RequestMapping("/addOrUpdate/")
    public ResponseEntity<Map<String, Object>> addOrUpdate(@RequestBody Role form, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        if(!loginUser.isAdmin()) {
            throw new SystemException(10002);
        }
        this.roleService.save(form, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("role", form), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @RequestMapping("/delete/")
    public ResponseEntity<Map<String, Object>> delete(@RequestParam("id") Long id, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        if(!loginUser.isAdmin()) {
            throw new SystemException(10002);
        }
        this.roleService.delete(id, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     *
     */
    @Autowired
    RoleService roleService;

    /**
     *
     */
    @Autowired
    UserLoginService userLoginService;
}
