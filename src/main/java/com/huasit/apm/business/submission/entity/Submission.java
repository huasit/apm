package com.huasit.apm.business.submission.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huasit.apm.core.user.entity.User;
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
    @Column(nullable = false)
    private boolean del;

    /**
     *
     */
    @Column(nullable = false)
    private int status;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name="assignedId")
    private User assigned;

    /**
     *
     */
    @Column(nullable = false)
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
    @Column(nullable = false)
    private String contractNo;
    /**
     *
     */
    @Column(nullable = false)
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
    @Column(nullable = false)
    private String viewPeopleIds;

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
    @Column(nullable = false)
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

    /**
     *
     */
    @Column(nullable = false)
    private Long modifyId;

    /**
     *
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date modifyTime;

    /**
     *
     */
    @Column(nullable = false)
    private Long creatorId;

    /**
     *
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createTime;

    /**
     *
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name="sid")
    private List<SubmissionDetail> details;

    /**
     *
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name="sid")
    private List<SubmissionSurveyFile> surveyFiles;

    /**
     *
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name="sid")
    private List<SubmissionArgueFile> argueFiles;

    /**
     *
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name="sid")
    private List<SubmissionSupplementFile> supplementFiles;

    /**
     *
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name="sid")
    private List<SubmissionAuditFirst> auditFirstFiles;

    /**
     *
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name="sid")
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

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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

    public String getViewPeopleIds() {
        return viewPeopleIds;
    }

    public void setViewPeopleIds(String viewPeopleIds) {
        this.viewPeopleIds = viewPeopleIds;
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

    public List<SubmissionSupplementFile> getSupplementFiles() {
        return supplementFiles;
    }

    public void setSupplementFiles(List<SubmissionSupplementFile> supplementFiles) {
        this.supplementFiles = supplementFiles;
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

    public String getViewPeopleIds2() {
        return viewPeopleIds2;
    }

    public void setViewPeopleIds2(String viewPeopleIds2) {
        this.viewPeopleIds2 = viewPeopleIds2;
    }

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }
}