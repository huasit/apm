package com.huasit.apm.core.user.controller;

import com.google.common.collect.ImmutableMap;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.service.UserLoginService;
import com.huasit.apm.core.user.service.UserService;
import com.huasit.apm.system.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     *
     */
    @ResponseBody
    @GetMapping("/get/")
    public ResponseEntity<Map<String, Object>> get(Long id, HttpServletRequest request) {
        User user;
        if(id == null) {
            user = this.userLoginService.getLoginUser(request);
        } else {
            user = this.userService.getUserById(id);
        }
        user.setPassword("***");
        return new ResponseEntity<>(ImmutableMap.of("user", user), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @GetMapping("/list/")
    public ResponseEntity<Map<String, Object>> list(User form, @RequestParam("page") int page, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        Page<User> list = this.userService.list(form, page, pageSize, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("list", list), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @GetMapping("/list/thirdparty/")
    public ResponseEntity<Map<String, Object>> list(Long thirdpartyId, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        List<User> list = this.userService.getByThirdpartyId(thirdpartyId,loginUser);
        return new ResponseEntity<>(ImmutableMap.of("list", list), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @RequestMapping("/addOrUpdate/")
    public ResponseEntity<Map<String, Object>> addOrUpdate(@RequestBody User form, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        if(!loginUser.isAdmin() && (form.getId() == null || !form.getId().equals(loginUser.getId()))) {
            throw new SystemException(10002);
        }
        this.userService.save(form, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("user", form), HttpStatus.OK);
    }
    /**
     *
     */
    @ResponseBody
    @RequestMapping("/updatePassword/")
    public ResponseEntity<Map<String, Object>> updatePassword(@RequestParam("password") String password, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.userService.updatePassword(password,loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @RequestMapping("/updateState/")
    public ResponseEntity<Map<String, Object>> delete(@RequestParam("id") Long id,User.State state, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        if(!loginUser.isAdmin()) {
            throw new SystemException(10002);
        }
        this.userService.updateState(id, state,loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     *
     */
    @Autowired
    UserService userService;

    /**
     *
     */
    @Autowired
    UserLoginService userLoginService;
}