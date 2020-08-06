package com.huasit.apm.core.user.controller;

import com.google.common.collect.ImmutableMap;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.service.UserLoginService;
import com.huasit.apm.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public ResponseEntity<Map<String, Object>> get(@RequestParam("id") Long id, HttpServletRequest request) {
        User user = this.userService.getUserById(id);
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
    @RequestMapping("/addOrUpdate/")
    public ResponseEntity<Map<String, Object>> addOrUpdate(@RequestBody User form, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.userService.save(form, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("user", form), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @RequestMapping("/delete/")
    public ResponseEntity<Map<String, Object>> delete(@RequestParam("id") Long id, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.userService.delete(id, loginUser);
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