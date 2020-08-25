package com.huasit.apm.business.submission.service;

import com.huasit.apm.business.submission.entity.*;
import com.huasit.apm.business.submission.form.*;
import com.huasit.apm.core.comment.entity.Comment;
import com.huasit.apm.core.comment.entity.CommentRepository;
import com.huasit.apm.core.file.entity.File;
import com.huasit.apm.core.file.service.FileService;
import com.huasit.apm.core.role.service.RoleService;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.service.UserService;
import com.huasit.apm.system.exception.SystemException;
import com.huasit.apm.system.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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
public class SubmissionService implements ApplicationRunner {

    // Status
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
    // 90 待/已审计复审

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
    public Page<Submission> list(Submission form,Long assignedId, int page, int pageSize, User loginUser) {
        boolean viewAll = this.roleService.checkUserHasRole(loginUser.getId(), "submission_list_all_view");
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("id")));
        return this.submissionRepository.findAll(new Specification<Submission>() {
            @Override
            public Predicate toPredicate(Root<Submission> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(!DataUtil.stringIsEmpty(form.getItemCode())) {
                    predicates.add(cb.like(root.get("itemCode").as(String.class), "%" + form.getItemCode().trim() + "%"));
                }
                if(!DataUtil.stringIsEmpty(form.getAuditNo())) {
                    predicates.add(cb.like(root.get("auditNo").as(String.class), "%" + form.getAuditNo().trim() + "%"));
                }
                if(!DataUtil.stringIsEmpty(form.getContractNo())) {
                    predicates.add(cb.like(root.get("contractNo").as(String.class), "%" + form.getContractNo().trim() + "%"));
                }
                if (!DataUtil.stringIsEmpty(form.getProjectName())) {
                    predicates.add(cb.like(root.get("projectName").as(String.class), "%" + form.getProjectName().trim() + "%"));
                }
                if (form.getConstructionUnit() != null) {
                    predicates.add(cb.equal(root.get("constructionUnit").as(Long.class), form.getConstructionUnit()));
                }
                if(form.getContractMoney() != null) {
                    predicates.add(cb.equal(root.get("contractMoney").as(BigDecimal.class), form.getContractMoney()));
                }
                if (!DataUtil.stringIsEmpty(form.getAuditType())) {
                    predicates.add(cb.equal(root.get("auditType").as(String.class), form.getAuditType().trim()));
                }
                if(assignedId != null) {
                    predicates.add(cb.equal(root.get("assigned.id").as(Long.class), assignedId));
                }

                if (form.getStatus() != 0) {
                    predicates.add(cb.equal(root.get("status").as(int.class), form.getStatus()));
                }
                if(!viewAll) {
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
        if (form.getStatus() != -10 && form.getStatus() != 10) {
            throw new SystemException(30000);
        }
        form.setAssigned(null);
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
    public void delete(Long id, User loginUser) {
        this.submissionRepository.updateDel(id, true, loginUser.getId(), new Date());
    }

    /**
     *
     */
    public void projectApprove(Comment comment, User loginUser) {
        Submission submission = this.submissionRepository.findSubmissionById(comment.getTargetId());
        if (submission == null || submission.getStatus() != 10) {
            throw new SystemException(30000);
        }
        comment.setTarget("submission");
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage("project");
        this.commentRepository.save(comment);
        if (comment.getType() == Comment.CommentType.ALLOW && submission.getAuditNo() == null) {
            String auditNo = this.generateAuditNo();
            this.submissionRepository.updateStatusAndAuditNo(comment.getTargetId(), 20, auditNo, loginUser.getId(), new Date());
        } else {
            this.submissionRepository.updateStatus(comment.getTargetId(), comment.getType() == Comment.CommentType.ALLOW ? 20 : -20, loginUser.getId(), new Date());
        }
    }

    /**
     *
     */
    public void projectApproves(Long[] targetIds, int type,  String commentContent, User loginUser) {
        for (Long targetId : targetIds) {
            Comment comment = new Comment();
            comment.setTargetId(targetId);
            comment.setContent(commentContent);
            comment.setType(Comment.CommentType.get(type));
            this.projectApprove(comment, loginUser);
        }
    }

    /**
     *
     */
    public void distributionApprove(Comment comment,  String auditType, Long assignedId, User loginUser) {
        Submission submission = this.submissionRepository.findSubmissionById(comment.getTargetId());
        if (submission == null || submission.getStatus() != 20) {
            throw new SystemException(30000);
        }
        comment.setTarget("submission");
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage("distribution");
        this.commentRepository.save(comment);
        if (comment.getType() == Comment.CommentType.ALLOW) {
            User user = this.userService.getUserById(assignedId);
            this.submissionRepository.updateStatusAndAssigned(comment.getTargetId(), 30, user,auditType, loginUser.getId(), new Date());
        } else {
            this.submissionRepository.updateStatus(comment.getTargetId(), -20, loginUser.getId(), new Date());
        }
    }

    /**
     *
     */
    public void distributionApproves(Long[] targetIds, int type,  String auditType,Long assignedId, String commentContent,  User loginUser) {
        for (Long targetId : targetIds) {
            Comment comment = new Comment();
            comment.setTargetId(targetId);
            comment.setContent(commentContent);
            comment.setType(Comment.CommentType.get(type));
            this.distributionApprove(comment, auditType, assignedId, loginUser);
        }
    }

    /**
     *
     */
    public void checkApprove(Comment comment, User loginUser) {
        Submission submission = this.submissionRepository.findSubmissionById(comment.getTargetId());
        if (submission == null || submission.getStatus() != 30) {
            throw new SystemException(30000);
        }
        comment.setTarget("submission");
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage("check");
        this.commentRepository.save(comment);
        if (comment.getType() == Comment.CommentType.ALLOW) {
            this.submissionRepository.updateStatus(comment.getTargetId(), 40, loginUser.getId(), new Date());
        } else {
            this.submissionRepository.updateStatus(comment.getTargetId(), -20, loginUser.getId(), new Date());
        }
    }

    /**
     *
     */
    public void checkApproves(Long[] targetIds, int type, String commentContent,  User loginUser) {
        for (Long targetId : targetIds) {
            Comment comment = new Comment();
            comment.setTargetId(targetId);
            comment.setContent(commentContent);
            comment.setType(Comment.CommentType.get(type));
            this.checkApprove(comment, loginUser);
        }
    }

    /**
     *
     */
    public void surveyPrepareApprove(Comment comment, String prepareViewDate,String viewDate,String viewPeopleIds, User loginUser) throws ParseException {
        Submission submission = this.submissionRepository.findSubmissionById(comment.getTargetId());
        if (submission == null || submission.getStatus() != 40) {
            throw new SystemException(30000);
        }
        comment.setTarget("submission");
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage("survey_prepare");
        this.commentRepository.save(comment);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.submissionRepository.updateWhileSurveyPrepare(comment.getTargetId(), 50, sdf.parse(prepareViewDate), sdf.parse(viewDate), viewPeopleIds, loginUser.getId(), new Date());
    }

    /**
     *
     */
    public void surveySceneApprove(SurveySceneForm form, User loginUser) {
        Submission submission = this.submissionRepository.findSubmissionById(form.getTargetId());
        if (submission == null || submission.getStatus() != 50) {
            throw new SystemException(30000);
        }
        Comment comment = new Comment();
        comment.setTargetId(form.getTargetId());
        comment.setContent(form.getComment());
        comment.setType(form.getType());
        comment.setTarget("submission");
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage("survey_scene");
        this.commentRepository.save(comment);
        submission.setStatus(60);
        submission.setSurveyFiles(form.getSurveyFiles());
        submission.setModifyId(loginUser.getId());
        submission.setModifyTime(new Date());
        this.submissionRepository.save(submission);
    }

    /**
     *
     */
    public void argueApprove(ArgueForm form, User loginUser) {
        Submission submission = this.submissionRepository.findSubmissionById(form.getTargetId());
        if (submission == null || submission.getStatus() != 60) {
            throw new SystemException(30000);
        }
        Comment comment = new Comment();
        comment.setTargetId(form.getTargetId());
        comment.setContent(form.getComment());
        comment.setType(form.getType());
        comment.setTarget("submission");
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage("argue");
        this.commentRepository.save(comment);
        if (comment.getType() == Comment.CommentType.ALLOW) {
            submission.setStatus(70);
        } else {
            submission.setStatus(-30);
        }
        submission.setArgueFiles(form.getArgueFiles());
        submission.setModifyId(loginUser.getId());
        submission.setModifyTime(new Date());
        this.submissionRepository.save(submission);
    }



    /**
     *
     */
    public void argueRejectApprove(ArgueRejectForm form, User loginUser) {
        Submission submission = this.submissionRepository.findSubmissionById(form.getTargetId());
        if (submission == null || submission.getStatus() != -30) {
            throw new SystemException(30000);
        }
        Comment comment = new Comment();
        comment.setTargetId(form.getTargetId());
        comment.setContent(form.getComment());
        comment.setType(form.getType());
        comment.setTarget("submission");
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage("argue");
        this.commentRepository.save(comment);
        submission.setStatus(60);
        submission.setSupplementFiles(form.getSupplementFiles());
        submission.setModifyId(loginUser.getId());
        submission.setModifyTime(new Date());
        this.submissionRepository.save(submission);
    }

    /**
     *
     */
    public void auditFirstApprove(AuditFirstForm form, User loginUser) throws ParseException {
        Submission submission = this.submissionRepository.findSubmissionById(form.getTargetId());
        if (submission == null || submission.getStatus() != 70) {
            throw new SystemException(30000);
        }
        Comment comment = new Comment();
        comment.setTargetId(form.getTargetId());
        comment.setContent(form.getComment());
        comment.setType(form.getType());
        comment.setTarget("submission");
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage("audit_first");
        this.commentRepository.save(comment);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        submission.setStatus(80);
        submission.setPrepareViewDate2(form.getPrepareViewDate2() == null ? null : sdf.parse(form.getPrepareViewDate2()));
        submission.setViewDate2(form.getViewDate2() == null ? null : sdf.parse(form.getViewDate2()));
        submission.setViewPeopleIds2(form.getViewPeopleIds2());
        submission.setSubmissionPrice(form.getSubmissionPrice());
        submission.setFirstAuditPrice(form.getFirstAuditPrice());
        submission.setAuditFirstFiles(form.getAuditFirstFiles());
        submission.setModifyId(loginUser.getId());
        submission.setModifyTime(new Date());
        this.submissionRepository.save(submission);
    }

    /**
     *
     */
    public void auditSecondApprove(AuditSecondForm form, User loginUser) {
        Submission submission = this.submissionRepository.findSubmissionById(form.getTargetId());
        if (submission == null || submission.getStatus() != 80) {
            throw new SystemException(30000);
        }
        Comment comment = new Comment();
        comment.setTargetId(form.getTargetId());
        comment.setContent(form.getComment());
        comment.setType(form.getType());
        comment.setTarget("submission");
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage("audit_second");
        this.commentRepository.save(comment);
        submission.setStatus(90);
        submission.setSecondAuditPrice(form.getSecondAuditPrice());

        BigDecimal auditFee = new BigDecimal(0);
        BigDecimal subtractPrice = submission.getSubmissionPrice().subtract(submission.getSecondAuditPrice());
        BigDecimal s1 = submission.getSubmissionPrice().floatValue() == 0 ? new BigDecimal(0) : subtractPrice.divide(submission.getSubmissionPrice(), 2, BigDecimal.ROUND_HALF_UP);
        if(s1.floatValue() >= 0.05 && s1.floatValue() < 0.1) {
            auditFee = subtractPrice.multiply(new BigDecimal("0.05"));
        } else if (s1.floatValue() >= 0.1){
            auditFee = subtractPrice.multiply(new BigDecimal("0.1"));
        }
        submission.setAuditFee(auditFee);
        submission.setSubtractPrice(subtractPrice);

        submission.setAuditSecondFiles(form.getAuditSecondFiles());
        submission.setModifyId(loginUser.getId());
        submission.setModifyTime(new Date());
        this.submissionRepository.save(submission);
    }

    /**
     *
     */
    private int auditNoMax;

    /**
     *
     */
    @Override
    public void run(ApplicationArguments applicationArguments) {
        String max = this.submissionRepository.findMaxAuditNo();
        if (max != null) {
            this.auditNoMax = Integer.parseInt(max.substring(4));
        } else {
            this.auditNoMax = 0;
        }
    }

    /**
     *
     */
    private synchronized String generateAuditNo() {
        return String.format("%d%04d", Calendar.getInstance().get(Calendar.YEAR), ++this.auditNoMax);
    }

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
    CommentRepository commentRepository;

    /**
     *
     */
    @Autowired
    SubmissionRepository submissionRepository;
}