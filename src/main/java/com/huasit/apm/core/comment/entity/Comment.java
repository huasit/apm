package com.huasit.apm.core.comment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huasit.apm.core.user.entity.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMMENT")
public class Comment {

    /**
     *
     */
    public enum CommentType {
        DISALLOW, ALLOW, DEFAULT;
        public static CommentType get(int v) {
            switch(v) {
                case 0:
                    return DISALLOW;
                case 1:
                    return ALLOW;
                case 2:
                    return DEFAULT;
                default:
                    return null;
            }
        }
    }

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     */
    @JsonIgnore
    @Column(nullable = false)
    private boolean del;

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
    private CommentType type;

    /**
     *
     */
    @Column(nullable = false)
    private String typeStr;

    /**
     *
     */
    @Column(nullable = false)
    private String stage;

    /**
     *
     */
    @Column(nullable = false)
    private String stageStr;

    /**
     *
     */
    @Column(nullable = false)
    private String content;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name="creatorId")
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
    private String auditNo;

    /**
     *
     */
    @Transient
    private Long workitemId;

    /**
     *
     */
    @Transient
    private String auditType;

    /**
     *
     */
    @Transient
    private Long assignedId;

    /**
     *
     */
    @Transient
    private Long thirdpartyId;

    /**
     *
     */
    @Transient
    private String prepareViewDate;

    /**
     *
     */
    @Transient
    private String memberIds;

    /**
     *
     */
    @Transient
    private String projectSum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Long getWorkitemId() {
        return workitemId;
    }

    public void setWorkitemId(Long workitemId) {
        this.workitemId = workitemId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public CommentType getType() {
        return type;
    }

    public void setType(CommentType type) {
        this.type = type;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public String getStageStr() {
        return stageStr;
    }

    public void setStageStr(String stageStr) {
        this.stageStr = stageStr;
    }

    public String getAuditNo() {
        return auditNo;
    }

    public void setAuditNo(String auditNo) {
        this.auditNo = auditNo;
    }

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public Long getAssignedId() {
        return assignedId;
    }

    public void setAssignedId(Long assignedId) {
        this.assignedId = assignedId;
    }

    public Long getThirdpartyId() {
        return thirdpartyId;
    }

    public void setThirdpartyId(Long thirdpartyId) {
        this.thirdpartyId = thirdpartyId;
    }

    public String getPrepareViewDate() {
        return prepareViewDate;
    }

    public void setPrepareViewDate(String prepareViewDate) {
        this.prepareViewDate = prepareViewDate;
    }

    public String getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(String memberIds) {
        this.memberIds = memberIds;
    }

    public String getProjectSum() {
        return projectSum;
    }

    public void setProjectSum(String projectSum) {
        this.projectSum = projectSum;
    }
}