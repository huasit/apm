package com.huasit.apm.business.submission.service;

import com.huasit.apm.business.submission.entity.Submission;
import com.huasit.apm.business.submission.entity.SubmissionDetail;
import com.huasit.apm.business.submission.entity.SubmissionRepository;
import com.huasit.apm.core.comment.entity.Comment;
import com.huasit.apm.core.comment.entity.CommentRepository;
import com.huasit.apm.core.file.entity.File;
import com.huasit.apm.core.file.service.FileService;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.system.exception.SystemException;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SubmissionService implements ApplicationRunner {

    // Status
    // -10 保存/未送审/被退回
    // 10 待审核
    // 20 已审核

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
        }
        return submission;
    }

    /**
     *
     */
    public Page<Submission> list(Submission form, int page, int pageSize, User loginUser) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("id")));
        return this.submissionRepository.findAll(new Specification<Submission>() {
            @Override
            public Predicate toPredicate(Root<Submission> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (form.getProjectName() != null && !"".equals(form.getProjectName().trim())) {
                    predicates.add(cb.like(root.get("projectName").as(String.class), "%" + form.getProjectName().trim() + "%"));
                }
                if (form.getStatus() != 0) {
                    predicates.add(cb.equal(root.get("status").as(int.class), form.getStatus()));
                }
                predicates.add(cb.equal(root.get("del").as(boolean.class), false));
                predicates.add(cb.equal(root.get("creatorId").as(Long.class), loginUser.getId()));
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
        form.setModifyId(loginUser.getId());
        form.setModifyTime(new Date());
        if (form.getId() == null) {
            form.setAuditNo(null);
            form.setCreatorId(form.getModifyId());
            form.setCreateTime(form.getModifyTime());
        } else {
            Submission db = this.submissionRepository.findSubmissionById(form.getId());
            if (db == null || db.getDel()) {
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
            this.submissionRepository.updateStatus(comment.getTargetId(), comment.getType() == Comment.CommentType.ALLOW ? 20 : -10, loginUser.getId(), new Date());
        }
    }

    /**
     *
     */
    public void projectApproves(Long[] targetIds, int type, User loginUser) {
        for (Long targetId : targetIds) {
            Comment comment = new Comment();
            comment.setTargetId(targetId);
            comment.setType(Comment.CommentType.get(type));
            this.projectApprove(comment, loginUser);
        }
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
    CommentRepository commentRepository;

    /**
     *
     */
    @Autowired
    SubmissionRepository submissionRepository;
}