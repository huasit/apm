package com.huasit.apm.business.bid.service;

import com.huasit.apm.business.bid.entity.*;
import com.huasit.apm.business.bid.form.*;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BidService {

    // Status
    // -20 被退回
    // -10 保存/未送审
    // 10 待审核/已提交
    // 20 待分配员/已审核
    // 30 待分配审核员/已分配
    // 40 待审计初审/已配审核员
    // 50 待审计复审/已审计初审
    // 60 待完成/已审计复审

    /**
     *
     */
    public Bid getById(Long id, User loginUser) {
        Bid bid = this.bidRepository.findBidById(id);
        if (bid.getDetails() != null) {
            for (BidDetail detail : bid.getDetails()) {
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
            for (BidArgueFile detail : bid.getArgueFiles()) {
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
            for (BidSupplementFile detail : bid.getSupplementFiles()) {
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
            for (BidAuditFirst detail : bid.getAuditFirstFiles()) {
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
            for (BidAuditSecond detail : bid.getAuditSecondFiles()) {
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
        return bid;
    }

    /**
     *
     */
    public Page<Bid> list(Bid form, Long assignedId, int page, int pageSize, User loginUser) {
        boolean viewAll = this.roleService.checkUserHasRole(loginUser.getId(), "bid_list_all_view");
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("id")));
        return this.bidRepository.findAll(new Specification<Bid>() {
            @Override
            public Predicate toPredicate(Root<Bid> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!DataUtil.stringIsEmpty(form.getItemCode())) {
                    predicates.add(cb.like(root.get("itemCode").as(String.class), "%" + form.getItemCode().trim() + "%"));
                }
                if (!DataUtil.stringIsEmpty(form.getAuditNo())) {
                    predicates.add(cb.like(root.get("auditNo").as(String.class), "%" + form.getAuditNo().trim() + "%"));
                }
                if (!DataUtil.stringIsEmpty(form.getProjectName())) {
                    predicates.add(cb.like(root.get("projectName").as(String.class), "%" + form.getProjectName().trim() + "%"));
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
    public void save(Bid form, User loginUser) {
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
            Bid db = this.bidRepository.findBidById(form.getId());
            if (db == null || db.isDel()) {
                throw new SystemException(30000);
            }
            form.setAuditNo(db.getAuditNo());
            form.setCreatorId(db.getCreatorId());
            form.setCreateTime(db.getCreateTime());
        }
        this.bidRepository.save(form);
    }

    /**
     *
     */
    public void delete(Long id, User loginUser) {
        this.bidRepository.updateDel(id, true, loginUser.getId(), new Date());
    }

    /**
     *
     */
    public void start(Bid form, User loginUser) {
        Flow current = this.flowService.getStartNode(Bid.class);
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
            Bid db = this.bidRepository.findBidById(form.getId());
            if (db == null || db.isDel()) {
                throw new SystemException(30000);
            }
            form.setAuditNo(db.getAuditNo());
            form.setCreatorId(db.getCreatorId());
            form.setCreateTime(db.getCreateTime());
        }
        this.bidRepository.save(form);
        this.workitemService.createWorkitemWithApply(current.getNextAgree().getStage(), "bid_project_approver", form, loginUser);
    }

    /**
     *
     */
    public void restart(Bid form, User loginUser) {
        Flow current = this.flowService.getCurrentNode("reject", Bid.class);
        if (form.getStatus() != current.getStatus()) {
            throw new SystemException(30000);
        }
        form.setStatus(current.getNextAgree().getStatus());
        form.setAssigned(null);
        form.setThirdparty(null);
        form.setModifyId(loginUser.getId());
        form.setModifyTime(new Date());
        Bid db = this.bidRepository.findBidById(form.getId());
        if (db == null || db.isDel()) {
            throw new SystemException(30000);
        }
        form.setAuditNo(db.getAuditNo());
        form.setCreatorId(db.getCreatorId());
        form.setCreateTime(db.getCreateTime());
        this.bidRepository.save(form);
        this.workitemService.createWorkitemWithComplete(form.getWorkitemId(), current.getNextAgree().getStage(), "bid_project_approver", form, loginUser);
    }

    /**
     *
     */
    public void projectApprove(Comment comment, User loginUser) {
        Flow current = this.flowService.getCurrentNode("project", Bid.class);
        Bid bid = this.bidRepository.findBidById(comment.getTargetId());
        if (bid == null || bid.getStatus() != current.getStatus()) {
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
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextAgree().getStage(), "bid_distribution_approver", bid, loginUser);
            if (bid.getAuditNo() == null) {
                this.bidRepository.updateStatusAndAuditNo(comment.getTargetId(), current.getNextAgree().getStatus(), this.getAuditNo(), loginUser.getId(), new Date());
            } else {
                this.bidRepository.updateStatus(comment.getTargetId(), current.getNextAgree().getStatus(), loginUser.getId(), new Date());
            }
        } else {
            this.bidRepository.updateStatus(comment.getTargetId(), current.getNextReject().getStatus(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextReject().getStage(), new Long[]{bid.getCreatorId()}, bid, loginUser);
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
        Flow current = this.flowService.getCurrentNode("distribution", Bid.class);
        Bid bid = this.bidRepository.findBidById(comment.getTargetId());
        if (bid == null || bid.getStatus() != current.getStatus()) {
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
            this.bidRepository.updateStatusAndAssigned(comment.getTargetId(), current.getNextAgree().getStatus(), assigned, thirdparty, comment.getAuditType(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextAgree().getStage(), new Long[]{assigned.getId()}, bid, loginUser);
        } else {
            this.bidRepository.updateStatus(comment.getTargetId(), current.getNextReject().getStatus(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextReject().getStage(), new Long[]{bid.getCreatorId()}, bid, loginUser);
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
        Flow current = this.flowService.getCurrentNode("memberl", Bid.class);
        Bid bid = this.bidRepository.findBidById(comment.getTargetId());
        if (!this.checkBidStatus(bid, current.getStatus())) {
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
            this.bidRepository.updateStatusAndMemberIds(comment.getTargetId(), current.getNextAgree().getStatus(), comment.getMemberIds(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextAgree().getStage(), "bid_check_approver", bid, loginUser);
        } else {
            this.bidRepository.updateStatus(comment.getTargetId(), current.getNextReject().getStatus(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextReject().getStage(), "bid_distribution_approver", bid, loginUser);
        }
    }

    /**
     *
     */
    public void checkApprove(Comment comment, User loginUser) {
        Flow current = this.flowService.getCurrentNode("check", Bid.class);
        Bid bid = this.bidRepository.findBidById(comment.getTargetId());
        if (bid == null || bid.getStatus() != current.getStatus()) {
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
            this.bidRepository.updateStatus(comment.getTargetId(), current.getNextAgree().getStatus(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextAgree().getStage(), new Long[]{bid.getAssigned().getId()}, bid, loginUser);
        } else {
            this.bidRepository.updateStatus(comment.getTargetId(), current.getNextReject().getStatus(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextReject().getStage(), new Long[]{bid.getAssigned().getId()}, bid, loginUser);
        }
    }

    /**
     *
     */
    public void checkApproves(BidCheckApprovesForm form, User loginUser) {
        Flow current = this.flowService.getCurrentNode("check", Bid.class);
        Bid bid = this.bidRepository.findBidById(form.getTargetId());

        if (!this.checkBidStatus(bid, current.getStatus())) {
            throw new SystemException(30000);
        }
        if (form.getType() == Comment.CommentType.DISALLOW) {
            form.setStatus(current.getNextReject().getStatus());
        } else {
            if (form.getStatus() != current.getNextAgree().getStatus() && form.getStatus() != current.getNextAgree2().getStatus()) {
                throw new SystemException(30000);
            }
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
        bid.setStatus(form.getStatus());
        bid.setModifyId(loginUser.getId());
        bid.setModifyTime(new Date());
        this.bidRepository.save(bid);
        String nextStep = "";
        if (form.getStatus() == current.getNextAgree().getStatus()) {
            nextStep = current.getNextAgree().getStage();
        } else if (form.getStatus() == current.getNextAgree2().getStatus()) {
            nextStep = current.getNextAgree2().getStage();
        } else if (form.getStatus() == current.getNextReject().getStatus()) {
            nextStep = current.getNextReject().getStage();
        }
        this.workitemService.createWorkitemWithComplete(form.getWorkitemId(), nextStep, new Long[]{bid.getAssigned().getId()}, bid, loginUser);
    }

    public void argueApprove(BidArgueForm form, User loginUser) {
        Flow current = this.flowService.getCurrentNode("argue", Bid.class);
        Bid bid = this.bidRepository.findBidById(form.getTargetId());
        if (!this.checkBidStatus(bid, current.getStatus())) {
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
        bid.setArgueFiles(form.getArgueFiles());
        bid.setModifyId(loginUser.getId());
        bid.setModifyTime(new Date());
        if (comment.getType() == Comment.CommentType.ALLOW) {
            bid.setStatus(current.getNextAgree().getStatus());
            this.workitemService.createWorkitemWithComplete(form.getWorkitemId(), current.getNextAgree().getStage(), new Long[]{bid.getAssigned().getId()}, bid, loginUser);
        } else {
            bid.setStatus(current.getNextReject().getStatus());
            this.workitemService.createWorkitemWithComplete(form.getWorkitemId(), current.getNextReject().getStage(), "bid_audit_dept_approver", bid, loginUser);
        }
        this.bidRepository.save(bid);
    }

    /**
     *
     */
    public void auditDeptApprove(Comment comment, User loginUser) {
        Flow current = this.flowService.getCurrentNode("audit_dept", Bid.class);
        Bid bid = this.bidRepository.findBidById(comment.getTargetId());
        if (!this.checkBidStatus(bid, current.getStatus())) {
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
            this.bidRepository.updateStatus(comment.getTargetId(), current.getNextAgree().getStatus(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextAgree().getStage(), new Long[]{bid.getAssigned().getId()}, bid, loginUser);
        } else {
            this.bidRepository.updateStatus(comment.getTargetId(), current.getNextReject().getStatus(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextReject().getStage(), new Long[]{bid.getCreatorId()}, bid, loginUser);
        }
    }

    /**
     *
     */
    public void argueRejectApprove(BidArgueRejectForm form, User loginUser) {
        Flow current = this.flowService.getCurrentNode("argue_reject", Bid.class);
        Bid bid = this.bidRepository.findBidById(form.getTargetId());
        if (!this.checkBidStatus(bid, current.getStatus())) {
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
        bid.setStatus(current.getNextAgree().getStatus());
        bid.setSupplementFiles(form.getSupplementFiles());
        bid.setModifyId(loginUser.getId());
        bid.setModifyTime(new Date());
        this.bidRepository.save(bid);
        this.workitemService.createWorkitemWithComplete(form.getWorkitemId(), current.getNextAgree().getStage(), "bid_audit_dept_approver", bid, loginUser);
    }

    /**
     *
     */
    public void auditFirstApprove(BidAuditFirstForm form, User loginUser) {
        Flow current = this.flowService.getCurrentNode("audit_first", Bid.class);
        Bid bid = this.bidRepository.findBidById(form.getTargetId());
        if (bid == null || bid.getStatus() != current.getStatus()) {
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
        bid.setStatus(current.getNextAgree().getStatus());
        bid.setSubmissionPrice(form.getSubmissionPrice());
        bid.setFirstAuditPrice(form.getFirstAuditPrice());
        bid.setAuditFirstFiles(form.getAuditFirstFiles());
        bid.setAuditFirstSub(form.getAuditFirstSub());
        bid.setAuditFirstSubRatio(form.getAuditFirstSubRatio());
        bid.setQas(form.getQas());
        bid.setModifyId(loginUser.getId());
        bid.setModifyTime(new Date());
        this.bidRepository.save(bid);
        this.workitemService.createWorkitemWithComplete(form.getWorkitemId(), current.getNextAgree().getStage(), "bid_audit_second_approver", bid, loginUser);
    }

    /**
     *
     */
    public void auditSecondApprove(BidAuditSecondForm form, User loginUser) {
        Flow current = this.flowService.getCurrentNode("audit_second", Bid.class);
        Bid bid = this.bidRepository.findBidById(form.getTargetId());
        if (bid == null || bid.getStatus() != current.getStatus()) {
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
        bid.setStatus(current.getNextAgree().getStatus());
        bid.setSecondAuditPrice(form.getSecondAuditPrice());

        BigDecimal auditFee = new BigDecimal(0);
        BigDecimal subtractPrice = bid.getSubmissionPrice().subtract(bid.getSecondAuditPrice());
        BigDecimal s1 = bid.getSubmissionPrice().floatValue() == 0 ? new BigDecimal(0) : subtractPrice.divide(bid.getSubmissionPrice(), 2, BigDecimal.ROUND_HALF_UP);
        if (s1.floatValue() >= 0.05 && s1.floatValue() < 0.1) {
            auditFee = subtractPrice.multiply(new BigDecimal("0.05"));
        } else if (s1.floatValue() >= 0.1) {
            auditFee = subtractPrice.multiply(new BigDecimal("0.1"));
        }
        bid.setAuditFee(auditFee);
        bid.setSubtractPrice(subtractPrice);

        bid.setAuditSecondFiles(form.getAuditSecondFiles());
        bid.setAuditNote(form.getAuditNote());
        bid.setAuditSecondSub(form.getAuditSecondSub());
        bid.setAuditSecondSubRatio(form.getAuditSecondSubRatio());
        bid.setAuditSecondNote(form.getAuditSecondNote());
        bid.setModifyId(loginUser.getId());
        bid.setModifyTime(new Date());
        this.bidRepository.save(bid);
        this.workitemService.createWorkitemWithComplete(form.getWorkitemId(), current.getNextAgree().getStage(), "bid_filed_approver", bid, loginUser);
    }

    public void takeAdvice(Comment comment, User loginUser) {
        Flow current = this.flowService.getCurrentNode("take_advice", Bid.class);
        Bid bid = this.bidRepository.findBidById(comment.getTargetId());
        if (!this.checkBidStatus(bid, current.getStatus())) {
            throw new SystemException(30000);
        }
        comment.setTarget(current.getTarget());
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage(current.getStage());
        comment.setStageStr(current.getStageStr());
        comment.setTypeStr(comment.getType() == Comment.CommentType.ALLOW ? current.getAgreeStr() : current.getRejectStr());
        this.commentRepository.save(comment);
        bid.setProjectSum(comment.getProjectSum());
        this.bidRepository.save(bid);
        if (comment.getType() == Comment.CommentType.ALLOW) {
            this.bidRepository.updateStatus(bid.getId(), current.getNextAgree().getStatus(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextAgree().getStage(), "bid_filed_approver", bid, loginUser);
        } else {
            this.bidRepository.updateStatus(bid.getId(), current.getNextReject().getStatus(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextReject().getStage(), "bid_audit_second_approver", bid, loginUser);
        }
    }

    /**
     *
     */
    public void completeApprove(Comment comment, User loginUser) {
        Flow current = this.flowService.getCurrentNode("complete", Bid.class);
        Bid bid = this.bidRepository.findBidById(comment.getTargetId());
        if (bid == null || bid.getStatus() != current.getStatus()) {
            throw new SystemException(30000);
        }
        comment.setTarget(current.getTarget());
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage(current.getStage());
        comment.setStageStr(current.getStageStr());
        comment.setTypeStr(comment.getType() == Comment.CommentType.ALLOW ? current.getAgreeStr() : current.getRejectStr());
        this.commentRepository.save(comment);
        bid.setProjectSum(comment.getProjectSum());
        this.bidRepository.save(bid);
        if (comment.getType() == Comment.CommentType.ALLOW) {
            this.bidRepository.updateStatus(bid.getId(), current.getNextAgree().getStatus(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextAgree().getStage(), "bid_filed_approver", bid, loginUser);
        } else {
            this.bidRepository.updateStatus(bid.getId(), current.getNextReject().getStatus(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextReject().getStage(), "bid_audit_second_approver", bid, loginUser);
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
        Flow current = this.flowService.getCurrentNode("filed", Bid.class);
        Bid bid = this.bidRepository.findBidById(comment.getTargetId());
        if (bid == null || bid.getStatus() != current.getStatus()) {
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
            this.bidRepository.updateStatus(bid.getId(), current.getNextAgree().getStatus(), loginUser.getId(), new Date());
            this.workitemService.completeWorkitem(comment.getWorkitemId(), loginUser);
        } else {
            this.bidRepository.updateStatus(bid.getId(), current.getNextReject().getStatus(), loginUser.getId(), new Date());
            this.workitemService.createWorkitemWithComplete(comment.getWorkitemId(), current.getNextReject().getStage(), "bid_filed_approver", bid, loginUser);
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
    private boolean checkBidStatus(Bid bid, int status) {
        if (bid == null || bid.isDel()) {
            return false;
        }
        return bid.getStatus() == status;
    }

    /**
     *
     */
    private String getAuditNo() {
        String prefix = String.format("KS%d", Calendar.getInstance().get(Calendar.YEAR) - 2000);
        Integer no = this.bidRepository.findMaxAuditNo(prefix + "%");
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
    BidRepository bidRepository;

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
}