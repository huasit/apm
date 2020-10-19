package com.huasit.apm.business.bid.controller;

import com.google.common.collect.ImmutableMap;
import com.huasit.apm.business.bid.entity.Bid;
import com.huasit.apm.business.bid.form.BidAuditFirstForm;
import com.huasit.apm.business.bid.form.BidAuditSecondForm;
import com.huasit.apm.business.bid.service.BidService;
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
@RequestMapping("/bid")
public class BidController {

    /**
     *
     */
    @ResponseBody
    @GetMapping("/get/")
    public ResponseEntity<Map<String, Object>> get(@RequestParam("id") Long id, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        Bid bid = this.bidService.getById(id, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("bid", bid), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @GetMapping("/list/")
    public ResponseEntity<Map<String, Object>> list(Bid form, Long assignedId, @RequestParam("page") int page, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        Page<Bid> list = this.bidService.list(form, assignedId, page, pageSize, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("list", list), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @PostMapping("/addOrUpdate/")
    public ResponseEntity<Map<String, Object>> addOrUpdate(@RequestBody Bid form, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.bidService.save(form, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("id", form.getId()), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @RequestMapping("/delete/")
    public ResponseEntity<Map<String, Object>> delete(@RequestParam("id") Long id, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.bidService.delete(id, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("id", id), HttpStatus.OK);
    }

    /**
     * 审核人审核
     */
    @ResponseBody
    @PostMapping("/project/approve/")
    public ResponseEntity<Map<String, Object>> projectApprove(@RequestBody Comment comment, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.bidService.projectApprove(comment, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 审核人批量审核
     */
    @ResponseBody
    @PostMapping("/project/approves/")
    public ResponseEntity<Map<String, Object>> projectApproves(@RequestParam("targetIds") Long[] targetIds, @RequestParam("type") int type, String comment, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.bidService.projectApproves(targetIds, type, comment, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 分配员批量选择分配人
     */
    @ResponseBody
    @PostMapping("/distribution/approves/")
    public ResponseEntity<Map<String, Object>> distributionApproves(@RequestParam("targetIds") Long[] targetIds, @RequestParam("type") int type, String auditType, Long assignedId, Long assignedLinkId, String comment, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.bidService.distributionApproves(targetIds, type, auditType, assignedId, assignedLinkId, comment, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 分配审核员审核
     */
    @ResponseBody
    @PostMapping("/check/approves/")
    public ResponseEntity<Map<String, Object>> checkApproves(@RequestParam("targetIds") Long[] targetIds, @RequestParam("type") int type, String comment, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.bidService.checkApproves(targetIds, type, comment, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 审计初审审批
     */
    @ResponseBody
    @PostMapping("/audit/first/approve/")
    public ResponseEntity<Map<String, Object>> auditFirstApprove(@RequestBody BidAuditFirstForm form, HttpServletRequest request) throws Exception {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.bidService.auditFirstApprove(form, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 审计复审审批
     */
    @ResponseBody
    @PostMapping("/audit/second/approve/")
    public ResponseEntity<Map<String, Object>> auditFirstApprove(@RequestBody BidAuditSecondForm form, HttpServletRequest request) throws Exception {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.bidService.auditSecondApprove(form, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 完成审批
     */
    @ResponseBody
    @PostMapping("/complete/approves/")
    public ResponseEntity<Map<String, Object>> completeApproves(@RequestParam("targetIds") Long[] targetIds, @RequestParam("type") int type, String comment, HttpServletRequest request) throws Exception {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.bidService.completeApproves(targetIds, type, comment, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 归档审批
     */
    @ResponseBody
    @PostMapping("/filed/approves/")
    public ResponseEntity<Map<String, Object>> filedApproves(@RequestParam("targetIds") Long[] targetIds, @RequestParam("type") int type, String comment, HttpServletRequest request) throws Exception {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.bidService.filedApproves(targetIds, type, comment, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     *
     */
    @Autowired
    BidService bidService;

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
