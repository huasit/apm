package com.huasit.apm.business.submission.controller;

import com.google.common.collect.ImmutableMap;
import com.huasit.apm.business.construction.entity.Construction;
import com.huasit.apm.business.construction.service.ConstructionService;
import com.huasit.apm.business.submission.entity.Submission;
import com.huasit.apm.business.submission.form.*;
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
    public ResponseEntity<Map<String, Object>> list(Submission form, Long assignedId, @RequestParam("page") int page, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        Page<Submission> list = this.submissionService.list(form, assignedId, page, pageSize, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("list", list), HttpStatus.OK);
    }

    /**
     *
     */
    @GetMapping("/export/")
    public void list(Submission form, Long assignedId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        User loginUser = this.userLoginService.getLoginUser(request);
        Page<Submission> list = this.submissionService.list(form, assignedId, 1, 9999, loginUser);
        Map<Integer, Flow> statusMap = this.flowService.getTargetStatusMap("submission");
        Map<Long, Construction> constructionMap = this.constructionService.getMap();
        String[] titles = new String[]{"审计状态", "审计编号", "工程项目", "立项代码", "合同编码", "施工单位", "中标/合同金额","中介机构","审计方式","送审金额","审定金额","惩罚性费用"};
        List<Object[]> datas = new ArrayList<>();
        if (list.hasContent()) {
            for (Submission data : list.getContent()) {
                Object[] s = new Object[titles.length];
                s[0] = ExcelUtil.value(statusMap.get(data.getStatus()).getStageStr());
                s[1] = ExcelUtil.value(data.getAuditNo());
                s[2] = ExcelUtil.value(data.getProjectName());
                s[3] = ExcelUtil.value(data.getItemCode());
                s[4] = ExcelUtil.value(data.getContractNo());
                s[5] = ExcelUtil.value(data.getConstructionUnit() == null ? "" : constructionMap.get(data.getConstructionUnit()).getName());
                s[6] = ExcelUtil.value(data.getContractMoney());
                s[7] = ExcelUtil.value(data.getAssigned() == null ? "": data.getAssigned().getName());
                s[8] = ExcelUtil.value(data.getAuditType());
                s[9] = ExcelUtil.value(data.getSubmissionPrice());
                s[10] = ExcelUtil.value(data.getSecondAuditPrice());
                s[11] = ExcelUtil.value(data.getAuditFee());
                datas.add(s);
            }
        }
        WebUtil.excelExport("export", ExcelUtil.export(titles, datas),request,response);
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
     * 提交
     */
    @ResponseBody
    @PostMapping("/start/")
    public ResponseEntity<Map<String, Object>> start(@RequestBody Submission form, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.submissionService.start(form, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 退回重新提交
     */
    @ResponseBody
    @PostMapping("/restart/")
    public ResponseEntity<Map<String, Object>> restart(@RequestBody Submission form, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.submissionService.restart(form, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
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
    public ResponseEntity<Map<String, Object>> projectApproves(@RequestBody List<Comment> comments, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.submissionService.projectApproves(comments, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 分配员批量选择分配人
     */
    @ResponseBody
    @PostMapping("/distribution/approves/")
    public ResponseEntity<Map<String, Object>> distributionApproves(@RequestBody List<Comment> comments, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.submissionService.distributionApproves(comments, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 组长审批
     */
    @ResponseBody
    @PostMapping("/memberl/approve/")
    public ResponseEntity<Map<String, Object>> memberlApprove(@RequestBody Comment comment, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.submissionService.memberlApprove(comment, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 分配审核员审核
     */
    @ResponseBody
    @PostMapping("/check/approves/")
    public ResponseEntity<Map<String, Object>> checkApproves(@RequestBody List<Comment> comments,HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.submissionService.checkApproves(comments, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 勘察准备审批
     */
    @ResponseBody
    @PostMapping("/survey/prepare/approve/")
    public ResponseEntity<Map<String, Object>> surveyPrepareApprove(@RequestBody Comment comment, HttpServletRequest request) throws Exception {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.submissionService.surveyPrepareApprove(comment, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 勘察现场审批
     */
    @ResponseBody
    @PostMapping("/survey/scene/approve/")
    public ResponseEntity<Map<String, Object>> surveySceneApprove(@RequestBody SurveySceneForm form, HttpServletRequest request) throws Exception {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.submissionService.surveySceneApprove(form, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 争议处理审批
     */
    @ResponseBody
    @PostMapping("/argue/approve/")
    public ResponseEntity<Map<String, Object>> argueApprove(@RequestBody ArgueForm form, HttpServletRequest request) throws Exception {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.submissionService.argueApprove(form, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 争议处理中审批
     */
    @ResponseBody
    @PostMapping("/argue/reject/approve/")
    public ResponseEntity<Map<String, Object>> argueRejectApprove(@RequestBody ArgueRejectForm form, HttpServletRequest request) throws Exception {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.submissionService.argueRejectApprove(form, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 审计处审批
     */
    @ResponseBody
    @PostMapping("/audit/dept/approve/")
    public ResponseEntity<Map<String, Object>> auditDeptApprove(@RequestBody Comment comment, HttpServletRequest request) throws Exception {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.submissionService.auditDeptApprove(comment, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 审计初审审批
     */
    @ResponseBody
    @PostMapping("/audit/first/approve/")
    public ResponseEntity<Map<String, Object>> auditFirstApprove(@RequestBody AuditFirstForm form, HttpServletRequest request) throws Exception {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.submissionService.auditFirstApprove(form, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 审计复审审批
     */
    @ResponseBody
    @PostMapping("/audit/second/approve/")
    public ResponseEntity<Map<String, Object>> auditFirstApprove(@RequestBody AuditSecondForm form, HttpServletRequest request) throws Exception {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.submissionService.auditSecondApprove(form, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 完成审批
     */
    @ResponseBody
    @PostMapping("/complete/approves/")
    public ResponseEntity<Map<String, Object>> completeApproves(@RequestBody List<Comment> comments, HttpServletRequest request) throws Exception {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.submissionService.completeApproves(comments, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     * 归档审批
     */
    @ResponseBody
    @PostMapping("/filed/approves/")
    public ResponseEntity<Map<String, Object>> filedApproves(@RequestBody List<Comment> comments, HttpServletRequest request) throws Exception {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.submissionService.filedApproves(comments, loginUser);
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
