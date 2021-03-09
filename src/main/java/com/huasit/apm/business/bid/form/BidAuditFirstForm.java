package com.huasit.apm.business.bid.form;

import com.huasit.apm.business.bid.entity.BidAuditFirst;
import com.huasit.apm.core.comment.entity.Comment;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.List;

public class BidAuditFirstForm {

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
    private String qas;

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
    private List<BidAuditFirst> auditFirstFiles;

    private String auditFirstSub;
    private String auditFirstSubRatio;

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

    public String getQas() {
        return qas;
    }

    public void setQas(String qas) {
        this.qas = qas;
    }

    public BigDecimal getFirstAuditPrice() {
        return firstAuditPrice;
    }

    public void setFirstAuditPrice(BigDecimal firstAuditPrice) {
        this.firstAuditPrice = firstAuditPrice;
    }

    public List<BidAuditFirst> getAuditFirstFiles() {
        return auditFirstFiles;
    }

    public void setAuditFirstFiles(List<BidAuditFirst> auditFirstFiles) {
        this.auditFirstFiles = auditFirstFiles;
    }

    public String getAuditFirstSub() {
        return auditFirstSub;
    }

    public void setAuditFirstSub(String auditFirstSub) {
        this.auditFirstSub = auditFirstSub;
    }

    public String getAuditFirstSubRatio() {
        return auditFirstSubRatio;
    }

    public void setAuditFirstSubRatio(String auditFirstSubRatio) {
        this.auditFirstSubRatio = auditFirstSubRatio;
    }

    public Long getWorkitemId() {
        return workitemId;
    }

    public void setWorkitemId(Long workitemId) {
        this.workitemId = workitemId;
    }
}
