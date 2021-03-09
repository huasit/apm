package com.huasit.apm.core.dept.controller;

import com.google.common.collect.ImmutableMap;
import com.huasit.apm.core.dept.entity.Dept;
import com.huasit.apm.core.dept.service.DeptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dept")
public class DeptController {

    /**
     *
     */
    @Resource
    DeptService deptService;

    /**
     *
     */
    @ResponseBody
    @GetMapping("/list/")
    public ResponseEntity<Map<String, Object>> list(HttpServletRequest request) {
        List<Dept> list = this.deptService.list();
        return new ResponseEntity<>(ImmutableMap.of("list", list), HttpStatus.OK);
    }
}
