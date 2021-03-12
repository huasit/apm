package com.huasit.apm.core.workitem.service;

import com.huasit.apm.business.bid.entity.Bid;
import com.huasit.apm.business.bid.entity.BidRepository;
import com.huasit.apm.business.submission.entity.Submission;
import com.huasit.apm.business.submission.entity.SubmissionRepository;
import com.huasit.apm.core.flow.entity.Flow;
import com.huasit.apm.core.flow.service.FlowService;
import com.huasit.apm.core.role.entity.Role;
import com.huasit.apm.core.role.entity.RoleGroupUser;
import com.huasit.apm.core.role.entity.RoleGroupUserRepository;
import com.huasit.apm.core.role.entity.RoleRepository;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.entity.UserRepository;
import com.huasit.apm.core.workitem.entity.Workitem;
import com.huasit.apm.core.workitem.entity.WorkitemHis;
import com.huasit.apm.core.workitem.entity.WorkitemHisRepository;
import com.huasit.apm.core.workitem.entity.WorkitemRepository;
import com.huasit.apm.system.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class WorkitemService {

    /**
     * 创建Submission待办
     */
    public void createWorkitem(String stage, Long[] approverIds, Submission submission, User loginUser) {
        this.createWorkitem("submission", submission.getId(), stage, approverIds, submission.getCreatorId(), submission.getCreateTime(), loginUser);
    }

    /**
     * 创建Submission待办
     */
    public void createWorkitem(String stage, String roleKey, Submission submission, User loginUser) {
        List<RoleGroupUser> list = this.roleGroupUserRepository.findByRoleRkey(roleKey);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        Long[] approverIds = new Long[list.size()];
        for (int n = 0; n < list.size(); n++) {
            approverIds[n] = list.get(n).getUserId();
        }
        this.createWorkitem("submission", submission.getId(), stage, approverIds, submission.getCreatorId(), submission.getCreateTime(), loginUser);
    }

    /**
     * 创建Submission待办
     */
    public void createWorkitemWithComplete(Long workitemId, String stage, Long[] approverIds, Submission submission, User loginUser) {
        Workitem workitem = this.workitemRepository.findById(workitemId).orElse(null);
        if (workitem == null) {
            return;
        }
        this.createWorkitem(stage, approverIds, submission, loginUser);
        this.completeWorkitem(workitem, loginUser);
    }

    /**
     * 创建Submission待办
     */
    public void createWorkitemWithComplete(Long workitemId, String stage, String roleKey, Submission submission, User loginUser) {
        Workitem workitem = this.workitemRepository.findById(workitemId).orElse(null);
        if (workitem == null) {
            return;
        }
        this.createWorkitem(stage, roleKey, submission, loginUser);
        this.completeWorkitem(workitem, loginUser);
    }

    /**
     * 创建Submission待办
     */
    public void createWorkitemWithApply(String stage, String roleKey, Submission submission, User loginUser) {
        this.createWorkitem(stage, roleKey, submission, loginUser);
        this.createApplyRecord("submission", submission.getId(), submission.getCreatorId(), submission.getCreateTime(), loginUser);
    }

    /**
     * 创建Bid待办
     */
    public void createWorkitem(String stage, Long[] approverIds, Bid bid, User loginUser) {
        this.createWorkitem("bid", bid.getId(), stage, approverIds, bid.getCreatorId(), bid.getCreateTime(), loginUser);
    }

    /**
     * 创建Bid待办
     */
    public void createWorkitem(String stage, String roleKey, Bid bid, User loginUser) {
        List<RoleGroupUser> list = this.roleGroupUserRepository.findByRoleRkey(roleKey);
        if (CollectionUtils.isEmpty(list)) {
            Role role = roleRepository.findByRkey(roleKey);
            throw new SystemException(50000, role.getName());
        }
        Long[] approverIds = new Long[list.size()];
        for (int n = 0; n < list.size(); n++) {
            approverIds[n] = list.get(n).getUserId();
        }
        this.createWorkitem("bid", bid.getId(), stage, approverIds, bid.getCreatorId(), bid.getCreateTime(), loginUser);
    }

    /**
     * 根据指定人创建待办
     */
    public void createWorkitemWithComplete(Long workitemId, String stage, Long[] approverIds, Bid bid, User loginUser) {
        Workitem workitem = this.workitemRepository.findById(workitemId).orElse(null);
        if (workitem == null) {
            return;
        }
        this.createWorkitem(stage, approverIds, bid, loginUser);
        this.completeWorkitem(workitem, loginUser);
    }

    /**
     * 根据指定角色创建待办
     */
    public void createWorkitemWithComplete(Long workitemId, String stage, String roleKey, Bid bid, User loginUser) {
        Workitem workitem = this.workitemRepository.findById(workitemId).orElse(null);
        if (workitem == null) {
            return;
        }
        this.createWorkitem(stage, roleKey, bid, loginUser);
        this.completeWorkitem(workitem, loginUser);
    }

    /**
     * 创建Bid待办
     */
    public void createWorkitemWithApply(String stage, String roleKey, Bid bid, User loginUser) {
        this.createWorkitem(stage, roleKey, bid, loginUser);
        this.createApplyRecord("bid", bid.getId(), bid.getCreatorId(), bid.getCreateTime(), loginUser);
    }

    /**
     * 创建待办
     */
    private void createWorkitem(String target, Long targetId, String stage, Long[] approverIds, Long applyerId, Date applyTime, User loginUser) {
        if (approverIds == null || approverIds.length == 0) {
            throw new SystemException(40000);
        }
        User applyer = this.userRepository.findUserById(applyerId);
        List<Workitem> workitems = new ArrayList<>();
        for (Long approverId : approverIds) {
            Date now = new Date();
            Workitem workitem = new Workitem();
            workitem.setTarget(target);
            workitem.setTargetId(targetId);
            workitem.setStage(stage);
            workitem.setApprover(this.userRepository.findUserById(approverId));
            workitem.setReachTime(now);
            workitem.setCreator(applyer);
            workitem.setCreateTime(applyTime);
            workitems.add(workitem);
        }
        this.workitemRepository.saveAll(workitems);
    }

    /**
     * 创建申请纪录
     */
    private void createApplyRecord(String target, Long targetId, Long applyerId, Date applyTime, User loginUser) {
        User applyer = this.userRepository.findUserById(applyerId);
        Date now = new Date();
        WorkitemHis his = new WorkitemHis();
        his.setTarget(target);
        his.setTargetId(targetId);
        his.setStage("[start]");
        his.setApprover(loginUser);
        his.setTransactor(loginUser);
        his.setReachTime(now);
        his.setCompleteTime(now);
        his.setCreator(applyer);
        his.setCreateTime(applyTime);
        this.workitemHisRepository.save(his);
    }

    /**
     * 完成待办
     */
    public void completeWorkitem(Long workitemId, User loginUser) {
        Workitem workitem = this.workitemRepository.findById(workitemId).orElse(null);
        if (workitem == null) {
            return;
        }
        this.completeWorkitem(workitem, loginUser);
    }

    /**
     * 完成待办
     */
    public void completeWorkitem(Workitem workitem, User loginUser) {
        Date now = new Date();
        WorkitemHis his = new WorkitemHis();
        his.setTarget(workitem.getTarget());
        his.setTargetId(workitem.getTargetId());
        his.setStage(workitem.getStage());
        his.setAuditNo(workitem.getAuditNo());
        his.setProjectName(workitem.getProjectName());
        his.setApprover(workitem.getApprover());
        his.setTransactor(loginUser);
        his.setReachTime(workitem.getReachTime());
        his.setCompleteTime(now);
        his.setCreator(workitem.getCreator());
        his.setCreateTime(workitem.getCreateTime());
        this.workitemHisRepository.save(his);
        this.workitemRepository.delete(workitem.getTarget(), workitem.getTargetId(), workitem.getStage());
    }

    /**
     *
     */
    public Page<Workitem> getWorkitemWithUndo(Long userId, int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("reachTime")));
        Page<Workitem> data = this.workitemRepository.findByApproverId(userId, pageRequest);
        return this.dealLocaleStr(data);
    }

    /**
     *
     */
    public Page<WorkitemHis> getWorkitemWithApply(Long userId, int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("reachTime")));
        return this.dealLocaleStrHis(this.workitemHisRepository.findByStageAndApproverId("apply", userId, pageRequest));
    }

    /**
     *
     */
    public Page<WorkitemHis> getWorkitemWithApprove(Long userId, int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("reachTime")));
        return this.dealLocaleStrHis(this.workitemHisRepository.findByApproverId(userId, pageRequest));
    }

    /**
     *
     */
    public Page<Workitem> getWorkitemWithAudit(Long userId, int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("reachTime")));
        List<String> stages = new ArrayList<>();
        stages.add("audit_first");
        stages.add("audit_second");
        return this.dealLocaleStr(this.workitemRepository.findByStagesAndApproverId(stages, userId, pageRequest));
    }

    /**
     *
     */
    public List<Object[]> getWorkitemReachTime(String target, Long targetId) {
        return this.workitemRepository.findReachTime(target, targetId);
    }

    /**
     *
     */
    private Page<Workitem> dealLocaleStr(Page<Workitem> workitems) {
        if (workitems == null || !workitems.hasContent()) {
            return workitems;
        }
        for (Workitem workitem : workitems.getContent()) {
            Flow node = flowService.getCurrentNode(workitem.getTarget(), workitem.getStage());
            workitem.setTargetStr(node.getTargetStr());
            workitem.setStageStr(node.getStageStr());
            String nextStageStr = "";
            if (node.getNextAgree() != null) {
                nextStageStr = nextStageStr + node.getNextAgree().getStageStr();
            }
            if (node.getNextReject() != null) {
                nextStageStr = nextStageStr + "/" + node.getNextReject().getStageStr();
            }
            workitem.setNextStageStr(nextStageStr);
            if ("submission".equals(workitem.getTarget())) {
                Submission submission = this.submissionRepository.findSubmissionById(workitem.getTargetId());
                if (submission != null) {
                    workitem.setAuditUnit(submission.getAuditUnit());
                    workitem.setAuditNo(submission.getAuditNo());
                    workitem.setProjectName(submission.getProjectName());
                }
            } else if ("bid".equals(workitem.getTarget())) {
                Bid bid = this.bidRepository.findBidById(workitem.getTargetId());
                if (bid != null) {
                    workitem.setAuditNo(bid.getAuditNo());
                    workitem.setProjectName(bid.getProjectName());
                }
            }
        }
        return workitems;
    }

    /**
     *
     */
    private Page<WorkitemHis> dealLocaleStrHis(Page<WorkitemHis> workitems) {
        if (workitems == null || !workitems.hasContent()) {
            return workitems;
        }
        for (WorkitemHis workitem : workitems.getContent()) {
            Flow node = flowService.getCurrentNode(workitem.getTarget(), workitem.getStage());
            workitem.setTargetStr(node.getTargetStr());
            workitem.setStageStr(node.getStageStr());
            if ("submission".equals(workitem.getTarget())) {
                Submission submission = this.submissionRepository.findSubmissionById(workitem.getTargetId());
                if (submission != null) {
                    workitem.setAuditUnit(submission.getAuditUnit());
                    workitem.setAuditNo(submission.getAuditNo());
                    workitem.setProjectName(submission.getProjectName());
                }
            } else if ("bid".equals(workitem.getTarget())) {
                Bid bid = this.bidRepository.findBidById(workitem.getTargetId());
                if (bid != null) {
                    workitem.setAuditNo(bid.getAuditNo());
                    workitem.setProjectName(bid.getProjectName());
                }
            }
        }
        return workitems;
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
    BidRepository bidRepository;

    /**
     *
     */
    @Autowired
    UserRepository userRepository;

    /**
     *
     */
    @Autowired
    WorkitemService workitemService;

    /**
     *
     */
    @Autowired
    WorkitemRepository workitemRepository;

    /**
     *
     */
    @Autowired
    SubmissionRepository submissionRepository;

    /**
     *
     */
    @Autowired
    WorkitemHisRepository workitemHisRepository;

    /**
     *
     */
    @Autowired
    RoleGroupUserRepository roleGroupUserRepository;

    @Autowired
    RoleRepository roleRepository;
}