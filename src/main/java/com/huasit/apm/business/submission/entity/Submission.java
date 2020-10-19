package com.huasit.apm.business.submission.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.entity.UserLink;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Entity
@Table(name = "SUBMISSION")
public class Submission implements Serializable {

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
    @Column
    private boolean del;

    /**
     *
     */
    @Column
    private int status;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "assignedId")
    private User assigned;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "assignedLinkId")
    private UserLink assignedLink;

    /**
     *
     */
    @Column
    private String itemCode;

    /**
     *
     */
    private String auditType;

    /**
     *
     */
    private String auditNo;

    /**
     *
     */
    @Column
    private String contractNo;
    /**
     *
     */
    @Column
    private String projectName;
    /**
     *
     */
    @Column
    private String feeFrom;
    /**
     *
     */
    @Column
    private BigDecimal budget;
    /**
     *
     */
    @Column
    private Long constructionUnit;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date startDate;
    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date endDate;

    /**
     *
     */
    @Column
    private BigDecimal contractMoney;

    /**
     *
     */
    @Column
    private BigDecimal constructMoney;

    /**
     *
     */
    @Column
    private BigDecimal installMoney;

    /**
     *
     */
    @Column
    private BigDecimal constructionUnitApplyFee;

    /**
     *
     */
    @Column
    private BigDecimal constructionUnitCheckFee;

    /**
     *
     */
    @Column
    private String constructionUnitTel;

    /**
     *
     */
    @Column
    private BigDecimal inspectUnitApplyFee;

    /**
     *
     */
    @Column
    private BigDecimal inspectUnitCheckFee;

    /**
     *
     */
    @Column
    private String inspectUnitTel;

    /**
     *
     */
    @Column
    private BigDecimal buildUnitApplyFee;

    /**
     *
     */
    @Column
    private BigDecimal buildUnitCheckFee;

    /**
     *
     */
    @Column
    private String buildUnitTel;

    /**
     *
     */
    @Column
    private String content;

    /**
     *
     */
    @Column
    private String payType;

    /**
     *
     */
    @Column
    private String payCondition;

    /**
     *
     */
    @Column
    private String description;

    /**
     *
     */
    @Column
    private Integer materialGroup;

    /**
     *
     */
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date viewDate;

    /**
     *
     */
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date prepareViewDate;

    /**
     *
     */
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date viewDate2;

    /**
     *
     */
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date prepareViewDate2;

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
    @Column
    private BigDecimal secondAuditPrice;

    /**
     *
     */
    @Column
    private BigDecimal subtractPrice;

    /**
     *
     */
    @Column
    private BigDecimal auditFee;

    @Column
    private String payTypeOther;
    @Column
    private String payConditionOther;
    @Column
    private String constructionUnitProjectMan;
    @Column
    private String inspectUnitProjectMan;
    @Column
    private String buildUnitProjectMan;
    @Column
    private String viewPeoplesAuditUnitIds;
    @Column
    private String viewPeoplesAuditUnitIds2;
    @Column
    private String viewPeoplesBuildUnitIds;
    @Column
    private String viewPeoplesConstructUnitIds;
    @Column
    private String viewPeoplesEntrustUnitIds;
    @Column
    private String viewPeoplesBuildUnitIds2;
    @Column
    private String viewPeoplesConstructUnitIds2;
    @Column
    private String viewPeoplesEntrustUnitIds2;
    @Column
    private String auditFirstSub;
    @Column
    private String auditFirstSubRatio;
    @Column
    private String auditSecondSub;
    @Column
    private String auditNote;
    @Column
    private String auditSecondSubRatio;

    /**
     *
     */
    @Column
    private Long modifyId;

    /**
     *
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date modifyTime;

    /**
     *
     */
    @Column
    private Long creatorId;

    /**
     *
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date createTime;

    /**
     *
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "sid")
    private List<SubmissionDetail> details;

    /**
     *
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "sid")
    private List<SubmissionSurveyFile> surveyFiles;

    /**
     *
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "sid")
    private List<SubmissionArgueFile> argueFiles;

    /**
     *
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "sid")
    private List<SubmissionSupplementFile> supplementFiles;

    /**
     *
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "sid")
    private List<SubmissionAuditFirst> auditFirstFiles;

    /**
     *
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "sid")
    private List<SubmissionAuditSecond> auditSecondFiles;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getAssigned() {
        return assigned;
    }

    public void setAssigned(User assigned) {
        this.assigned = assigned;
    }

    public UserLink getAssignedLink() {
        return assignedLink;
    }

    public void setAssignedLink(UserLink assignedLink) {
        this.assignedLink = assignedLink;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public String getAuditNo() {
        return auditNo;
    }

    public void setAuditNo(String auditNo) {
        this.auditNo = auditNo;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getFeeFrom() {
        return feeFrom;
    }

    public void setFeeFrom(String feeFrom) {
        this.feeFrom = feeFrom;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public Long getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(Long constructionUnit) {
        this.constructionUnit = constructionUnit;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(BigDecimal contractMoney) {
        this.contractMoney = contractMoney;
    }

    public BigDecimal getConstructMoney() {
        return constructMoney;
    }

    public void setConstructMoney(BigDecimal constructMoney) {
        this.constructMoney = constructMoney;
    }

    public BigDecimal getInstallMoney() {
        return installMoney;
    }

    public void setInstallMoney(BigDecimal installMoney) {
        this.installMoney = installMoney;
    }

    public BigDecimal getConstructionUnitApplyFee() {
        return constructionUnitApplyFee;
    }

    public void setConstructionUnitApplyFee(BigDecimal constructionUnitApplyFee) {
        this.constructionUnitApplyFee = constructionUnitApplyFee;
    }

    public BigDecimal getConstructionUnitCheckFee() {
        return constructionUnitCheckFee;
    }

    public void setConstructionUnitCheckFee(BigDecimal constructionUnitCheckFee) {
        this.constructionUnitCheckFee = constructionUnitCheckFee;
    }

    public String getConstructionUnitTel() {
        return constructionUnitTel;
    }

    public void setConstructionUnitTel(String constructionUnitTel) {
        this.constructionUnitTel = constructionUnitTel;
    }

    public BigDecimal getInspectUnitApplyFee() {
        return inspectUnitApplyFee;
    }

    public void setInspectUnitApplyFee(BigDecimal inspectUnitApplyFee) {
        this.inspectUnitApplyFee = inspectUnitApplyFee;
    }

    public BigDecimal getInspectUnitCheckFee() {
        return inspectUnitCheckFee;
    }

    public void setInspectUnitCheckFee(BigDecimal inspectUnitCheckFee) {
        this.inspectUnitCheckFee = inspectUnitCheckFee;
    }

    public String getInspectUnitTel() {
        return inspectUnitTel;
    }

    public void setInspectUnitTel(String inspectUnitTel) {
        this.inspectUnitTel = inspectUnitTel;
    }

    public BigDecimal getBuildUnitApplyFee() {
        return buildUnitApplyFee;
    }

    public void setBuildUnitApplyFee(BigDecimal buildUnitApplyFee) {
        this.buildUnitApplyFee = buildUnitApplyFee;
    }

    public BigDecimal getBuildUnitCheckFee() {
        return buildUnitCheckFee;
    }

    public void setBuildUnitCheckFee(BigDecimal buildUnitCheckFee) {
        this.buildUnitCheckFee = buildUnitCheckFee;
    }

    public String getBuildUnitTel() {
        return buildUnitTel;
    }

    public void setBuildUnitTel(String buildUnitTel) {
        this.buildUnitTel = buildUnitTel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayCondition() {
        return payCondition;
    }

    public void setPayCondition(String payCondition) {
        this.payCondition = payCondition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaterialGroup() {
        return materialGroup;
    }

    public void setMaterialGroup(Integer materialGroup) {
        this.materialGroup = materialGroup;
    }

    public Date getViewDate() {
        return viewDate;
    }

    public void setViewDate(Date viewDate) {
        this.viewDate = viewDate;
    }

    public Date getPrepareViewDate() {
        return prepareViewDate;
    }

    public void setPrepareViewDate(Date prepareViewDate) {
        this.prepareViewDate = prepareViewDate;
    }

    public Date getViewDate2() {
        return viewDate2;
    }

    public void setViewDate2(Date viewDate2) {
        this.viewDate2 = viewDate2;
    }

    public Date getPrepareViewDate2() {
        return prepareViewDate2;
    }

    public void setPrepareViewDate2(Date prepareViewDate2) {
        this.prepareViewDate2 = prepareViewDate2;
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

    public BigDecimal getSecondAuditPrice() {
        return secondAuditPrice;
    }

    public void setSecondAuditPrice(BigDecimal secondAuditPrice) {
        this.secondAuditPrice = secondAuditPrice;
    }

    public BigDecimal getSubtractPrice() {
        return subtractPrice;
    }

    public void setSubtractPrice(BigDecimal subtractPrice) {
        this.subtractPrice = subtractPrice;
    }

    public BigDecimal getAuditFee() {
        return auditFee;
    }

    public void setAuditFee(BigDecimal auditFee) {
        this.auditFee = auditFee;
    }

    public String getPayTypeOther() {
        return payTypeOther;
    }

    public void setPayTypeOther(String payTypeOther) {
        this.payTypeOther = payTypeOther;
    }

    public String getPayConditionOther() {
        return payConditionOther;
    }

    public void setPayConditionOther(String payConditionOther) {
        this.payConditionOther = payConditionOther;
    }

    public String getConstructionUnitProjectMan() {
        return constructionUnitProjectMan;
    }

    public void setConstructionUnitProjectMan(String constructionUnitProjectMan) {
        this.constructionUnitProjectMan = constructionUnitProjectMan;
    }

    public String getInspectUnitProjectMan() {
        return inspectUnitProjectMan;
    }

    public void setInspectUnitProjectMan(String inspectUnitProjectMan) {
        this.inspectUnitProjectMan = inspectUnitProjectMan;
    }

    public String getBuildUnitProjectMan() {
        return buildUnitProjectMan;
    }

    public void setBuildUnitProjectMan(String buildUnitProjectMan) {
        this.buildUnitProjectMan = buildUnitProjectMan;
    }

    public String getViewPeoplesAuditUnitIds() {
        return viewPeoplesAuditUnitIds;
    }

    public void setViewPeoplesAuditUnitIds(String viewPeoplesAuditUnitIds) {
        this.viewPeoplesAuditUnitIds = viewPeoplesAuditUnitIds;
    }

    public String getViewPeoplesAuditUnitIds2() {
        return viewPeoplesAuditUnitIds2;
    }

    public void setViewPeoplesAuditUnitIds2(String viewPeoplesAuditUnitIds2) {
        this.viewPeoplesAuditUnitIds2 = viewPeoplesAuditUnitIds2;
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

    public String getAuditSecondSub() {
        return auditSecondSub;
    }

    public void setAuditSecondSub(String auditSecondSub) {
        this.auditSecondSub = auditSecondSub;
    }

    public String getAuditNote() {
        return auditNote;
    }

    public void setAuditNote(String auditNote) {
        this.auditNote = auditNote;
    }

    public String getAuditSecondSubRatio() {
        return auditSecondSubRatio;
    }

    public void setAuditSecondSubRatio(String auditSecondSubRatio) {
        this.auditSecondSubRatio = auditSecondSubRatio;
    }

    public Long getModifyId() {
        return modifyId;
    }

    public void setModifyId(Long modifyId) {
        this.modifyId = modifyId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<SubmissionDetail> getDetails() {
        return details;
    }

    public void setDetails(List<SubmissionDetail> details) {
        this.details = details;
    }

    public List<SubmissionSurveyFile> getSurveyFiles() {
        return surveyFiles;
    }

    public void setSurveyFiles(List<SubmissionSurveyFile> surveyFiles) {
        this.surveyFiles = surveyFiles;
    }

    public List<SubmissionArgueFile> getArgueFiles() {
        return argueFiles;
    }

    public void setArgueFiles(List<SubmissionArgueFile> argueFiles) {
        this.argueFiles = argueFiles;
    }

    public List<SubmissionSupplementFile> getSupplementFiles() {
        return supplementFiles;
    }

    public void setSupplementFiles(List<SubmissionSupplementFile> supplementFiles) {
        this.supplementFiles = supplementFiles;
    }

    public List<SubmissionAuditFirst> getAuditFirstFiles() {
        return auditFirstFiles;
    }

    public void setAuditFirstFiles(List<SubmissionAuditFirst> auditFirstFiles) {
        this.auditFirstFiles = auditFirstFiles;
    }

    public List<SubmissionAuditSecond> getAuditSecondFiles() {
        return auditSecondFiles;
    }

    public void setAuditSecondFiles(List<SubmissionAuditSecond> auditSecondFiles) {
        this.auditSecondFiles = auditSecondFiles;
    }
}