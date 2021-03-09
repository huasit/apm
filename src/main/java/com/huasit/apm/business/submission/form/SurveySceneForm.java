package com.huasit.apm.business.submission.form;

import com.huasit.apm.business.submission.entity.SubmissionSurveyFile;
import com.huasit.apm.core.comment.entity.Comment;

import java.util.List;

public class SurveySceneForm {

    /**
     *
     */
    private int status;

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
    private String viewDate;

    /**
     *
     */
    private Comment.CommentType type;

    /**
     *
     */
    private String comment;


    private String viewPeoplesAuditUnitIds;
    private String viewPeoplesBuildUnitIds;
    private String viewPeoplesConstructUnitIds;
    private String viewPeoplesEntrustUnitIds;

    /**
     *
     */
    private List<SubmissionSurveyFile> surveyFiles;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Long getWorkitemId() {
        return workitemId;
    }

    public void setWorkitemId(Long workitemId) {
        this.workitemId = workitemId;
    }

    public String getViewDate() {
        return viewDate;
    }

    public void setViewDate(String viewDate) {
        this.viewDate = viewDate;
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

    public String getViewPeoplesAuditUnitIds() {
        return viewPeoplesAuditUnitIds;
    }

    public void setViewPeoplesAuditUnitIds(String viewPeoplesAuditUnitIds) {
        this.viewPeoplesAuditUnitIds = viewPeoplesAuditUnitIds;
    }

    public String getViewPeoplesBuildUnitIds() {
        return viewPeoplesBuildUnitIds;
    }

    public void setViewPeoplesBuildUnitIds(String viewPeoplesBuildUnitIds) {
        this.viewPeoplesBuildUnitIds = viewPeoplesBuildUnitIds;
    }

    public String getViewPeoplesConstructUnitIds() {
        return viewPeoplesConstructUnitIds;
    }

    public void setViewPeoplesConstructUnitIds(String viewPeoplesConstructUnitIds) {
        this.viewPeoplesConstructUnitIds = viewPeoplesConstructUnitIds;
    }

    public String getViewPeoplesEntrustUnitIds() {
        return viewPeoplesEntrustUnitIds;
    }

    public void setViewPeoplesEntrustUnitIds(String viewPeoplesEntrustUnitIds) {
        this.viewPeoplesEntrustUnitIds = viewPeoplesEntrustUnitIds;
    }

    public List<SubmissionSurveyFile> getSurveyFiles() {
        return surveyFiles;
    }

    public void setSurveyFiles(List<SubmissionSurveyFile> surveyFiles) {
        this.surveyFiles = surveyFiles;
    }
}
