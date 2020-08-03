package com.huasit.apm.core.user.controller;

import com.google.common.collect.ImmutableMap;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.service.UserLoginService;
import com.huasit.apm.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @GetMapping("/info/")
    public ResponseEntity<Map<String, Object>> info(Long id, HttpServletRequest request) {
        User user;
        if(id == null) {
            user = this.userLoginService.getLoginUser(request);
        } else {
            user = this.userService.getUserById(id);
        }
        return new ResponseEntity<>(ImmutableMap.of("user", user), HttpStatus.OK);
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