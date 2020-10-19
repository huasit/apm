package com.huasit.apm.business.bid.entity;

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
@Table(name = "Bid")
public class Bid implements Serializable {

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date startDate;


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
    private String content;


    @Column
    private Integer materialGroup;


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
    private String auditFirstSub;
    @Column
    private String auditFirstSubRatio;
    @Column
    private String auditSecondSub;
    @Column
    private String auditNote;
    @Column
    private String auditSecondSubRatio;

    @Column
    private String bidUnit;
    @Column
    private String bidMan;
    @Column
    private String bidManTel;
    @Column
    private BigDecimal subMoney;

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
    private List<BidDetail> details;

    /**
     *
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "sid")
    private List<BidAuditFirst> auditFirstFiles;

    /**
     *
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "sid")
    private List<BidAuditSecond> auditSecondFiles;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getMaterialGroup() {
        return materialGroup;
    }

    public void setMaterialGroup(Integer materialGroup) {
        this.materialGroup = materialGroup;
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

    public String getBidUnit() {
        return bidUnit;
    }

    public void setBidUnit(String bidUnit) {
        this.bidUnit = bidUnit;
    }

    public String getBidMan() {
        return bidMan;
    }

    public void setBidMan(String bidMan) {
        this.bidMan = bidMan;
    }

    public String getBidManTel() {
        return bidManTel;
    }

    public void setBidManTel(String bidManTel) {
        this.bidManTel = bidManTel;
    }

    public BigDecimal getSubMoney() {
        return subMoney;
    }

    public void setSubMoney(BigDecimal subMoney) {
        this.subMoney = subMoney;
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

    public List<BidDetail> getDetails() {
        return details;
    }

    public void setDetails(List<BidDetail> details) {
        this.details = details;
    }

    public List<BidAuditFirst> getAuditFirstFiles() {
        return auditFirstFiles;
    }

    public void setAuditFirstFiles(List<BidAuditFirst> auditFirstFiles) {
        this.auditFirstFiles = auditFirstFiles;
    }

    public List<BidAuditSecond> getAuditSecondFiles() {
        return auditSecondFiles;
    }

    public void setAuditSecondFiles(List<BidAuditSecond> auditSecondFiles) {
        this.auditSecondFiles = auditSecondFiles;
    }
}