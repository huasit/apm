package com.huasit.apm.business.bid.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huasit.apm.business.thirdparty.entity.Thirdparty;
import com.huasit.apm.core.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @JoinColumn(name = "thirdpartyId")
    private Thirdparty thirdparty;

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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "sid")
    private List<BidArgueFile> argueFiles;

    /**
     *
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "sid")
    private List<BidSupplementFile> supplementFiles;


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
    @Column
    private String memberIds;
    @Column
    private String projectSum;
    @Column
    private String qas;
    @Column
    private String auditSecondNote;

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

    /**
     *
     */
    @Transient
    private Long workitemId;

    /**
     *
     */
    @Transient
    private List<Integer> statuses;

}