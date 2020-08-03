package com.huasit.apm.business.submission.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.io.Serializable;

/**
 *
 */
@Entity
@Table(name = "BUSINESS_SUBMISSION_DETAIL")
public class SubmissionDetail implements Serializable {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "submissionFormId", nullable = false)
    private SubmissionForm submissionForm;

    /**
     *
     */
    @Column(nullable = false)
    private Integer mId;

    /**
     *
     */
    @Column(nullable = false)
    private String mName;

    /**
     *
     */
    @Column(nullable = false)
    private Integer[] mFiles;

    /**
     *
     */
    @Column(nullable = false)
    private String mNote;
    
}