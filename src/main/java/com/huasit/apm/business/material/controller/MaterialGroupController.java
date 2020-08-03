package com.huasit.apm.business.material.controller;

import com.google.common.collect.ImmutableMap;
import com.huasit.apm.business.material.entity.MaterialGroup;
import com.huasit.apm.business.material.service.MaterialGroupService;
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
@RequestMapping("/material/group")
public class MaterialGroupController {

    /**
     *
     */
    @ResponseBody
    @GetMapping("/list/")
    public ResponseEntity<Map<String, Object>> list(HttpServletRequest request) {
        List<MaterialGroup> list = this.materialGroupService.getAll();
        return new ResponseEntity<>(ImmutableMap.of("list", list), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @GetMapping("/get/")
    public ResponseEntity<Map<String, Object>> get(Long id, HttpServletRequest request) {
        MaterialGroup materialGroup = this.materialGroupService.getById(id);
        return new ResponseEntity<>(ImmutableMap.of("materialGroup", materialGroup), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @PostMapping("/addOrUpdate/")
    public ResponseEntity<Map<String, Object>> addOrUpate(Long id, String name, Long[] materialId, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        MaterialGroup materialGroup;
        if(id == null) {
            materialGroup = this.materialGroupService.add(name, materialId, loginUser);
        } else {
            materialGroup = this.materialGroupService.update(id, name, materialId, loginUser);
        }
        return new ResponseEntity<>(ImmutableMap.of("materialGroup", materialGroup), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @RequestMapping("/delete/")
    public ResponseEntity<Map<String, Object>> delete(Long id, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.materialGroupService.delete(id, loginUser);
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
    MaterialGroupService materialGroupService;
}
