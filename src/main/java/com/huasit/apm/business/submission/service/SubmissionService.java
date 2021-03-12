package com.huasit.apm.business.submission.service;

import com.huasit.apm.business.submission.entity.*;
import com.huasit.apm.business.submission.form.*;
import com.huasit.apm.business.thirdparty.entity.Thirdparty;
import com.huasit.apm.business.thirdparty.service.ThirdpartyService;
import com.huasit.apm.core.comment.entity.Comment;
import com.huasit.apm.core.comment.entity.CommentRepository;
import com.huasit.apm.core.file.entity.File;
import com.huasit.apm.core.file.service.FileService;
import com.huasit.apm.core.flow.entity.Flow;
import com.huasit.apm.core.flow.service.FlowService;
import com.huasit.apm.core.role.service.RoleService;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.service.UserService;
import com.huasit.apm.core.workitem.service.WorkitemService;
import com.huasit.apm.system.exception.SystemException;
import com.huasit.apm.system.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SubmissionService {

    // Status
    // -30 被审计处退回
    // -20 被退回
    // -10 保存/未送审
    // 10 待审核/已提交
    // 20 待分配员/已审核
    // 30 待分配审核员/已分配
    // 40 待勘察准备/已配审核员
    // 50 待勘察现场/已勘察准备
    // 60 待争议处理/已勘察现场
    // 70 待审计初审/已争议处理
    // 80 待审计复审/已审计初审
    // 90 待完成/已审计复审

    /**
     *
     */
    public Submission getById(Long id, User loginUser) {
        Submission submission = this.submissionRepository.findSubmissionById(id);
        if (submission.getDetails() != null) {
            for (SubmissionDetail detail : submission.getDetails()) {
                if (detail.getmFileIds() != null && !"".equals(detail.getmFileIds())) {
                    List<Long> ids = new ArrayList<>();
                    for (String i : detail.getmFileIds().split(",")) {
                        if (!"".equals(i.trim())) {
                            try {
                                ids.add(Long.parseLong(i));
                            } catch (Exception e) {

                            }
                        }
                    }
                    if (ids.size() > 0) {
                        List<File> files = this.fileService.getFileByIds(ids);
                        detail.setmFiles(files);
                    }
                }
                if (detail.getmFiles() == null) {
                    detail.setmFiles(new ArrayList<>());
                }
            }
            for (SubmissionSurveyFile detail : submission.getSurveyFiles()) {
                if (detail.getmFileIds() != null && !"".equals(detail.getmFileIds())) {
                    List<Long> ids = new ArrayList<>();
                    for (String i : detail.getmFileIds().split(",")) {
                        if (!"".equals(i.trim())) {
                            try {
                                ids.add(Long.parseLong(i));
                            } catch (Exception e) {

                            }
                        }
                    }
                    if (ids.size() > 0) {
                        List<File> files = this.fileService.getFileByIds(ids);
                        detail.setmFiles(files);
                    }
                }
                if (detail.getmFiles() == null) {
                    detail.setmFiles(new ArrayList<>());
                }
            }
            for (SubmissionArgueFile detail : submission.getArgueFiles()) {
                if (detail.getmFileIds() != null && !"".equals(detail.getmFileIds())) {
                    List<Long> ids = new ArrayList<>();
                    for (String i : detail.getmFileIds().split(",")) {
                        if (!"".equals(i.trim())) {
                            try {
                                ids.add(Long.parseLong(i));
                            } catch (Exception e) {

                            }
                        }
                    }
                    if (ids.size() > 0) {
                        List<File> files = this.fileService.getFileByIds(ids);
                        detail.setmFiles(files);
                    }
                }
                if (detail.getmFiles() == null) {
                    detail.setmFiles(new ArrayList<>());
                }
            }
            for (SubmissionAuditFirst detail : submission.getAuditFirstFiles()) {
                if (detail.getmFileIds() != null && !"".equals(detail.getmFileIds())) {
                    List<Long> ids = new ArrayList<>();
                    for (String i : detail.getmFileIds().split(",")) {
                        if (!"".equals(i.trim())) {
                            try {
                                ids.add(Long.parseLong(i));
                            } catch (Exception e) {

                            }
                        }
                    }
                    if (ids.size() > 0) {
                        List<File> files = this.fileService.getFileByIds(ids);
                        detail.setmFiles(files);
                    }
                }
                if (detail.getmFiles() == null) {
                    detail.setmFiles(new ArrayList<>());
                }
            }
            for (SubmissionAuditSecond detail : submission.getAuditSecondFiles()) {
                if (detail.getmFileIds() != null && !"".equals(detail.getmFileIds())) {
                    List<Long> ids = new ArrayList<>();
                    for (String i : detail.getmFileIds().split(",")) {
                        if (!"".equals(i.trim())) {
                            try {
                                ids.add(Long.parseLong(i));
                            } catch (Exception e) {

                            }
                        }
                    }
                    if (ids.size() > 0) {
                        List<File> files = this.fileService.getFileByIds(ids);
                        detail.setmFiles(files);
                    }
                }
                if (detail.getmFiles() == null) {
                    detail.setmFiles(new ArrayList<>());
                }
            }
            for (SubmissionSupplementFile detail : submission.getSupplementFiles()) {
                if (detail.getmFileIds() != null && !"".equals(detail.getmFileIds())) {
                    List<Long> ids = new ArrayList<>();
                    for (String i : detail.getmFileIds().split(",")) {
                        if (!"".equals(i.trim())) {
                            try {
                                ids.add(Long.parseLong(i));
                            } catch (Exception e) {

                            }
                        }
                    }
                    if (ids.size() > 0) {
                        List<File> files = this.fileService.getFileByIds(ids);
                        detail.setmFiles(files);
                    }
                }
                if (detail.getmFiles() == null) {
                    detail.setmFiles(new ArrayList<>());
                }
            }
        }
        return submission;
    }

    /**
     *
     */
    public Page<Submission> list(Submission form, Long assignedId, int page, int pageSize, User loginUser) {
        boolean viewAll = this.roleService.checkUserHasRole(loginUser.getId(), "submission_list_all_view");
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("id")));
        return this.submissionRepository.findAll(new Specification<Submission>() {
            @Override
            public Predicate toPredicate(Root<Submission> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!DataUtil.stringIsEmpty(form.getItemCode())) {
                    predicates.add(cb.like(root.get("itemCode").as(String.class), "%" + form.getItemCode().trim() + "%"));
                }
                if (!DataUtil.stringIsEmpty(form.getAuditNo())) {
                    predicates.add(cb.like(root.get("auditNo").as(String.class), "%" + form.getAuditNo().trim() + "%"));
                }
                if (!DataUtil.stringIsEmpty(form.getContractNo())) {
                    predicates.add(cb.like(root.get("contractNo").as(String.class), "%" + form.getContractNo().trim() + "%"));
                }
                if (!DataUtil.stringIsEmpty(form.getProjectName())) {
                    predicates.add(cb.like(root.get("projectName").as(String.class), "%" + form.getProjectName().trim() + "%"));
                }
                if (form.getConstructionUnit() != null) {
                    predicates.add(cb.equal(root.get("constructionUnit").as(Long.class), form.getConstructionUnit()));
                }
                if (form.getContractMoney() != null) {
                    predicates.add(cb.equal(root.get("contractMoney").as(BigDecimal.class), form.getContractMoney()));
                }
                if (!DataUtil.stringIsEmpty(form.getAuditType())) {
                    predicates.add(cb.equal(root.get("auditType").as(String.class), form.getAuditType().trim()));
                }
                if (assignedId != null) {
                    User u = new User();
                    u.setId(assignedId);
                    predicates.add(cb.equal(root.get("assigned").as(User.class), u));
                }
                if (form.getStatuses() != null && !form.getStatuses().contains(0)) {
                    predicates.add(root.get("status").in(form.getStatuses()));
                }
                if (loginUser.getId().equals(form.getCreatorId())) {
                    predicates.add(cb.equal(root.get("creatorId").as(Long.class), loginUser.getId()));
                } else if (!viewAll && new Long(-1).equals(form.getCreatorId())) {
                    predicates.add(cb.equal(root.get("creatorId").as(Long.class), -1L));
                } else if (!viewAll) {
                    predicates.add(cb.equal(root.get("creatorId").as(Long.class), loginUser.getId()));
                }
                predicates.add(cb.equal(root.get("del").as(boolean.class), false));
                if (predicates.size() > 0) {
                    Predicate[] array = new Predicate[predicates.size()];
                    query.where(predicates.toArray(array));
                }
                return query.getRestriction();
            }
        }, pageRequest);
    }

    /**
     *
     */
    public void save(Submission form, User loginUser) {
        form.setStatus(-10);
        form.setAssigned(null);
        form.setThirdparty(null);
        form.setModifyId(loginUser.getId());
        form.setModifyTime(new Date());
        if (form.getId() == null) {
            form.setAuditNo(null);
            form.setCreatorId(form.getModifyId());
            form.setCreateTime(form.getModifyTime());
        } else {
            Submission db = this.submissionRepository.findSubmissionById(form.getId());
            if (db == null || db.isDel()) {
                throw new SystemException(30000);
            }
            form.setAuditNo(db.getAuditNo());
            form.setCreatorId(db.getCreatorId());
            form.setCreateTime(db.getCreateTime());
        }
        this.submissionRepository.save(form);
    }

    /**
     *
     */
    public void start(Submission form, User loginUser) {
        Flow current = this.flowService.getStartNode(Submission.class);
        form.setStatus(current.getNextAgree().getStatus());
        form.setAssigned(null);
        form.setThirdparty(null);
        form.setModifyId(loginUser.getId());
        form.setModifyTime(new Date());
        if (form.getId() == null) {
            form.setAuditNo(null);
            form.setCreatorId(form.getModifyId());
            form.setCreateTime(form.getModifyTime());
        } else {
            Submission db = this.submissionRepository.findSubmissionById(form.getId());
            if (db == null || db.isDel()) {
                throw new SystemException(30000);
            }
            form.setAuditNo(db.getAuditNo());
            form.setCreatorId(db.getCreatorId());
            form.setCreateTime(db.getCreateTime());
        }
        this.submissionRepository.save(form);
        this.workitemService.createWorkitemWithApply(current.getNextAgree().getStage(), "submission_project_approver", form, loginUser);
    }

    /**
     *
     */
    public void restart(Submission form, User loginUser) {
        Flow current = this.flowService.getCurrentNode("reject", Submission.class);
        Submission submission = this.submissionRepository.findSubmissionById(form.getId());
        if (!this.checkSubmissionStatus(submission, current.getStatus())) {
            throw new SystemException(30000);
        }
        form.setStatus(current.getNextAgree().getStatus());
        form.setAssigned(null);
        form.setThirdparty(null);
        form.setModifyId(loginUser.getId());
        form.setModifyTime(new Date());
        form.setAuditNo(submission.getAuditNo());
        form.setCreatorId(submission.getCreatorId());
        form.setCreateTime(submission.getCreateTime());
        this.submissionRepository.save(form);
        this.workitemService.createWorkitemWithComplete(form.getWorkitemId(), current.getNextAgree().getStage(), "submission_project_approver", form, loginUser);
    }

    /**
     *
     */
    public void delete(Long id, User loginUser) {
        this.submissionRepository.updateDel(id, true, loginUser.getId(), new Date());
    }

    /**
     *
     */
    public void projectApprove(Comment comment, User loginUser) {
        Flow current = this.flowService.getCurrentNode("project", Submission.class);
        Submission submission = this.submissionRepository.findSubmissionById(comment.getTargetId());
        if (!this.checkSubmissionStatus(submission, current.getStatus())) {
            throw new SystemException(30000);
        }
        comment.setTarget(current.getTarget());
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage(current.getStage());
        comment.setStageStr(current.getStageStr());
        comment.setTypeStr(comment.getType() == Comment.CommentType.ALLOW ? current.getAgreeStr() : current.getRejectStr());
        this.commentRepository.save(comment);
        if (comment.getType() == Comment.CommentType.ALLOW) {

            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextAgree().getStage(), "submission_distribution_approver", submission, loginUser);
            if (submission.getAuditNo() == null) {
                this.submissionRepository.updateStatusAndAuditNo(comment.getTargetId(), current.getNextAgree().getStatus(), this.getAuditNo(), loginUser.getId(), new Date());
            } else {
                this.submissionRepository.updateStatus(comment.getTargetId(), current.getNextAgree().getStatus(), loginUser.getId(), new Date());
            }
        } else {
            this.submissionRepository.updateStatus(comment.getTargetId(), current.getNextReject().getStatus(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextReject().getStage(), new Long[]{submission.getCreatorId()}, submission, loginUser);
        }
    }

    /**
     *
     */
    public void projectApproves(List<Comment> comments, User loginUser) {
        for (Comment comment : comments) {
            this.projectApprove(comment, loginUser);
        }
    }

    /**
     *
     */
    public void distributionApprove(Comment comment, User loginUser) {
        Flow current = this.flowService.getCurrentNode("distribution", Submission.class);
        Submission submission = this.submissionRepository.findSubmissionById(comment.getTargetId());
        if (!this.checkSubmissionStatus(submission, current.getStatus())) {
            throw new SystemException(30000);
        }
        comment.setTarget(current.getTarget());
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage(current.getStage());
        comment.setStageStr(current.getStageStr());
        comment.setTypeStr(comment.getType() == Comment.CommentType.ALLOW ? current.getAgreeStr() : current.getRejectStr());
        this.commentRepository.save(comment);
        if (comment.getType() == Comment.CommentType.ALLOW) {
            User assigned = this.userService.getUserById(comment.getAssignedId());
            Thirdparty thirdparty = null;
            if (comment.getThirdpartyId() != null) {
                thirdparty = this.thirdpartyService.getThirdpartyById(comment.getThirdpartyId());
            }
            this.submissionRepository.updateStatusAndAssigned(comment.getTargetId(), current.getNextAgree().getStatus(), assigned, thirdparty, comment.getAuditType(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextAgree().getStage(), new Long[]{assigned.getId()}, submission, loginUser);
        } else {
            this.submissionRepository.updateStatus(comment.getTargetId(), current.getNextReject().getStatus(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextReject().getStage(), new Long[]{submission.getCreatorId()}, submission, loginUser);
        }
    }

    /**
     *
     */
    public void distributionApproves(List<Comment> comments, User loginUser) {
        for (Comment comment : comments) {
            this.distributionApprove(comment, loginUser);
        }
    }

    /**
     *
     */
    public void memberlApprove(Comment comment, User loginUser) {
        Flow current = this.flowService.getCurrentNode("memberl", Submission.class);
        Submission submission = this.submissionRepository.findSubmissionById(comment.getTargetId());
        if (!this.checkSubmissionStatus(submission, current.getStatus())) {
            throw new SystemException(30000);
        }
        comment.setTarget(current.getTarget());
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage(current.getStage());
        comment.setStageStr(current.getStageStr());
        comment.setTypeStr(comment.getType() == Comment.CommentType.ALLOW ? current.getAgreeStr() : current.getRejectStr());
        this.commentRepository.save(comment);
        if (comment.getType() == Comment.CommentType.ALLOW) {
            this.submissionRepository.updateStatusAndMemberIds(comment.getTargetId(), current.getNextAgree().getStatus(), comment.getMemberIds(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextAgree().getStage(), "submission_check_approver", submission, loginUser);
        } else {
            this.submissionRepository.updateStatus(comment.getTargetId(), current.getNextReject().getStatus(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextReject().getStage(), "submission_distribution_approver", submission, loginUser);
        }
    }

    /**
     *
     */
    public void checkApprove(Comment comment, User loginUser) {
        Flow current = this.flowService.getCurrentNode("check", Submission.class);
        Submission submission = this.submissionRepository.findSubmissionById(comment.getTargetId());
        if (!this.checkSubmissionStatus(submission, current.getStatus())) {
            throw new SystemException(30000);
        }
        comment.setTarget(current.getTarget());
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage(current.getStage());
        comment.setStageStr(current.getStageStr());
        comment.setTypeStr(comment.getType() == Comment.CommentType.ALLOW ? current.getAgreeStr() : current.getRejectStr());
        this.commentRepository.save(comment);
        if (comment.getType() == Comment.CommentType.ALLOW) {
            this.submissionRepository.updateStatus(comment.getTargetId(), current.getNextAgree().getStatus(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextAgree().getStage(), "submission_survey_prepare_approver", submission, loginUser);
        } else {
            this.submissionRepository.updateStatus(comment.getTargetId(), current.getNextReject().getStatus(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextReject().getStage(), new Long[]{submission.getAssigned().getId()}, submission, loginUser);
        }
    }

    /**
     *
     */
    public void checkApproves(List<Comment> comments, User loginUser) {
        for (Comment comment : comments) {
            this.checkApprove(comment, loginUser);
        }
    }

    /**
     *
     */
    public void surveyPrepareApprove(Comment comment, User loginUser) throws ParseException {
        Flow current = this.flowService.getCurrentNode("survey_prepare", Submission.class);
        Submission submission = this.submissionRepository.findSubmissionById(comment.getTargetId());
        if (!this.checkSubmissionStatus(submission, current.getStatus())) {
            throw new SystemException(30000);
        }
        comment.setTarget(current.getTarget());
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage(current.getStage());
        comment.setStageStr(current.getStageStr());
        comment.setTypeStr(comment.getType() == Comment.CommentType.ALLOW ? current.getAgreeStr() : current.getRejectStr());
        this.commentRepository.save(comment);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.submissionRepository.updateWhileSurveyPrepare(comment.getTargetId(), current.getNextAgree().getStatus(), sdf.parse(comment.getPrepareViewDate()), loginUser.getId(), new Date());
        this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextAgree().getStage(), new Long[]{submission.getAssigned().getId()}, submission, loginUser);
    }

    /**
     *
     */
    public void surveySceneApprove(SurveySceneForm form, User loginUser) throws ParseException {
        Flow current = this.flowService.getCurrentNode("survey_scene", Submission.class);
        Submission submission = this.submissionRepository.findSubmissionById(form.getTargetId());
        if (!this.checkSubmissionStatus(submission, current.getStatus()) || (form.getStatus() != current.getNextAgree().getStatus() && form.getStatus() != current.getNextReject().getStatus())) {
            throw new SystemException(30000);
        }
        Comment comment = new Comment();
        comment.setTargetId(form.getTargetId());
        comment.setContent(form.getComment());
        comment.setType(form.getType());
        comment.setTarget(current.getTarget());
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage(current.getStage());
        comment.setStageStr(current.getStageStr());
        comment.setTypeStr(comment.getType() == Comment.CommentType.ALLOW ? current.getAgreeStr() : current.getRejectStr());
        this.commentRepository.save(comment);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        submission.setStatus(form.getStatus());
        submission.setViewDate(sdf.parse(form.getViewDate()));
        submission.setSurveyFiles(form.getSurveyFiles());
        submission.setModifyId(loginUser.getId());
        submission.setModifyTime(new Date());
        submission.setViewPeoplesAuditUnitIds(form.getViewPeoplesAuditUnitIds());
        submission.setViewPeoplesBuildUnitIds(form.getViewPeoplesBuildUnitIds());
        submission.setViewPeoplesConstructUnitIds(form.getViewPeoplesConstructUnitIds());
        submission.setViewPeoplesEntrustUnitIds(form.getViewPeoplesEntrustUnitIds());
        this.submissionRepository.save(submission);
        if (form.getStatus() == current.getNextAgree().getStatus()) {
            this.workitemService.createWorkitemWithComplete(form.getWorkitemId(), current.getNextAgree().getStage(), new Long[]{submission.getAssigned().getId()}, submission, loginUser);
        } else if (form.getStatus() == current.getNextReject().getStatus()) {
            this.workitemService.createWorkitemWithComplete(form.getWorkitemId(), current.getNextReject().getStage(), new Long[]{submission.getAssigned().getId()}, submission, loginUser);
        }
    }

    /**
     *
     */
    public void argueApprove(ArgueForm form, User loginUser) {
        Flow current = this.flowService.getCurrentNode("argue", Submission.class);
        Submission submission = this.submissionRepository.findSubmissionById(form.getTargetId());
        if (!this.checkSubmissionStatus(submission, current.getStatus())) {
            throw new SystemException(30000);
        }
        Comment comment = new Comment();
        comment.setTargetId(form.getTargetId());
        comment.setContent(form.getComment());
        comment.setType(form.getType());
        comment.setTarget(current.getTarget());
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage(current.getStage());
        comment.setStageStr(current.getStageStr());
        comment.setTypeStr(comment.getType() == Comment.CommentType.ALLOW ? current.getAgreeStr() : current.getRejectStr());
        this.commentRepository.save(comment);
        submission.setArgueFiles(form.getArgueFiles());
        submission.setModifyId(loginUser.getId());
        submission.setModifyTime(new Date());
        if (comment.getType() == Comment.CommentType.ALLOW) {
            submission.setStatus(current.getNextAgree().getStatus());
            this.workitemService.createWorkitemWithComplete(form.getWorkitemId(), current.getNextAgree().getStage(), new Long[]{submission.getAssigned().getId()}, submission, loginUser);
        } else {
            submission.setStatus(current.getNextReject().getStatus());
            this.workitemService.createWorkitemWithComplete(form.getWorkitemId(), current.getNextReject().getStage(), "submission_audit_dept_approver", submission, loginUser);
        }
        this.submissionRepository.save(submission);
    }

    /**
     *
     */
    public void auditDeptApprove(Comment comment, User loginUser) {
        Flow current = this.flowService.getCurrentNode("audit_dept", Submission.class);
        Submission submission = this.submissionRepository.findSubmissionById(comment.getTargetId());
        if (!this.checkSubmissionStatus(submission, current.getStatus())) {
            throw new SystemException(30000);
        }
        comment.setTarget(current.getTarget());
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage(current.getStage());
        comment.setStageStr(current.getStageStr());
        comment.setTypeStr(comment.getType() == Comment.CommentType.ALLOW ? current.getAgreeStr() : current.getRejectStr());
        this.commentRepository.save(comment);
        if (comment.getType() == Comment.CommentType.ALLOW) {
            this.submissionRepository.updateStatus(comment.getTargetId(), current.getNextAgree().getStatus(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextAgree().getStage(), new Long[]{submission.getAssigned().getId()}, submission, loginUser);
        } else {
            this.submissionRepository.updateStatus(comment.getTargetId(), current.getNextReject().getStatus(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextReject().getStage(), new Long[]{submission.getCreatorId()}, submission, loginUser);
        }
    }

    /**
     *
     */
    public void argueRejectApprove(ArgueRejectForm form, User loginUser) {
        Flow current = this.flowService.getCurrentNode("argue_reject", Submission.class);
        Submission submission = this.submissionRepository.findSubmissionById(form.getTargetId());
        if (!this.checkSubmissionStatus(submission, current.getStatus())) {
            throw new SystemException(30000);
        }
        Comment comment = new Comment();
        comment.setTargetId(form.getTargetId());
        comment.setContent(form.getComment());
        comment.setType(form.getType());
        comment.setTarget(current.getTarget());
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage(current.getStage());
        comment.setStageStr(current.getStageStr());
        comment.setTypeStr(comment.getType() == Comment.CommentType.ALLOW ? current.getAgreeStr() : current.getRejectStr());
        this.commentRepository.save(comment);
        submission.setStatus(current.getNextAgree().getStatus());
        submission.setSupplementFiles(form.getSupplementFiles());
        submission.setModifyId(loginUser.getId());
        submission.setModifyTime(new Date());
        this.submissionRepository.save(submission);
        this.workitemService.createWorkitemWithComplete(form.getWorkitemId(), current.getNextAgree().getStage(), "submission_audit_dept_approver", submission, loginUser);
    }

    /**
     *
     */
    public void auditFirstApprove(AuditFirstForm form, User loginUser) throws ParseException {
        Flow current = this.flowService.getCurrentNode("audit_first", Submission.class);
        Submission submission = this.submissionRepository.findSubmissionById(form.getTargetId());
        if (!this.checkSubmissionStatus(submission, current.getStatus())) {
            throw new SystemException(30000);
        }
        Comment comment = new Comment();
        comment.setTargetId(form.getTargetId());
        comment.setContent(form.getComment());
        comment.setType(form.getType());
        comment.setTarget(current.getTarget());
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage(current.getStage());
        comment.setStageStr(current.getStageStr());
        comment.setTypeStr(comment.getType() == Comment.CommentType.ALLOW ? current.getAgreeStr() : current.getRejectStr());
        this.commentRepository.save(comment);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        submission.setStatus(current.getNextAgree().getStatus());
        submission.setPrepareViewDate2(form.getPrepareViewDate2() == null ? null : sdf.parse(form.getPrepareViewDate2()));
        submission.setViewDate2(form.getViewDate2() == null ? null : sdf.parse(form.getViewDate2()));
        submission.setSubmissionPrice(form.getSubmissionPrice());
        submission.setFirstAuditPrice(form.getFirstAuditPrice());
        submission.setAuditFirstFiles(form.getAuditFirstFiles());
        submission.setAuditFirstSub(form.getAuditFirstSub());
        submission.setAuditFirstSubRatio(form.getAuditFirstSubRatio());
        submission.setViewPeoplesAuditUnitIds2(form.getViewPeoplesAuditUnitIds2());
        submission.setViewPeoplesBuildUnitIds2(form.getViewPeoplesBuildUnitIds2());
        submission.setViewPeoplesConstructUnitIds2(form.getViewPeoplesConstructUnitIds2());
        submission.setViewPeoplesEntrustUnitIds2(form.getViewPeoplesEntrustUnitIds2());
        submission.setQas(form.getQas());
        submission.setModifyId(loginUser.getId());
        submission.setModifyTime(new Date());
        this.submissionRepository.save(submission);
        this.workitemService.createWorkitemWithComplete(form.getWorkitemId(), current.getNextAgree().getStage(), "submission_audit_second_approver", submission, loginUser);
    }

    /**
     *
     */
    public void auditSecondApprove(AuditSecondForm form, User loginUser) {
        Flow current = this.flowService.getCurrentNode("audit_second", Submission.class);
        Submission submission = this.submissionRepository.findSubmissionById(form.getTargetId());
        if (!this.checkSubmissionStatus(submission, current.getStatus())) {
            throw new SystemException(30000);
        }
        Comment comment = new Comment();
        comment.setTargetId(form.getTargetId());
        comment.setContent(form.getComment());
        comment.setType(form.getType());
        comment.setTarget(current.getTarget());
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage(current.getStage());
        comment.setStageStr(current.getStageStr());
        comment.setTypeStr(comment.getType() == Comment.CommentType.ALLOW ? current.getAgreeStr() : current.getRejectStr());
        this.commentRepository.save(comment);
        submission.setStatus(current.getNextAgree().getStatus());
        submission.setSecondAuditPrice(form.getSecondAuditPrice());

        BigDecimal auditFee = new BigDecimal(0);
        BigDecimal subtractPrice = submission.getSubmissionPrice().subtract(submission.getSecondAuditPrice());
        BigDecimal s1 = submission.getSubmissionPrice().floatValue() == 0 ? new BigDecimal(0) : subtractPrice.divide(submission.getSubmissionPrice(), 2, BigDecimal.ROUND_HALF_UP);
        if (s1.floatValue() >= 0.05 && s1.floatValue() < 0.1) {
            auditFee = subtractPrice.multiply(new BigDecimal("0.05"));
        } else if (s1.floatValue() >= 0.1) {
            auditFee = subtractPrice.multiply(new BigDecimal("0.1"));
        }
        submission.setAuditFee(auditFee);
        submission.setSubtractPrice(subtractPrice);

        submission.setAuditSecondFiles(form.getAuditSecondFiles());
        submission.setAuditNote(form.getAuditNote());
        submission.setAuditSecondSub(form.getAuditSecondSub());
        submission.setAuditSecondSubRatio(form.getAuditSecondSubRatio());
        submission.setAuditSecondNote(form.getAuditSecondNote());
        submission.setModifyId(loginUser.getId());
        submission.setModifyTime(new Date());
        this.submissionRepository.save(submission);
        this.workitemService.createWorkitemWithComplete(form.getWorkitemId(), current.getNextAgree().getStage(), new Long[]{submission.getCreatorId()}, submission, loginUser);
    }


    public void takeAdvice(Comment comment, User loginUser) {
        Flow current = this.flowService.getCurrentNode("take_advice", Submission.class);
        Submission submission = this.submissionRepository.findSubmissionById(comment.getTargetId());
        if (!this.checkSubmissionStatus(submission, current.getStatus())) {
            throw new SystemException(30000);
        }
        comment.setTarget(current.getTarget());
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage(current.getStage());
        comment.setStageStr(current.getStageStr());
        comment.setTypeStr(comment.getType() == Comment.CommentType.ALLOW ? current.getAgreeStr() : current.getRejectStr());
        this.commentRepository.save(comment);
        submission.setProjectSum(comment.getProjectSum());
        this.submissionRepository.save(submission);
        if (comment.getType() == Comment.CommentType.ALLOW) {
            this.submissionRepository.updateStatus(submission.getId(), current.getNextAgree().getStatus(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextAgree().getStage(), "submission_filed_approver", submission, loginUser);
        } else {
            this.submissionRepository.updateStatus(submission.getId(), current.getNextReject().getStatus(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextReject().getStage(), "submission_audit_second_approver", submission, loginUser);
        }
    }

    public void completeApprove(Comment comment, User loginUser) {
        Flow current = this.flowService.getCurrentNode("complete", Submission.class);
        Submission submission = this.submissionRepository.findSubmissionById(comment.getTargetId());
        if (!this.checkSubmissionStatus(submission, current.getStatus())) {
            throw new SystemException(30000);
        }
        comment.setTarget(current.getTarget());
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage(current.getStage());
        comment.setStageStr(current.getStageStr());
        comment.setTypeStr(comment.getType() == Comment.CommentType.ALLOW ? current.getAgreeStr() : current.getRejectStr());
        this.commentRepository.save(comment);
        submission.setProjectSum(comment.getProjectSum());
        this.submissionRepository.save(submission);
        if (comment.getType() == Comment.CommentType.ALLOW) {
            this.submissionRepository.updateStatus(submission.getId(), current.getNextAgree().getStatus(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextAgree().getStage(), "submission_filed_approver", submission, loginUser);
        } else {
            this.submissionRepository.updateStatus(submission.getId(), current.getNextReject().getStatus(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextReject().getStage(), "submission_audit_second_approver", submission, loginUser);
        }
    }

    /**
     *
     */
    public void completeApproves(List<Comment> comments, User loginUser) {
        for (Comment comment : comments) {
            this.completeApprove(comment, loginUser);
        }
    }

    /**
     *
     */
    public void filedApprove(Comment comment, User loginUser) {
        Flow current = this.flowService.getCurrentNode("filed", Submission.class);
        Submission submission = this.submissionRepository.findSubmissionById(comment.getTargetId());
        if (!this.checkSubmissionStatus(submission, current.getStatus())) {
            throw new SystemException(30000);
        }
        comment.setTarget(current.getTarget());
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage(current.getStage());
        comment.setStageStr(current.getStageStr());
        comment.setTypeStr(comment.getType() == Comment.CommentType.ALLOW ? current.getAgreeStr() : current.getRejectStr());
        this.commentRepository.save(comment);
        if (comment.getType() == Comment.CommentType.ALLOW) {
            this.submissionRepository.updateStatus(submission.getId(), current.getNextAgree().getStatus(), loginUser.getId(), new Date());
            this.workitemService.completeWorkitem(comment.getWorkitemId(), loginUser);
        } else {
            this.submissionRepository.updateStatus(submission.getId(), current.getNextReject().getStatus(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextReject().getStage(), "submission_filed_approver", submission, loginUser);
        }
    }

    /**
     *
     */
    public void filedApproves(List<Comment> comments, User loginUser) {
        for (Comment comment : comments) {
            this.filedApprove(comment, loginUser);
        }
    }

    /**
     *
     */
    private boolean checkSubmissionStatus(Submission submission, int status) {
        if (submission == null || submission.isDel()) {
            return false;
        }
        return submission.getStatus() == status;
    }

    /**
     *
     */
    private String getAuditNo() {
        String prefix = String.format("JS%d", Calendar.getInstance().get(Calendar.YEAR) - 2000);
        Integer no = this.submissionRepository.findMaxAuditNo(prefix + "%");
        if (no == null) {
            no = 1;
        }
        return String.format(prefix + "%03d", no);
    }

    /**
     *
     */
    @Autowired
    FlowService flowService;

    /**
     *
     */
    @Autowired
    FileService fileService;

    /**
     *
     */
    @Autowired
    UserService userService;

    /**
     *
     */
    @Autowired
    RoleService roleService;

    /**
     *
     */
    @Autowired
    WorkitemService workitemService;

    /**
     *
     */
    @Autowired
    CommentRepository commentRepository;

    /**
     *
     */
    @Autowired
    ThirdpartyService thirdpartyService;

    /**
     *
     */
    @Autowired
    SubmissionRepository submissionRepository;
}