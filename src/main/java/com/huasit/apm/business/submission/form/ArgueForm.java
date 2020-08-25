package com.huasit.apm.business.submission.form;

import com.huasit.apm.business.submission.entity.SubmissionArgueFile;
import com.huasit.apm.core.comment.entity.Comment;

import java.util.List;

public class ArgueForm {

    /**
     *
     */
    private Long targetId;

    /**
     *
     */
    private Comment.CommentType type;

    /**
     *
     */
    private String comment;

    /**
     *
     */
    private List<SubmissionArgueFile> argueFiles;

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Comment.CommentType getType() {
        return type;
    }

    public void setType(Comment.CommentType type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<SubmissionArgueFile> getArgueFiles() {
        return argueFiles;
    }

    public void setArgueFiles(List<SubmissionArgueFile> argueFiles) {
        this.argueFiles = argueFiles;
    }
}
