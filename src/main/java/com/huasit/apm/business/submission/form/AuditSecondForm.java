package com.huasit.apm.business.submission.form;

import com.huasit.apm.business.submission.entity.SubmissionAuditSecond;
import com.huasit.apm.core.comment.entity.Comment;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.List;

public class AuditSecondForm {

    /**
     *
     */
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
    private List<SubmissionAuditSecond> auditSecondFiles;

    private String auditNote;
    private String  auditSecondSub;
    private String auditSecondSubRatio;

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
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

    public List<SubmissionAuditSecond> getAuditSecondFiles() {
        return auditSecondFiles;
    }

    public void setAuditSecondFiles(List<SubmissionAuditSecond> auditSecondFiles) {
        this.auditSecondFiles = auditSecondFiles;
    }

    public String getAuditSecondNote() {
        return auditSecondNote;
    }

    public void setAuditSecondNote(String auditSecondNote) {
        this.auditSecondNote = auditSecondNote;
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
