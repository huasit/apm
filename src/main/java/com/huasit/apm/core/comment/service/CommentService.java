package com.huasit.apm.core.comment.service;

import com.huasit.apm.core.comment.entity.Comment;
import com.huasit.apm.core.comment.entity.CommentRepository;
import com.huasit.apm.system.util.LocaleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CommentService {

    /**
     *
     */
    public List<Comment> getByTarget(String target, Long targetId) {
        return this.commentRepository.findByTarget(target, targetId);
    }

    /**
     *
     */
    public List<Comment> getByTarget(String target, Long targetId, HttpServletRequest request) {
        List<Comment> comments = this.getByTarget(target, targetId);
        if (comments != null) {
            for (Comment comment : comments) {
                switch (comment.getType()) {
                    case ALLOW:
                        comment.setTypeStr(LocaleUtil.getMessage(request, "comment_type_allow"));
                        break;
                    case DISALLOW:
                        comment.setTypeStr(LocaleUtil.getMessage(request, "comment_type_disallow"));
                        break;
                    default:
                        comment.setTypeStr(LocaleUtil.getMessage(request, "comment_type_default"));
                }
                switch (comment.getStage()) {
                    case "project":
                        comment.setStageStr(LocaleUtil.getMessage(request, "comment_stage_project"));
                        break;
                    default:
                        comment.setStageStr("");
                }
            }
        }
        return comments;
    }

    /**
     *
     */
    @Autowired
    CommentRepository commentRepository;
}
