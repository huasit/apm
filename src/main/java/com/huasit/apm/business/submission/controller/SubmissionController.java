package com.huasit.apm.business.submission.controller;

import com.google.common.collect.ImmutableMap;
import com.huasit.apm.business.submission.entity.Submission;
import com.huasit.apm.business.submission.service.SubmissionService;
import com.huasit.apm.core.comment.entity.Comment;
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
@RequestMapping("/submission")
public class SubmissionController {

    /**
     *
     */
    @ResponseBody
    @GetMapping("/get/")
    public ResponseEntity<Map<String, Object>> get(@RequestParam("id") Long id, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        Submission submission = this.submissionService.getById(id, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("submission", submission), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @GetMapping("/list/")
    public ResponseEntity<Map<String, Object>> list(Submission form, @RequestParam("page") int page, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        Page<Submission> list = this.submissionService.list(form, page, pageSize, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("list", list), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @PostMapping("/addOrUpdate/")
    public ResponseEntity<Map<String, Object>> addOrUpdate(@RequestBody Submission form, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.submissionService.save(form, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("id", form.getId()), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @RequestMapping("/delete/")
    public ResponseEntity<Map<String, Object>> delete(@RequestParam("id") Long id, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.submissionService.delete(id, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("id", id), HttpStatus.OK);
    }

    /**
     * 审核人审核
     */
    @ResponseBody
    @PostMapping("/project/approve/")
    public ResponseEntity<Map<String, Object>> projectApprove(@RequestBody Comment comment, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.submissionService.projectApprove(comment, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 审核人批量审核
     */
    @ResponseBody
    @PostMapping("/project/approves/")
    public ResponseEntity<Map<String, Object>> projectApproves(@RequestParam("targetIds") Long[] targetIds, @RequestParam("type") int type, String comment, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.submissionService.projectApproves(targetIds, type, comment, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 分配员批量选择分配人
     */
    @ResponseBody
    @PostMapping("/distribution/approves/")
    public ResponseEntity<Map<String, Object>> distributionApproves(@RequestParam("targetIds") Long[] targetIds, @RequestParam("type") int type, Long assignedId, String comment, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.submissionService.distributionApproves(targetIds, type, assignedId, comment, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 被分配人审核
     */
    @ResponseBody
    @PostMapping("/assigned/approves/")
    public ResponseEntity<Map<String, Object>> assignApproves(@RequestParam("targetIds") Long[] targetIds, @RequestParam("type") int type, String comment, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.submissionService.assignedApproves(targetIds, type, comment, loginUser);
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

    /**
     *
     */
    @Autowired
    SubmissionService submissionService;
}
