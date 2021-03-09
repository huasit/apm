package com.huasit.apm.core.workitem.entity;

import com.huasit.apm.core.user.entity.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "WORKITEM_HIS")
public class WorkitemHis {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     */
    @Column(nullable = false)
    private String target;

    /**
     *
     */
    @Column(nullable = false)
    private Long targetId;

    /**
     *
     */
    @Column(nullable = false)
    private String stage;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "approverId")
    private User approver;

    /**
     *
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date reachTime;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "transactorId")
    private User transactor;

    /**
     *
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date completeTime;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "creatorId")
    private User creator;

    /**
     *
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createTime;

    /**
     *
     */
    @Transient
    private String stageStr;

    /**
     *
     */
    @Transient
    private String targetStr;

    /**
     *
     */
    @Transient
    private String auditNo;

    /**
     *
     */
    @Transient
    private String projectName;

    /**
     *
     */
    @Transient
    private String auditUnit;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getTarget() {
        return target;
    }


    public void setTarget(String target) {
        this.target = target;
    }


    public Long getTargetId() {
        return targetId;
    }


    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }


    public String getStage() {
        return stage;
    }


    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getAuditNo() {
        return auditNo;
    }

    public void setAuditNo(String auditNo) {
        this.auditNo = auditNo;
    }

    public String getProjectName() {
        return projectName;
    }


    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    public User getApprover() {
        return approver;
    }


    public void setApprover(User approver) {
        this.approver = approver;
    }


    public Date getReachTime() {
        return reachTime;
    }


    public void setReachTime(Date reachTime) {
        this.reachTime = reachTime;
    }

    public User getTransactor() {
        return transactor;
    }

    public void setTransactor(User transactor) {
        this.transactor = transactor;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }


    public User getCreator() {
        return creator;
    }


    public void setCreator(User creator) {
        this.creator = creator;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public String getStageStr() {
        return stageStr;
    }


    public void setStageStr(String stageStr) {
        this.stageStr = stageStr;
    }


    public String getTargetStr() {
        return targetStr;
    }


    public void setTargetStr(String targetStr) {
        this.targetStr = targetStr;
    }

    public String getAuditUnit() {
        return auditUnit;
    }

    public void setAuditUnit(String auditUnit) {
        this.auditUnit = auditUnit;
    }
}