package com.huasit.apm.business.submission.entity;

import com.huasit.apm.core.file.entity.File;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 */
@Entity
@Table(name = "SUBMISSION_AUDITSECOND")
public class SubmissionAuditSecond implements Serializable {

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
    private Integer mId;

    /**
     *
     */
    @Column(nullable = false)
    private String mName;

    /**
     *
     */
    private String mNote;

    /**
     *
     */
    private String mFileIds;

    /**
     *
     */
    @Transient
    private List<File> mFiles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmNote() {
        return mNote;
    }

    public void setmNote(String mNote) {
        this.mNote = mNote;
    }

    public String getmFileIds() {
        return mFileIds;
    }

    public void setmFileIds(String mFileIds) {
        this.mFileIds = mFileIds;
    }

    public List<File> getmFiles() {
        return mFiles;
    }

    public void setmFiles(List<File> mFiles) {
        this.mFiles = mFiles;
    }
}