package com.huasit.apm.business.construction.controller;

import com.google.common.collect.ImmutableMap;
import com.huasit.apm.business.construction.entity.Construction;
import com.huasit.apm.business.construction.service.ConstructionService;
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

@Controller
@RequestMapping("/construction")
public class ConstructionController {

    /**
     *
     */
    @ResponseBody
    @GetMapping("/list/")
    public ResponseEntity<Map<String, Object>> list(Construction form, @RequestParam("page") int page, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        Page<Construction> list = this.constructionService.list(form, page, pageSize, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("list", list), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @GetMapping("/get/")
    public ResponseEntity<Map<String, Object>> get(Long id, HttpServletRequest request) {
        Construction construction = this.constructionService.getById(id);
        return new ResponseEntity<>(ImmutableMap.of("construction", construction), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @PostMapping("/addOrUpdate/")
    public ResponseEntity<Map<String, Object>> addOrUpate(Construction form, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        if(form.getId() == null) {
            this.constructionService.add(form, loginUser);
        } else {
            this.constructionService.update(form, loginUser);
        }
        return new ResponseEntity<>(ImmutableMap.of("construction", form), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @RequestMapping("/delete/")
    public ResponseEntity<Map<String, Object>> delete(Long id, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.constructionService.delete(id, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("id", id), HttpStatus.OK);
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

    /**
     *
     */
    @Autowired
    ConstructionService constructionService;
}
