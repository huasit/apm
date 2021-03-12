package com.huasit.apm.core.flow.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FLOW")
public class Flow {


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
    private String target;

    /**
     *
     */
    @Column(nullable = false)
    private String stage;

    /**
     *
     */
    @Column(nullable = false)
    private int status;

    /**
     *
     */
    @Column(nullable = false)
    private String targetStr;

    /**
     *
     */
    @Column(nullable = false)
    private String stageStr;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "nextAgreeId")
    private Flow nextAgree;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "nextAgreeId2")
    private Flow nextAgree2;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "nextRejectId")
    private Flow nextReject;

    /**
     *
     */
    @Column(nullable = false)
    private String agreeStr;

    /**
     *
     */
    @Column(nullable = false)
    private String rejectStr;

}
