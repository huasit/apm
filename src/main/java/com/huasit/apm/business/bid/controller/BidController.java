package com.huasit.apm.business.bid.controller;

import com.google.common.collect.ImmutableMap;
import com.huasit.apm.business.bid.entity.Bid;
import com.huasit.apm.business.bid.form.*;
import com.huasit.apm.business.bid.service.BidService;
import com.huasit.apm.business.construction.entity.Construction;
import com.huasit.apm.business.construction.service.ConstructionService;
import com.huasit.apm.business.submission.service.SubmissionService;
import com.huasit.apm.core.comment.entity.Comment;
import com.huasit.apm.core.flow.entity.Flow;
import com.huasit.apm.core.flow.service.FlowService;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.service.UserLoginService;
import com.huasit.apm.core.user.service.UserService;
import com.huasit.apm.system.util.ExcelUtil;
import com.huasit.apm.system.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
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
    @GetMapping("/export/")
    public void list(Bid form, Long assignedId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        User loginUser = this.userLoginService.getLoginUser(request);
        Page<Bid> list = this.bidService.list(form, assignedId, 1, 9999, loginUser);
        Map<Integer, Flow> statusMap = this.flowService.getTargetStatusMap("bid");
        Map<Long, Construction> constructionMap = this.constructionService.getMap();
        String[] titles = new String[]{"审计状态", "审计编号", "工程项目", "立项代码", "中介机构", "审计方式", "送审金额", "审定金额"};
        List<Object[]> datas = new ArrayList<>();
        if (list.hasContent()) {
            for (Bid data : list.getContent()) {
                Object[] s = new Object[titles.length];
                s[0] = ExcelUtil.value(statusMap.get(data.getStatus()).getStageStr());
                s[1] = ExcelUtil.value(data.getAuditNo());
                s[2] = ExcelUtil.value(data.getProjectName());
                s[3] = ExcelUtil.value(data.getItemCode());
                s[4] = ExcelUtil.value(data.getAssigned() == null ? "" : data.getAssigned().getName());
                s[5] = ExcelUtil.value(data.getAuditType());
                s[6] = ExcelUtil.value(data.getSubmissionPrice());
                s[7] = ExcelUtil.value(data.getSecondAuditPrice());
                datas.add(s);
            }
        }
        WebUtil.excelExport("export", ExcelUtil.export(titles, datas), request, response);
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
     * 提交
     */
    @ResponseBody
    @PostMapping("/start/")
    public ResponseEntity<Map<String, Object>> start(@RequestBody Bid form, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.bidService.start(form, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 退回重新提交
     */
    @ResponseBody
    @PostMapping("/restart/")
    public ResponseEntity<Map<String, Object>> restart(@RequestBody Bid form, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.bidService.restart(form, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
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
    public ResponseEntity<Map<String, Object>> projectApproves(@RequestBody List<Comment> comments, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.bidService.projectApproves(comments, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 分配员批量选择分配人
     */
    @ResponseBody
    @PostMapping("/distribution/approves/")
    public ResponseEntity<Map<String, Object>> distributionApproves(@RequestBody List<Comment> comments, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.bidService.distributionApproves(comments, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 组长审批
     */
    @ResponseBody
    @PostMapping("/memberl/approve/")
    public ResponseEntity<Map<String, Object>> memberlApprove(@RequestBody Comment comment, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.bidService.memberlApprove(comment, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 分配审核员审核
     */
    @ResponseBody
    @PostMapping("/check/approves/")
    public ResponseEntity<Map<String, Object>> checkApproves(@RequestBody BidCheckApprovesForm form, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.bidService.checkApproves(form, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 争议处理审批
     */
    @ResponseBody
    @PostMapping("/argue/approve/")
    public ResponseEntity<Map<String, Object>> argueApprove(@RequestBody BidArgueForm form, HttpServletRequest request) throws Exception {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.bidService.argueApprove(form, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 审计处审批
     */
    @ResponseBody
    @PostMapping("/audit/dept/approve/")
    public ResponseEntity<Map<String, Object>> auditDeptApprove(@RequestBody Comment comment, HttpServletRequest request) throws Exception {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.bidService.auditDeptApprove(comment, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 争议处理中审批
     */
    @ResponseBody
    @PostMapping("/argue/reject/approve/")
    public ResponseEntity<Map<String, Object>> argueRejectApprove(@RequestBody BidArgueRejectForm form, HttpServletRequest request) throws Exception {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.bidService.argueRejectApprove(form, loginUser);
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
     * 征求意见
     */
    @ResponseBody
    @PostMapping("/takeAdvice/")
    public ResponseEntity<Map<String, Object>> takeAdvice(@RequestBody Comment comment, HttpServletRequest request) throws Exception {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.bidService.takeAdvice(comment, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 完成审批
     */
    @ResponseBody
    @PostMapping("/complete/approves/")
    public ResponseEntity<Map<String, Object>> completeApproves(@RequestBody List<Comment> comments, HttpServletRequest request) throws Exception {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.bidService.completeApproves(comments, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 归档审批
     */
    @ResponseBody
    @PostMapping("/filed/approves/")
    public ResponseEntity<Map<String, Object>> filedApproves(@RequestBody List<Comment> comments, HttpServletRequest request) throws Exception {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.bidService.filedApproves(comments, loginUser);
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
    FlowService flowService;

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

    /**
     *
     */
    @Autowired
    ConstructionService constructionService;
}
