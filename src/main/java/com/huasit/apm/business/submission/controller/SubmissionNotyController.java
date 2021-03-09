package com.huasit.apm.business.submission.controller;

import com.google.common.collect.ImmutableMap;
import com.huasit.apm.business.submission.entity.SubmissionNoty;
import com.huasit.apm.business.submission.service.SubmissionNotyService;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/submission/noty")
public class SubmissionNotyController {

    /**
     *
     */
    @ResponseBody
    @GetMapping("/get/")
    public ResponseEntity<Map<String, Object>> get(Long submissionId, HttpServletRequest request) {
        SubmissionNoty submissionNoty = this.submissionNotyService.getSubmissionNotyBySubmissionId(submissionId);
        if (submissionNoty == null) {
            return new ResponseEntity<>(ImmutableMap.of("empty", true), HttpStatus.OK);
        }
        return new ResponseEntity<>(ImmutableMap.of("submission_noty", submissionNoty), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @GetMapping("/list/")
    public ResponseEntity<Map<String, Object>> list(SubmissionNoty form, @RequestParam("page") int page, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        Page<SubmissionNoty> list = this.submissionNotyService.list(form, page, pageSize, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("list", list), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @RequestMapping("/addOrUpdate/")
    public ResponseEntity<Map<String, Object>> addOrUpdate(@RequestBody SubmissionNoty form, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.submissionNotyService.save(form, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("role", form), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @RequestMapping("/delete/")
    public ResponseEntity<Map<String, Object>> delete(@RequestParam("id") Long id, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.submissionNotyService.delete(id, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     *
     */
    @Autowired
    UserLoginService userLoginService;

    /**
     *
     */
    @Autowired
    SubmissionNotyService submissionNotyService;
}
