package com.huasit.apm.core.flow.entity;

import javax.persistence.*;

@Entity
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
    @JoinColumn(name="nextAgreeId")
    private Flow nextAgree;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name="nextRejectId")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTargetStr() {
        return targetStr;
    }

    public void setTargetStr(String targetStr) {
        this.targetStr = targetStr;
    }

    public String getStageStr() {
        return stageStr;
    }

    public void setStageStr(String stageStr) {
        this.stageStr = stageStr;
    }

    public Flow getNextAgree() {
        return nextAgree;
    }

    public void setNextAgree(Flow nextAgree) {
        this.nextAgree = nextAgree;
    }

    public Flow getNextReject() {
        return nextReject;
    }

    public void setNextReject(Flow nextReject) {
        this.nextReject = nextReject;
    }

    public String getAgreeStr() {
        return agreeStr;
    }

    public void setAgreeStr(String agreeStr) {
        this.agreeStr = agreeStr;
    }

    public String getRejectStr() {
        return rejectStr;
    }

    public void setRejectStr(String rejectStr) {
        this.rejectStr = rejectStr;
    }
}
