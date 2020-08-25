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
    @Column
    private BigDecimal secondAuditPrice;

    /**
     *
     */
    private List<SubmissionAuditSecond> auditSecondFiles;

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
}
