package com.huasit.apm.business.submission.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huasit.apm.business.material.entity.MaterialGroupDetail;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Entity
@Table(name = "BUSINESS_SUBMISSION")
public class SubmissionForm implements Serializable {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     */
    @Column(nullable = false)
    private String itemCode;

    /**
     *
     */
    @Column(nullable = false)
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
    @Column(nullable = false)
    private String feeFrom;
    /**
     *
     */
    @Column(nullable = false)
    private BigDecimal budget;
    /**
     *
     */
    @Column(nullable = false)
    private String constructionUnit;
    /**
     *
     */
    @Column(nullable = false)
    private Date startDate;
    /**
     *
     */
    @Column(nullable = false)
    private Date endDate;
    /**
     *
     */
    @Column(nullable = false)
    private BigDecimal contractMoney;

    /**
     *
     */
    @Column(nullable = false)
    private BigDecimal constructMoney;

    /**
     *
     */
    @Column(nullable = false)
    private BigDecimal installMoney;

    /**
     *
     */
    @Column(nullable = false)
    private BigDecimal constructionUnitApplyFee;

    /**
     *
     */
    @Column(nullable = false)
    private BigDecimal constructionUnitCheckFee;

    /**
     *
     */
    @Column(nullable = false)
    private String constructionUnitTel;

    /**
     *
     */
    @Column(nullable = false)
    private BigDecimal inspectUnitApplyFee;

    /**
     *
     */
    @Column(nullable = false)
    private BigDecimal inspectUnitCheckFee;

    /**
     *
     */
    @Column(nullable = false)
    private String inspectUnitTel;

    /**
     *
     */
    @Column(nullable = false)
    private BigDecimal buildUnitApplyFee;

    /**
     *
     */
    @Column(nullable = false)
    private BigDecimal buildUnitCheckFee;

    /**
     *
     */
    @Column(nullable = false)
    private String buildUnitTel;

    /**
     *
     */
    @Column(nullable = false)
    private String content;

    /**
     *
     */
    @Column(nullable = false)
    private String description;

    /**
     *
     */
    @Column(nullable = false)
    private Integer materialGroup;

    /**
     *
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "submissionForm", orphanRemoval = true)
    private List<SubmissionDetail> details;
}