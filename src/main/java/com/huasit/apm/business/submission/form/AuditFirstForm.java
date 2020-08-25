package com.huasit.apm.business.submission.form;

import com.huasit.apm.business.submission.entity.SubmissionAuditFirst;
import com.huasit.apm.core.comment.entity.Comment;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.List;

public class AuditFirstForm {

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
    private String prepareViewDate2;

    /**
     *
     */
    private String viewDate2;

    /**
     *
     */
    private String viewPeopleIds2;

    /**
     *
     */
    @Column
    private BigDecimal submissionPrice;

    /**
     *
     */
    @Column
    private BigDecimal firstAuditPrice;

    /**
     *
     */
    private List<SubmissionAuditFirst> auditFirstFiles;

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

    public BigDecimal getSubmissionPrice() {
        return submissionPrice;
    }

    public void setSubmissionPrice(BigDecimal submissionPrice) {
        this.submissionPrice = submissionPrice;
    }

    public BigDecimal getFirstAuditPrice() {
        return firstAuditPrice;
    }

    public void setFirstAuditPrice(BigDecimal firstAuditPrice) {
        this.firstAuditPrice = firstAuditPrice;
    }

    public List<SubmissionAuditFirst> getAuditFirstFiles() {
        return auditFirstFiles;
    }

    public void setAuditFirstFiles(List<SubmissionAuditFirst> auditFirstFiles) {
        this.auditFirstFiles = auditFirstFiles;
    }

    public String getPrepareViewDate2() {
        return prepareViewDate2;
    }

    public void setPrepareViewDate2(String prepareViewDate2) {
        this.prepareViewDate2 = prepareViewDate2;
    }

    public String getViewDate2() {
        return viewDate2;
    }

    public void setViewDate2(String viewDate2) {
        this.viewDate2 = viewDate2;
    }

    public String getViewPeopleIds2() {
        return viewPeopleIds2;
    }

    public void setViewPeopleIds2(String viewPeopleIds2) {
        this.viewPeopleIds2 = viewPeopleIds2;
    }
}
