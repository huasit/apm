package com.huasit.apm.business.bid.form;

import com.huasit.apm.business.bid.entity.BidAuditSecond;
import com.huasit.apm.core.comment.entity.Comment;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.List;

public class BidAuditSecondForm {

    /**
     *
     */
    @Transient
    private Long workitemId;

    /**
     *
     */
    private Long targetId;

    /**
     *
     */
    private Comment.CommentType type;

    /**
     *
     */
    private String comment;

    /**
     *
     */
    private String auditSecondNote;

    /**
     *
     */
    @Column
    private BigDecimal secondAuditPrice;

    /**
     *
     */
    private List<BidAuditSecond> auditSecondFiles;

    private String auditNote;
    private String  auditSecondSub;
    private String auditSecondSubRatio;

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getAuditSecondNote() {
        return auditSecondNote;
    }

    public void setAuditSecondNote(String auditSecondNote) {
        this.auditSecondNote = auditSecondNote;
    }

    public Comment.CommentType getType() {
        return type;
    }

    public void setType(Comment.CommentType type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getSecondAuditPrice() {
        return secondAuditPrice;
    }

    public void setSecondAuditPrice(BigDecimal secondAuditPrice) {
        this.secondAuditPrice = secondAuditPrice;
    }

    public List<BidAuditSecond> getAuditSecondFiles() {
        return auditSecondFiles;
    }

    public void setAuditSecondFiles(List<BidAuditSecond> auditSecondFiles) {
        this.auditSecondFiles = auditSecondFiles;
    }

    public String getAuditNote() {
        return auditNote;
    }

    public void setAuditNote(String auditNote) {
        this.auditNote = auditNote;
    }

    public String getAuditSecondSub() {
        return auditSecondSub;
    }

    public void setAuditSecondSub(String auditSecondSub) {
        this.auditSecondSub = auditSecondSub;
    }

    public String getAuditSecondSubRatio() {
        return auditSecondSubRatio;
    }

    public void setAuditSecondSubRatio(String auditSecondSubRatio) {
        this.auditSecondSubRatio = auditSecondSubRatio;
    }

    public Long getWorkitemId() {
        return workitemId;
    }

    public void setWorkitemId(Long workitemId) {
        this.workitemId = workitemId;
    }
}
