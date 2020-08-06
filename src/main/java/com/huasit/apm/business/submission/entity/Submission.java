package com.huasit.apm.business.submission.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

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
    @Column(nullable = false)
    private String itemCode;

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
    private String constructionUnit;
    /**
     *
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date startDate;
    /**
     *
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
    private String description;

    /**
     *
     */
    @Column
    private Integer materialGroup;

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
    @JoinColumn(name="sid")
    private List<SubmissionDetail> details;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
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

    public String getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(String constructionUnit) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}