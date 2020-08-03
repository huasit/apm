package com.huasit.apm.business.material.controller;

import com.google.common.collect.ImmutableMap;
import com.huasit.apm.business.material.entity.Material;
import com.huasit.apm.business.material.service.MaterialService;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.service.UserLoginService;
import com.huasit.apm.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/material")
public class MaterialController {

    /**
     *
     */
    @ResponseBody
    @GetMapping("/list/")
    public ResponseEntity<Map<String, Object>> list(HttpServletRequest request) {
        List<Material> list = this.materialService.getAll();
        return new ResponseEntity<>(ImmutableMap.of("list", list), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @GetMapping("/get/")
    public ResponseEntity<Map<String, Object>> get(Long id, HttpServletRequest request) {
        Material material = this.materialService.getById(id);
        return new ResponseEntity<>(ImmutableMap.of("material", material), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @PostMapping("/addOrUpdate/")
    public ResponseEntity<Map<String, Object>> addOrUpate(Material material, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        if(material.getId() == null) {
            this.materialService.add(material, loginUser);
        } else {
            this.materialService.update(material, loginUser);
        }
        return new ResponseEntity<>(ImmutableMap.of("material", material), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @RequestMapping("/delete/")
    public ResponseEntity<Map<String, Object>> delete(Long id, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.materialService.delete(id, loginUser);
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
    MaterialService materialService;

    /**
     *
     */
    @Autowired
    UserLoginService userLoginService;
}
