package com.huasit.apm.core.workitem.controller;

import com.google.common.collect.ImmutableMap;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.service.UserLoginService;
import com.huasit.apm.core.user.service.UserService;
import com.huasit.apm.core.workitem.entity.Workitem;
import com.huasit.apm.core.workitem.entity.WorkitemHis;
import com.huasit.apm.core.workitem.service.WorkitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/workitem")
public class WorkitemController {

    /**
     *
     */
    @ResponseBody
    @GetMapping("/undo/")
    public ResponseEntity<Map<String, Object>> list(@RequestParam("page") int page, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        Page<Workitem> list = this.workitemService.getWorkitemWithUndo(loginUser.getId(), page, pageSize);
        return new ResponseEntity<>(ImmutableMap.of("list", list), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @GetMapping("/apply/")
    public ResponseEntity<Map<String, Object>> applyed(@RequestParam("page") int page, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        Page<WorkitemHis> list = this.workitemService.getWorkitemWithApply(loginUser.getId(), page, pageSize);
        return new ResponseEntity<>(ImmutableMap.of("list", list), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @GetMapping("/approve/")
    public ResponseEntity<Map<String, Object>> approved(@RequestParam("page") int page, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        Page<WorkitemHis> list = this.workitemService.getWorkitemWithApprove(loginUser.getId(), page, pageSize);
        return new ResponseEntity<>(ImmutableMap.of("list", list), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @GetMapping("/approve/audit/")
    public ResponseEntity<Map<String, Object>> approvedAudit(@RequestParam("page") int page, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        Page<Workitem> list = this.workitemService.getWorkitemWithAudit(loginUser.getId(), page, pageSize);
        return new ResponseEntity<>(ImmutableMap.of("list", list), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @GetMapping("/reach/")
    public ResponseEntity<Map<String, Object>> approvedAudit(@RequestParam("target") String target, @RequestParam("targetId") Long targetId, HttpServletRequest request) {
        List<Object[]> list = this.workitemService.getWorkitemReachTime(target, targetId);
        return new ResponseEntity<>(ImmutableMap.of("list", list), HttpStatus.OK);
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
    WorkitemService workitemService;

    /**
     *
     */
    @Autowired
    UserLoginService userLoginService;
}