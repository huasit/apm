package com.huasit.apm.business.bid.service;

import com.huasit.apm.business.bid.entity.*;
import com.huasit.apm.business.bid.form.BidAuditFirstForm;
import com.huasit.apm.business.bid.form.BidAuditSecondForm;
import com.huasit.apm.core.comment.entity.Comment;
import com.huasit.apm.core.comment.entity.CommentRepository;
import com.huasit.apm.core.file.entity.File;
import com.huasit.apm.core.file.service.FileService;
import com.huasit.apm.core.role.service.RoleService;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.entity.UserLink;
import com.huasit.apm.core.user.service.UserService;
import com.huasit.apm.system.exception.SystemException;
import com.huasit.apm.system.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
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
                if (form.getStatus() != 0) {
                    predicates.add(cb.equal(root.get("status").as(int.class), form.getStatus()));
                }
                if (!viewAll) {
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
        if (form.getStatus() != -10 && form.getStatus() != 10) {
            throw new SystemException(30000);
        }
        form.setAssigned(null);
        form.setAssignedLink(null);
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
    public void projectApprove(Comment comment, User loginUser) {
        Bid bid = this.bidRepository.findBidById(comment.getTargetId());
        if (bid == null || bid.getStatus() != 10) {
            throw new SystemException(30000);
        }
        comment.setTarget("bid");
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage("project");
        this.commentRepository.save(comment);
        if (comment.getType() == Comment.CommentType.ALLOW && bid.getAuditNo() == null) {
            this.bidRepository.updateStatusAndAuditNo(comment.getTargetId(), 20, comment.getAuditNo(), loginUser.getId(), new Date());
        } else {
            this.bidRepository.updateStatusAndAuditNo(comment.getTargetId(), comment.getType() == Comment.CommentType.ALLOW ? 20 : -20, comment.getAuditNo(), loginUser.getId(), new Date());
            //this.bidRepository.updateStatus(comment.getTargetId(), comment.getType() == Comment.CommentType.ALLOW ? 20 : -20, loginUser.getId(), new Date());
        }
    }

    /**
     *
     */
    public void projectApproves(Long[] targetIds, int type, String commentContent, User loginUser) {
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
    public void distributionApprove(Comment comment, String auditType, Long assignedId, Long assignedLinkId, User loginUser) {
        Bid bid = this.bidRepository.findBidById(comment.getTargetId());
        if (bid == null || bid.getStatus() != 20) {
            throw new SystemException(30000);
        }
        comment.setTarget("bid");
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage("distribution");
        this.commentRepository.save(comment);
        if (comment.getType() == Comment.CommentType.ALLOW) {
            User assigned = this.userService.getUserById(assignedId);
            UserLink assignedLink = null;
            if(assignedLinkId != null) {
                assignedLink = this.userService.getUserLinkById(assignedLinkId);
            }
            this.bidRepository.updateStatusAndAssigned(comment.getTargetId(), 30, assigned,assignedLink, auditType, loginUser.getId(), new Date());
        } else {
            this.bidRepository.updateStatus(comment.getTargetId(), -20, loginUser.getId(), new Date());
        }
    }

    /**
     *
     */
    public void distributionApproves(Long[] targetIds, int type, String auditType, Long assignedId, Long assignedLinkId, String commentContent, User loginUser) {
        for (Long targetId : targetIds) {
            Comment comment = new Comment();
            comment.setTargetId(targetId);
            comment.setContent(commentContent);
            comment.setType(Comment.CommentType.get(type));
            this.distributionApprove(comment, auditType, assignedId,assignedLinkId, loginUser);
        }
    }

    /**
     *
     */
    public void checkApprove(Comment comment, User loginUser) {
        Bid bid = this.bidRepository.findBidById(comment.getTargetId());
        if (bid == null || bid.getStatus() != 30) {
            throw new SystemException(30000);
        }
        comment.setTarget("bid");
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage("check");
        this.commentRepository.save(comment);
        if (comment.getType() == Comment.CommentType.ALLOW) {
            this.bidRepository.updateStatus(comment.getTargetId(), 40, loginUser.getId(), new Date());
        } else {
            this.bidRepository.updateStatus(comment.getTargetId(), -20, loginUser.getId(), new Date());
        }
    }

    /**
     *
     */
    public void checkApproves(Long[] targetIds, int type, String commentContent, User loginUser) {
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
    public void auditFirstApprove(BidAuditFirstForm form, User loginUser)  {
        Bid bid = this.bidRepository.findBidById(form.getTargetId());
        if (bid == null || bid.getStatus() != 40) {
            throw new SystemException(30000);
        }
        Comment comment = new Comment();
        comment.setTargetId(form.getTargetId());
        comment.setContent(form.getComment());
        comment.setType(form.getType());
        comment.setTarget("bid");
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage("audit_first");
        this.commentRepository.save(comment);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        bid.setStatus(50);
        bid.setSubmissionPrice(form.getSubmissionPrice());
        bid.setFirstAuditPrice(form.getFirstAuditPrice());
        bid.setAuditFirstFiles(form.getAuditFirstFiles());
        bid.setAuditFirstSub(form.getAuditFirstSub());
        bid.setAuditFirstSubRatio(form.getAuditFirstSubRatio());
        bid.setModifyId(loginUser.getId());
        bid.setModifyTime(new Date());
        this.bidRepository.save(bid);
    }

    /**
     *
     */
    public void auditSecondApprove(BidAuditSecondForm form, User loginUser) {
        Bid bid = this.bidRepository.findBidById(form.getTargetId());
        if (bid == null || bid.getStatus() != 50) {
            throw new SystemException(30000);
        }
        Comment comment = new Comment();
        comment.setTargetId(form.getTargetId());
        comment.setContent(form.getComment());
        comment.setType(form.getType());
        comment.setTarget("bid");
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage("audit_second");
        this.commentRepository.save(comment);
        bid.setStatus(60);
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
        bid.setModifyId(loginUser.getId());
        bid.setModifyTime(new Date());
        this.bidRepository.save(bid);
    }

    /**
     *
     */
    public void completeApprove(Comment comment, User loginUser) {
        Bid bid = this.bidRepository.findBidById(comment.getTargetId());
        if (bid == null || bid.getStatus() != 60) {
            throw new SystemException(30000);
        }
        comment.setTarget("bid");
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage("complete");
        this.commentRepository.save(comment);
        this.bidRepository.updateStatus(bid.getId(), comment.getType() == Comment.CommentType.ALLOW ? 70 : 50, loginUser.getId(), new Date());
    }

    /**
     *
     */
    public void completeApproves(Long[] targetIds, int type, String commentContent, User loginUser) {
        for (Long targetId : targetIds) {
            Comment comment = new Comment();
            comment.setTargetId(targetId);
            comment.setContent(commentContent);
            comment.setType(Comment.CommentType.get(type));
            this.completeApprove(comment, loginUser);
        }
    }

    /**
     *
     */
    public void filedApprove(Comment comment, User loginUser) {
        Bid bid = this.bidRepository.findBidById(comment.getTargetId());
        if (bid == null || bid.getStatus() != 70) {
            throw new SystemException(30000);
        }
        comment.setTarget("bid");
        comment.setCreator(loginUser);
        comment.setCreateTime(new Date());
        comment.setStage("filed");
        this.commentRepository.save(comment);
        this.bidRepository.updateStatus(bid.getId(), comment.getType() == Comment.CommentType.ALLOW ? 80 : 60, loginUser.getId(), new Date());
    }

    /**
     *
     */
    public void filedApproves(Long[] targetIds, int type, String commentContent, User loginUser) {
        for (Long targetId : targetIds) {
            Comment comment = new Comment();
            comment.setTargetId(targetId);
            comment.setContent(commentContent);
            comment.setType(Comment.CommentType.get(type));
            this.filedApprove(comment, loginUser);
        }
    }

    /**
     *
     */
    private int auditNoMax;

    /**
     *
     */
    //@Override
    public void run(ApplicationArguments applicationArguments) {
        String max = this.bidRepository.findMaxAuditNo();
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
    BidRepository bidRepository;

    /**
     *
     */
    @Autowired
    CommentRepository commentRepository;
}