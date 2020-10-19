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


    private String auditFirstSub;
    private String auditFirstSubRatio;
    private String viewPeoplesAuditUnitIds2;
    private String viewPeoplesBuildUnitIds2;
    private String viewPeoplesConstructUnitIds2;
    private String viewPeoplesEntrustUnitIds2;


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

    public String getViewPeoplesAuditUnitIds2() {
        return viewPeoplesAuditUnitIds2;
    }

    public void setViewPeoplesAuditUnitIds2(String viewPeoplesAuditUnitIds2) {
        this.viewPeoplesAuditUnitIds2 = viewPeoplesAuditUnitIds2;
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

    public String getViewPeoplesBuildUnitIds2() {
        return viewPeoplesBuildUnitIds2;
    }

    public void setViewPeoplesBuildUnitIds2(String viewPeoplesBuildUnitIds2) {
        this.viewPeoplesBuildUnitIds2 = viewPeoplesBuildUnitIds2;
    }

    public String getViewPeoplesConstructUnitIds2() {
        return viewPeoplesConstructUnitIds2;
    }

    public void setViewPeoplesConstructUnitIds2(String viewPeoplesConstructUnitIds2) {
        this.viewPeoplesConstructUnitIds2 = viewPeoplesConstructUnitIds2;
    }

    public String getViewPeoplesEntrustUnitIds2() {
        return viewPeoplesEntrustUnitIds2;
    }

    public void setViewPeoplesEntrustUnitIds2(String viewPeoplesEntrustUnitIds2) {
        this.viewPeoplesEntrustUnitIds2 = viewPeoplesEntrustUnitIds2;
    }
}
